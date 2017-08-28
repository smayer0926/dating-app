package dao;

import models.Question;
import models.QuestionOption;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;


public class Sql2oQuestionOptionDao implements QuestionOptionDao{
    private final Sql2o sql2o;
    public Sql2oQuestionOptionDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add (QuestionOption questionOption){
        String sql = "INSERT INTO questionoption (choice, questionid) VALUES (:choice, :questionid)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("choice", questionOption.getChoice())
                    .addParameter("questionid", questionOption.getQuestionId())
                    .addColumnMapping("CHOICE", "choice")
                    .addColumnMapping("QUESTIONID", "questionid")
                    .executeUpdate()
                    .getKey();
            questionOption.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public List<QuestionOption> getAll() {
        return null;
    }

}
