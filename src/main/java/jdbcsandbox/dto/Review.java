package jdbcsandbox.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Review {

  public final int id;
  public final int bookId;
  public final String username;
  public final int rating;


  public Review(int id, int bookId, String username, int rating) {
    this.id = id;
    this.bookId = bookId;
    this.username = username;
    this.rating = rating;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Review review = (Review) o;

    return new EqualsBuilder()
        .append(id, review.id)
        .append(bookId, review.bookId)
        .append(rating, review.rating)
        .append(username, review.username)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(id)
        .append(bookId)
        .append(username)
        .append(rating)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("bookId", bookId)
        .append("username", username)
        .append("rating", rating)
        .toString();
  }
}
