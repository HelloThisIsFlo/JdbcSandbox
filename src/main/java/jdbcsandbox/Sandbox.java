package jdbcsandbox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbcsandbox.dto.Book;
import jdbcsandbox.dto.Review;

public class Sandbox implements AutoCloseable {

  Connection connection;
  List<Statement> openStatements;
  List<ResultSet> openResultSets;

  public Sandbox() throws SQLException {
    openStatements = new ArrayList<>();
    openResultSets = new ArrayList<>();
    connection = connect();
  }

  public void nicolasTemp() throws SQLException {
    Statement statement = newStatement();
        try(ResultSet resultSet = statement.executeQuery(
            "select reviews.id, book_id, username, rating, b.title "
            + "from reviews "
            + " join books b on b.id = reviews.book_id;")) {
          resultSet.next();
          System.out.println("debug here");
        }
  }

  public List<Book> getAllBooks() throws SQLException {
    Statement statement = newStatement();
    try (ResultSet resultSet = statement.executeQuery("select * from reviews")) {
      var yo = parseToReviews(resultSet);
      yo.forEach(System.out::println);
    }
    try (ResultSet resultSet = statement.executeQuery("select * from books")) {
      return parseToBooks(resultSet);
    }
  }

  public Book getBookWithTitle(String title) throws SQLException {
    String SELECT_BOOK_WITH_TITLE = "select * from books where title like ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_WITH_TITLE)) {
      preparedStatement.setString(1, title);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        resultSet.next();
        return parseToBook(resultSet);
      }
    }
  }

  private Statement newStatement() throws SQLException {
    Statement statement = connection.createStatement();
    openStatements.add(statement);
    return statement;
  }


  private Connection connect() throws SQLException {
    return DriverManager.getConnection(
        "jdbc:mysql://localhost/jdbc-sandbox",
        "florian",
        "pass"
    );
  }

  @Override
  public void close() throws Exception {
    connection.close();
    closeAllAndClear(openResultSets);
    closeAllAndClear(openStatements);
  }

  private void closeAllAndClear(List<? extends AutoCloseable> thingsToClose) throws Exception {
    for (AutoCloseable thingToClose : thingsToClose) {
      thingToClose.close();
    }
    thingsToClose.clear();
  }

  private List<Book> parseToBooks(ResultSet resultSet) throws SQLException {
    List<Book> books = new ArrayList<>();
    while (resultSet.next()) {
      books.add(parseToBook(resultSet));
    }
    return books;
  }

  private Book parseToBook(ResultSet resultSet) throws SQLException {
    return new Book(
        resultSet.getInt("id"),
        resultSet.getString("title"),
        resultSet.getString("author")
    );
  }

  private List<Review> parseToReviews(ResultSet resultSet) throws SQLException {
    List<Review> reviews = new ArrayList<>();
    while (resultSet.next()) {
      reviews.add(parseToReview(resultSet));
    }
    return reviews;
  }

  private Review parseToReview(ResultSet resultSet) throws SQLException {
    return new Review(
        resultSet.getInt("id"),
        resultSet.getInt("book_id"),
        resultSet.getString("username"),
        resultSet.getInt("rating")
    );
  }
}
