import com.google.gson.Gson;
import dao.Sql2oQuestionDao;
import dao.Sql2oUserDao;
import models.Question;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static spark.Spark.get;
import static spark.Spark.post;

public class App {
    public static void main(String[] args) {
        Sql2oUserDao userDao;
        Sql2oQuestionDao questionDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/datingapp4;INIT=RUNSCRIPT from 'classpath:db/create.sql'"; //check me!

        Sql2o sql2o = new Sql2o(connectionString, "", "");
        userDao = new Sql2oUserDao(sql2o);
        questionDao = new Sql2oQuestionDao(sql2o);
        conn = sql2o.open();

        //CREATE USER
        post("/users/new", "application/json", (req, res) -> {
            User user = gson.fromJson(req.body(), User.class);
            userDao.add(user);
            res.status(201);
            return gson.toJson(user);
        });

        //READ ALL USERS
        get("/users", "application/json", (req, res) -> {
            return gson.toJson(userDao.getAll());
        });

        //READ SPECIFIC USER
        get("/users/:id", "application/json", (req, res) -> {
            res.type("application/json");
            int userId = Integer.parseInt(req.params("id"));
            User foundUser = userDao.findById(userId);
            if (foundUser == null){
                throw new RuntimeException(String.format("No user with the id: %s exists", req.params("id")));
            }
            return gson.toJson(foundUser);
        });

        //CREATE QUESTION
        post("/questions/new", "application/json", (req, res) -> {
            Question question = gson.fromJson(req.body(), Question.class);
            questionDao.add(question);
            res.status(201);
            return gson.toJson(question);
        });

        //READ ALL QUESTIONS
        get("/questions", "application/json", (req, res) -> {
            return gson.toJson(questionDao.getAll());
        });

        //READ SPECIFIC QUESTION
        get("/questions/:id", "application/json", (req, res) -> {
            res.type("application/json");
            int questionId = Integer.parseInt(req.params("id"));
            Question foundQuestion = questionDao.findById(questionId);
            if (foundQuestion == null){
                throw new RuntimeException(String.format("No question with the id: %s exists", req.params("id")));
            }
            return gson.toJson(foundQuestion);
        });

    }
}
