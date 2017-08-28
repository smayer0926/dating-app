import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.*;
import exceptions.ApiException;
import models.DateReview;
import models.Question;
import models.QuestionOption;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

public class App {
    public static void main(String[] args) {
        Sql2oUserDao userDao;
        Sql2oQuestionDao questionDao;
        Sql2oDateReviewDao dateReviewDao;
        Sql2oQuestionOptionDao questionOptionDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/datingapp8;INIT=RUNSCRIPT from 'classpath:db/create.sql'"; //check me!

        Sql2o sql2o = new Sql2o(connectionString, "", "");
        userDao = new Sql2oUserDao(sql2o);
        questionDao = new Sql2oQuestionDao(sql2o);
        dateReviewDao = new Sql2oDateReviewDao(sql2o);
        questionOptionDao = new Sql2oQuestionOptionDao(sql2o);
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
                throw new ApiException(404, String.format("No user with the id: %s exists", req.params("id")));
            }
            return gson.toJson(foundUser);
        });

        //LOAD FORM TO ADD NEW QUESTION
        get("/questions/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "question-form.hbs");
        }, new HandlebarsTemplateEngine());

        //CREATE QUESTION
        post("/questions/new", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String prompt = req.queryParams("questionPrompt");
            Question question = new Question(prompt);
            int questionId = question.getId();
            question.setId(questionId);
            questionDao.add(question);
            String optionString1 = req.queryParams("questionChoice1");
            String optionString2 = req.queryParams("questionChoice2");
            String optionString3 = req.queryParams("questionChoice3");
            String optionString4 = req.queryParams("questionChoice4");
            QuestionOption option1 = new QuestionOption(optionString1,questionId);
            QuestionOption option2 = new QuestionOption(optionString2,questionId);
            QuestionOption option3 = new QuestionOption(optionString3,questionId);
            QuestionOption option4 = new QuestionOption(optionString4,questionId);
            questionOptionDao.add(option1);
            questionOptionDao.add(option2);
            questionOptionDao.add(option3);
            questionOptionDao.add(option4);
            res.redirect("/questions/new");
            return null;
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
                throw new ApiException(404, String.format("No question with the id: %s exists", req.params("id")));
            }
            return gson.toJson(foundQuestion);
        });

        //Create joiner table record
        post("users/:id/questions/:questionid", "application/json", (req,res)->{
            User user = userDao.findById(Integer.parseInt(req.params("id")));
            Question question = questionDao.findById(Integer.parseInt(req.params("questionid")));
            questionDao.addQuestionToUser(user,question);
            res.status(201);
            return gson.toJson(questionDao.getAllUsersThatAnsweredQuestion(Integer.parseInt(req.params("questionid"))));
        });

        //Get ALL questions (temporary-testing)
        get("users/:id/questions", (req, res) -> {

            List<Question> foundQuestions = questionDao.getAll();
            List<QuestionOption> foundQuestionOptions = questionOptionDao.getAll();


            return gson.toJson(foundQuestions);
        });

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

        //Display all users that answered a question
        get("questions/:id/users", "application/json", (req,res)->{
            res.type("application/json");
            int questionId = Integer.parseInt(req.params("id"));
            List<User> foundUsers = questionDao.getAllUsersThatAnsweredQuestion(questionId);
            if (foundUsers.size() == 0){
                throw new ApiException(404, "No users have answered this question");
            }
            return gson.toJson(foundUsers);
        });

        //CREATE DATE REVIEW
        post("/users/:id/reviewuser/:dateid/new", "application/json", (req, res) -> {
            int userId = Integer.parseInt(req.params("id"));
            int dateUserId = Integer.parseInt(req.params("dateid"));
            DateReview dateReview = gson.fromJson(req.body(), DateReview.class);
            dateReview.setUserId(userId);
            dateReview.setDateUserId(dateUserId);
            dateReviewDao.add(dateReview);
            res.status(201);
            return gson.toJson(dateReview);
        });

        //READ ALL DATE REVIEWS
        get("/datereviews", "application/json", (req, res) -> {
            return gson.toJson(dateReviewDao.getAll());
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



    }
}
