package dao;

import models.Question;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

public class Sql2oQuestionResponseDao implements QuestionResponseDao{
    private final Sql2o sql2o;
    public Sql2oQuestionResponseDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }
    @Override
    public void addQuestionToUser(int userId, int questionId, String response){
        String sql = "INSERT INTO userquestions (userid, questionid, response) VALUES (:userId, :questionId, :response)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("userId", userId)
                    .addParameter("questionId", questionId)
                    .addParameter("response", response)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
