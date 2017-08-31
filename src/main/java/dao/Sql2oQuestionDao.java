package dao;

import models.Question;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.lang.StringBuilder;

import java.util.ArrayList;
import java.util.List;

public class Sql2oQuestionDao implements QuestionDao {
    private final Sql2o sql2o;

    public Sql2oQuestionDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Question question) {
        String sql = "INSERT INTO questions (prompt, choice1, choice2, choice3, choice4, userswhohaveanswered) VALUES (:prompt, :choice1, :choice2, :choice3, :choice4, :userswhohaveanswered)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("prompt", question.getPrompt())
                    .addParameter("choice1", question.getChoice1())
                    .addParameter("choice2", question.getChoice2())
                    .addParameter("choice3", question.getChoice3())
                    .addParameter("choice4", question.getChoice4())
                    .addParameter("userswhohaveanswered", question.getUsersWhoHaveAnswered())
                    .bind(question)
                    .executeUpdate()
                    .getKey();
            question.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addQuestionToUser(User user, Question question) {

        String sql = "INSERT INTO userquestions (userid, questionid) VALUES (:userId, :questionId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("userId", user.getId())
                    .addParameter("questionId", question.getId())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


    public String addUserToUsersWhoHaveAnsweredThisQuestion (int userId, Question question){
        StringBuilder str = new StringBuilder(question.getUsersWhoHaveAnswered());
        str.append("," + userId);
        return str.toString();
    }

    public void addUserToUsersWhoHaveAnsweredThisQuestion2 (int userId, Question question, int questionId, String usersWhoHaveAnswered){
        try(Connection con = sql2o.open()){
            con.createQuery("UPDATE questions SET (userswhohaveanswered) = (:userswhohaveanswered) WHERE questionid = :questionid")
                    .addParameter("questionid", questionId)
                    .addParameter("userswhohaveanswered", usersWhoHaveAnswered)
                    .executeAndFetch(Question.class);
        }
    }

    @Override
    public List<Question> getAllUnanswered(int userId, List<Integer>gitquestionAnsweredIds) {
        for(int questionId : questionsAnswered)
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM questions WHERE NOT userswhohaveanswered LIKE '%:userid%'")
                    .addParameter("userid", userId)
                    .executeAndFetch(Question.class);
        }
    }

    @Override
    public List<Question> getAll() {

        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM questions")
                    .executeAndFetch(Question.class);
        }
    }


    @Override
    public void deleteById(int id) {
        String sql = "DELETE from questions WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public Question findById(int id){
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM questions WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Question.class);
        }
    }
}


//    @Override
//    public List<Question> getAllAnswered(){
//        try(Connection con = sql2o.open()) {
//            return con.createQuery("SELECT * FROM questions")
//                    .executeAndFetch(Question.class);
//        }
//    }
//    @Override
//    public List<Question> getAllUnanswered(){
//        try(Connection con = sql2o.open()) {
//            return con.createQuery("SELECT * FROM questions")
//                    .executeAndFetch(Question.class);
//        }
//    }

    //    @Override
//    public List<QuestionOption> getAllForSpecificQuestion(int questionId) {
//        try (Connection con = sql2o.open()) {
//            return con.createQuery("SELECT * FROM questionoptions WHERE questionid = :questionId")
//                    .executeAndFetch(QuestionOption.class);
//        }
//    }

//    @Override
//    public int countNumberOfQuestionIdMatches(int questionId) {
//        int matchCount = 0;
//        for (Question eachQuestion : getAll()) {
//            if (eachQuestion.getId() == questionId) {
//                matchCount++;
//            }
//        }
//        return matchCount;
//    }

//

