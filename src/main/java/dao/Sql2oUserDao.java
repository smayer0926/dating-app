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
    public List<User> getAllMatches(int minAge, int maxAge){
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM users WHERE age >= :matchminage and age <= :matchmaxage")
                    .addParameter("matchminage", minAge)
                    .addParameter("matchmaxage", maxAge)
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
}
