package dao;

import models.DateReview;
import models.Question;
import models.User;

import java.util.List;

public interface DateReviewDao {
    //create
    void add (DateReview dateReview);

    //get
    List<DateReview> getAll();
    List<DateReview> getAllDateReviewsOfSpecificUser(int dateUserId);

}
