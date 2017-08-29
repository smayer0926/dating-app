import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.*;

import dao.Sql2oQuestionDao;
import dao.Sql2oUserDao;

import exceptions.ApiException;
import models.Question;
import models.QuestionOption;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        Sql2oUserDao userDao;
        Sql2oQuestionDao questionDao;
        Sql2oQuestionOptionDao questionOptionDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/datingapp9;INIT=RUNSCRIPT from 'classpath:db/create.sql'"; //check me!

        Sql2o sql2o = new Sql2o(connectionString, "", "");
        userDao = new Sql2oUserDao(sql2o);
        questionDao = new Sql2oQuestionDao(sql2o);
        questionOptionDao = new Sql2oQuestionOptionDao(sql2o);
        conn = sql2o.open();


        //CREATE USER
        post("/users/new", "application/json", (req, res) -> {
            User user = gson.fromJson(req.body(), User.class);
            userDao.add(user);
            res.status(201);
            return gson.toJson(user);
        });

        //READ ALL potential matches
        get("/users/:id/matches", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            User user = userDao.findById(Integer.parseInt(req.params("id")));
            int minAge = user.getMatchMinAge();
            int maxAge = user.getMatchMaxAge();
            List<User> matches = userDao.getAllMatches(minAge, maxAge);
            model.put("matches", matches);
            return new ModelAndView(model, "matched-users.hbs");
        }, new HandlebarsTemplateEngine());


//        //READ SPECIFIC USER
//        get("/users/:id", "application/json", (req, res) -> {
//            res.type("application/json");
//            int userId = Integer.parseInt(req.params("id"));
//            User foundUser = userDao.findById(userId);
//            if (foundUser == null){
//                throw new ApiException(404, String.format("No user with the id: %s exists", req.params("id")));
//            }
//            return gson.toJson(foundUser);
//        });


        //LOAD FORM TO ADD NEW QUESTION
        get("/questions/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "question-form.hbs");
        }, new HandlebarsTemplateEngine());

        //CREATE QUESTION
        post("/questions/new", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String prompt = req.queryParams("questionPrompt");
            String choice1 = req.queryParams("choice1");
            String choice2 = req.queryParams("choice2");
            String choice3 = req.queryParams("choice3");
            String choice4 = req.queryParams("choice4");
            Question question = new Question(prompt,choice1, choice2, choice3, choice4);
            int questionId = question.getId();
            question.setId(questionId);
            questionDao.add(question);
            res.redirect("/questions/new");
            return null;
        });

        //Create QUESTION ANSWER from users response to question
        post("users/:userId/questions/:id", (req,res)->{
            User user = userDao.findById(Integer.parseInt(req.params("userId")));
            String questionId = req.params(":id");
            Question question = questionDao.findById(Integer.parseInt(req.params("id")));
            String response = req.queryParams("responseTo"+ questionId);
            String acceptableResponse = Arrays.toString(req.queryParamsValues("acceptable" + questionId));
            questionDao.addQuestionToUser(user,question);
            res.status(201);
            return gson.toJson(questionDao.getAllUsersThatAnsweredQuestion(Integer.parseInt(req.params("questionid"))));
        });

        //display index
        get("/index", (req,res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //display ALL questions
        get("/questions", (req,res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Question> foundQuestions = questionDao.getAll();
            model.put("foundquestions", foundQuestions);
            return new ModelAndView(model, "questions.hbs");
        }, new HandlebarsTemplateEngine());

        //READ ALL QUESTIONS ANSWERED BY SPECIFIC USER
        get("users/:id/questions", (req, res) -> {
            int userId = Integer.parseInt(req.params("id"));
            List<Question> foundQuestions = userDao.getAllQuestionsAnsweredByUser(userId);
            if (userDao.countNumberOfUserIdMatches(userId) == 0){
                throw new ApiException(404, "This user doesn't exist");
            }
            if (foundQuestions.size() == 0){
                throw new ApiException(404, "This user hasn't answered any questions");
            }
            return gson.toJson(foundQuestions);
        });






        exception(ApiException.class, (exc, req, res) -> {
            ApiException err = (ApiException) exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json"); //after does not run in case of an exception.
            res.status(err.getStatusCode()); //set the status
            res.body(gson.toJson(jsonMap));  //set the output.
        });



        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<User> users = userDao.getAll();
            model.put("users", users);
            return new ModelAndView(model, "index2.hbs");
        }, new HandlebarsTemplateEngine());

        //show new user registration form
        get("/users/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<User> users = userDao.getAll();
            model.put("users", users);
            return new ModelAndView(model, "user-registration-form.hbs"); //new
        }, new HandlebarsTemplateEngine());

        //process new user form
        post("/users/new", (request, response) -> { //new
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("inputName");
            int age = Integer.parseInt(request.queryParams("inputAge"));
            String gender = request.queryParams("gender");
            String genderPreference = request.queryParams("genderPreference");
            int minAge = Integer.parseInt(request.queryParams("inputMinimumAge"));
            int maxAge = Integer.parseInt(request.queryParams("inputMaximumAge"));
            String zip = request.queryParams("inputZip");
            String email = request.queryParams("inputEmailAddress");
            String password = request.queryParams("inputPassword");
            User newUser = new User(name, age, gender, genderPreference, minAge, maxAge, zip, email, password);
            userDao.add(newUser);
            List<User> users = userDao.getAll();
            model.put("users", users);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        //show new user registration form
        get("/users/login", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<User> users = userDao.getAll();
            model.put("users", users);
            return new ModelAndView(model, "user-login.hbs"); //new
        }, new HandlebarsTemplateEngine());



    }
}
