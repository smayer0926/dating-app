package dao;

import models.DateReview;
import models.Question;
import models.User;

import java.util.List;

public interface DateReviewDao {
    //create
    void add (DateReview dateReview);

    //establish relationship between users and date review
//    void addDateReviewToSubject(User user, DateReview dateReview);
//    void addDateReviewToAuthor(User user, DateReview dateReview);

    //get
    List<DateReview> getAll();
    List<DateReview> getAllDateReviewsOfSpecificUser(int dateUserId);

    //find
    DateReview findById(int id);
}
