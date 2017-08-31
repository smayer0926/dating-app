package dao;

import models.Answer;
import models.Question;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Sql2oUserDao implements UserDao {
    private final Sql2o sql2o;
    public Sql2oUserDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add (User user){
        String sql = "INSERT INTO users (name, age, gender,genderPreference, matchminage, matchmaxage, zip, bio, email, password) VALUES (:name, :age, :gender,:genderPreference, :matchminage, :matchmaxage, :zip, :bio,:email, :password)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("name", user.getName())
                    .addParameter("age", user.getAge())
                    .addParameter("gender", user.getGender())
                    .addParameter("genderPreference", user.getGenderPreference())
                    .addParameter("matchminage", user.getMatchMinAge())
                    .addParameter("matchmaxage", user.getMatchMaxAge())
                    .addParameter("zip", user.getZip())
                    .addParameter("bio", user.getBio())
                    .addParameter("email", user.getEmail())
                    .addParameter("password", user.getPassword())

                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("AGE", "age")
                    .addColumnMapping("GENDER", "gender")
                    .addColumnMapping("GENDERPREFERENCE", "genderPreference")
                    .addColumnMapping("MATCHMINAGE", "matchminage")
                    .addColumnMapping("MATCHMAXAGE", "matchmaxage")
                    .addColumnMapping("ZIP", "zip")
                    .addColumnMapping("BIO", "bio")
                    .addColumnMapping("EMAIL", "email")
                    .addColumnMapping("PASSWORD", "password")

                    .executeUpdate()
                    .getKey();
            user.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addUserToQuestion(User user, Question question){
        String sql = "INSERT INTO userquestions (userid, questionid) VALUES (:userId, :questionId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("userId", user.getId())
                    .addParameter("questionId", question.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public int countNumberOfUserIdMatches(int userId){
        int matchCount = 0;
        for(User eachUser : getAll()){
            if(eachUser.getId() == userId){
                matchCount ++;
            }
        }
        return matchCount;
    }

    @Override
    public List<User> getAll(){
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM users")
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public List<User> getAllMatches(int userId, int minAge, int maxAge, String genderPreference){
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM users WHERE age >= :matchminage and age <= :matchmaxage and gender = :genderPreference and id != :id")
                    .addParameter("matchminage", minAge)
                    .addParameter("matchmaxage", maxAge)
                    .addParameter("genderPreference", genderPreference)
                    .addParameter("id", userId)
                    .executeAndFetch(User.class);
        }
    }



    @Override
    public List<Answer> getAllAnswers(int matchId) {
        ArrayList<Answer> answers = new ArrayList<>();
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM answers WHERE userid = :userid")
                    .addParameter("userid", matchId)
                    .executeAndFetch(Answer.class);
        }
    }

    @Override
    public User findById(int id){
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM users WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
    }
    @Override
    public  Answer findAnswerByQuestionId(int questionId, int userId){
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM answers WHERE userid = :userid AND questionid = :questionid")
                    .addParameter("userid", userId)
                    .addParameter("questionid", questionId)
                    .executeAndFetchFirst(Answer.class);
        }
    }
//    @Override
//    public List<Integer> getQuestionIdOfAnswersForSpecificUser(int userId){
//        ArrayList<Answer> answersForSpecificUser = new ArrayList<>();
//
//    }

    @Override
    public int evaluateCompatibility(List<Integer> user1QuestionIds, List<Answer> user2Answers, int userId){
    int compatibilityScore = 0;
    int countOfQuestionsShared = 0;
    int countOfCompatibilities= 0;
        for(Answer user2Answer : user2Answers){
            if(user1QuestionIds.contains(user2Answer.getQuestionId())){
                countOfQuestionsShared ++;
                Answer user1Answer = findAnswerByQuestionId(user2Answer.getQuestionId(), userId);
                if(user1Answer.getAcceptableAnswer().contains(user2Answer.getAnswer()) && user2Answer.getAcceptableAnswer().contains(user1Answer.getAnswer())){
                    countOfCompatibilities ++;
                }
            }
            if(countOfQuestionsShared == 0){
                compatibilityScore = 0;
            } else {
                compatibilityScore = (countOfCompatibilities * 100 / countOfQuestionsShared);
            }
        }
        return compatibilityScore;
    }

    public void update(int id, String newName, int newAge, String newGender, String newGenderPreference, int newMinAge, int newMaxAge, String newZip, String newEmail, String newPassword, String newBio){
        String sql = "UPDATE users SET (name, age, gender, genderPreference, matchminage, matchmaxage, zip, email, password, bio) = (:name, :age, :gender, :genderPreference, :matchminage, :matchmaxage, :zip, :email, :password, :bio) WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", newName)
                    .addParameter("age", newAge)
                    .addParameter("gender", newGender)
                    .addParameter("genderPreference", newGenderPreference)
                    .addParameter("matchminage", newMinAge)
                    .addParameter("matchmaxage", newMaxAge)
                    .addParameter("zip", newZip)
                    .addParameter("email", newEmail)
                    .addParameter("password", newPassword)
                    .addParameter("id", id)
                    .addParameter("bio", newBio)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public User getUser(String email){
        List<User> users = getAll();
        String sql = "SELECT * FROM users where email = :email";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("email", email)
                    .executeAndFetchFirst(User.class);
        }
    }


}
