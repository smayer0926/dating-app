package dao;

import models.DateReview;
import models.Question;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oDateReviewDao implements DateReviewDao{
    private final Sql2o sql2o;
    public Sql2oDateReviewDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add (DateReview dateReview){
        String sql = "INSERT INTO datereviews (postcontent, rating) VALUES (:postcontent, :rating)";
        try(Connection con = sql2o.open()) {
            int postId = (int) con.createQuery(sql)
//                    .addParameter("userid", dateReview.getUserId())
//                    .addParameter("dateuserid", dateReview.getDateUserId())
                    .addParameter("postcontent", dateReview.getContent())
                    .addParameter("rating", dateReview.getRating())
//                    .addColumnMapping("USERID", "userid")
//                    .addColumnMapping("DATEUSERID", "dateuserid")
                    .addColumnMapping("POSTCONTENT", "postcontent")
                    .addColumnMapping("RATING", "rating")
                    .executeUpdate()
                    .getKey();
            dateReview.setPostId(postId);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<DateReview> getAll () {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM datereviews")
                    .executeAndFetch(DateReview.class);
        }
    }

    @Override
    public List<DateReview> getAllDateReviewsOfSpecificUser(int dateUserId){
        try (Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM datereviews WHERE userId = :dateUserId")
                    .addParameter("dateUserId", dateUserId)
                    .executeAndFetch(DateReview.class);
        }
    }

//    @Override
//    void addDateReviewToSubject(User user, DateReview dateReview){
//        String sql = "INSERT INTO userquestions (userid, questionid) VALUES (:userId, :questionId)";
//        try (Connection con = sql2o.open()) {
//            con.createQuery(sql)
//                    .addParameter("userId", user.getId())
//                    .addParameter("questionId", question.getId())
//                    .executeUpdate();
//        } catch (Sql2oException ex){
//            System.out.println(ex);
//        }
//    }
//
//    @Override
//    void addDateReviewToAuthor(User user, DateReview dateReview){
//        String sql = "INSERT INTO userquestions (userid, questionid) VALUES (:userId, :questionId)";
//        try (Connection con = sql2o.open()) {
//            con.createQuery(sql)
//                    .addParameter("userId", user.getId())
//                    .addParameter("questionId", question.getId())
//                    .executeUpdate();
//        } catch (Sql2oException ex){
//            System.out.println(ex);
//        }
//    }



    @Override
    public DateReview findById(int id){
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM datereviews WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(DateReview.class);
        }
    }
}
