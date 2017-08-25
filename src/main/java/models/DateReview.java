package models;

import java.sql.Timestamp;

public class DateReview extends Post{
    private int dateUserId;
    private int rating;

    public DateReview(int userId, String name, String content, Timestamp postTime, int dateUserId, int rating){
        super(userId,name,content,postTime);
        this.dateUserId = dateUserId;
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateReview that = (DateReview) o;

        if (dateUserId != that.dateUserId) return false;
        return rating == that.rating;
    }

    @Override
    public int hashCode() {
        int result = dateUserId;
        result = 31 * result + rating;
        return result;
    }

    public int getDateUserId() {
        return dateUserId;
    }

    public void setDateUserId(int dateUserId) {
        this.dateUserId = dateUserId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
