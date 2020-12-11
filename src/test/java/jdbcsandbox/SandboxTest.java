package jdbcsandbox;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;
import jdbcsandbox.dto.Book;
import org.junit.jupiter.api.Test;

class SandboxTest {

  @Test
  void runSandbox() throws Exception {
    try (Sandbox sandbox = new Sandbox()) {

      List<Book> books = sandbox.getAllBooks();
      for (Book book : books) {
        System.out.println("book = " + book);
      }

      Book mobyDick = sandbox.getBookWithTitle("Moby Dick");
      System.out.println("mobyDick = " + mobyDick);
      sandbox.nicolas();
    }
  }


}