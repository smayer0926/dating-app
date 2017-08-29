package dao;

import models.Question;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oUserDao implements UserDao {
    private final Sql2o sql2o;
    public Sql2oUserDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add (User user){
        String sql = "INSERT INTO users (name, age, gender,genderPreference, matchminage, matchmaxage, zip, email, password) VALUES (:name, :age, :gender,:genderPreference, :matchminage, :matchmaxage, :zip, :email, :password)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("name", user.getName())
                    .addParameter("age", user.getAge())
                    .addParameter("gender", user.getGender())
                    .addParameter("genderPreference", user.getGenderPreference())
                    .addParameter("matchminage", user.getMatchMinAge())
                    .addParameter("matchmaxage", user.getMatchMaxAge())
                    .addParameter("zip", user.getZip())
                    .addParameter("email", user.getEmail())
                    .addParameter("password", user.getPassword())

                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("AGE", "age")
                    .addColumnMapping("GENDER", "gender")
                    .addColumnMapping("GENDERPREFERENCE", "genderPreference")
                    .addColumnMapping("MATCHMINAGE", "matchminage")
                    .addColumnMapping("MATCHMAXAGE", "matchmaxage")
                    .addColumnMapping("ZIP", "zip")
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
    public List<Question> getAllQuestionsAnsweredByUser(int userId){
        ArrayList<Question> questions = new ArrayList<>();

        String joinQuery = "SELECT questionid FROM userquestions WHERE userid = :userId";

        try (Connection con = sql2o.open()) {
            List<Integer> allQuestionIds = con.createQuery(joinQuery)
                    .addParameter("userId", userId)
                    .executeAndFetch(Integer.class);
            for (Integer questionId : allQuestionIds){
                String questionQuery = "SELECT * FROM questions WHERE id = :questionId";
                questions.add(
                        con.createQuery(questionQuery)
                                .addParameter("questionId", questionId)
                                .executeAndFetchFirst(Question.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return questions;
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
    public void update(int id, String newName, int newAge, String newGender, String newGenderPreference, int newMinAge, int newMaxAge, String newZip, String newEmail, String newPassword){
        String sql = "UPDATE users SET (name, age, gender, genderPreference, minAge, maxAge, zip, email, password) = (:name, :age, :gender, :genderPreference, :minAge, :maxAge, :zip, :email, :password ) WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", newName)
                    .addParameter("age", newAge)
                    .addParameter("gender", newGender)
                    .addParameter("genderPreference", newGenderPreference)
                    .addParameter("minAge", newMinAge)
                    .addParameter("maxAge", newMaxAge)
                    .addParameter("zip", newZip)
                    .addParameter("email", newEmail)
                    .addParameter("password", newPassword)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
