package dao;


import models.Answer;
import models.Question;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oAnswerDao implements AnswerDao {
    private final Sql2o sql2o;

    public Sql2oAnswerDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Answer answer) {
        String sql = "INSERT INTO answers (questionid, userid, answer, acceptableAnswer) VALUES (:questionid, :userid, :answer, :acceptableAnswer)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("questionid", answer.getQuestionId())
                    .addParameter("userid", answer.getUserId())
                    .addParameter("answer", answer.getAnswer())
                    .addParameter("acceptableAnswer", answer.getAcceptableAnswer())
                    .bind(answer)
                    .executeUpdate()
                    .getKey();
            answer.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public List<Answer> getAllForAnswersSpecificUser(int userId) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM answers WHERE userid = :userid")
                    .addParameter("userid", userId)
                    .executeAndFetch(Answer.class);
        }
    }

    @Override
    public void setAnswerBooleans (Question question, String answer){
        if (answer.equals("1")) {
            question.setAnswerIs1(true);
        } else if (answer.equals("2")) {
            question.setAnswerIs2(true);
        } else if (answer.equals("3")) {
            question.setAnswerIs3(true);
        } else if (answer.equals("4")) {
            question.setAnswerIs4(true);
        }
    }

    @Override
    public List<Integer> getQuestionIdsFromUsersAnsweredQuestions (int userId){
        List<Integer> listOfQuestionIds = new ArrayList<Integer>();
        List<Answer> listOfAnswers = getAllForAnswersSpecificUser(userId);
        for(Answer answer : listOfAnswers) {
            int questionId = answer.getQuestionId();
            listOfQuestionIds.add(questionId);
        }
        return listOfQuestionIds;
    }
}
