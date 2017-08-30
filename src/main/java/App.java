import com.google.gson.Gson;
import dao.*;

import dao.Sql2oQuestionDao;
import dao.Sql2oUserDao;

import exceptions.ApiException;
import models.Answer;
import models.Question;
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
        Sql2oAnswerDao answerDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/datingapp9;INIT=RUNSCRIPT from 'classpath:db/create.sql'"; //check me!

        Sql2o sql2o = new Sql2o(connectionString, "", "");
        userDao = new Sql2oUserDao(sql2o);
        questionDao = new Sql2oQuestionDao(sql2o);
        answerDao = new Sql2oAnswerDao(sql2o);
        conn = sql2o.open();

        //display index
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        //READ ALL potential matches
        get("/users/:id/matches", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            User user = userDao.findById(Integer.parseInt(req.params("id")));
            int userId = Integer.parseInt(req.params("id"));
            int minAge = user.getMatchMinAge();
            int maxAge = user.getMatchMaxAge();
            String genderPreference = user.getGenderPreference();
            List<User> matches = userDao.getAllMatches(userId, minAge, maxAge, genderPreference);
            model.put("userId", userId);
            model.put("matches", matches);
            return new ModelAndView(model, "matched-users.hbs");
        }, new HandlebarsTemplateEngine());



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
            Question question = new Question(prompt, choice1, choice2, choice3, choice4, false, false, false, false,"");
            int questionId = question.getId();
            question.setId(questionId);
            questionDao.add(question);
            res.redirect("/questions/new");
            return null;
        });
        //display ALL questions
        get("/users/:id/questions", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            int userId = Integer.parseInt(req.params("id"));
            List<Question> foundQuestions = questionDao.getAll();
            model.put("foundquestions", foundQuestions);
            return new ModelAndView(model, "questions.hbs");
        }, new HandlebarsTemplateEngine());

        //Create ANSWER from users response to question
        post("users/:userId/questions/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            User user = userDao.findById(Integer.parseInt(req.params("userId")));
            int userId = Integer.parseInt(req.params("userId"));
            int questionId = Integer.parseInt(req.params(":id"));
            Question question = questionDao.findById(Integer.parseInt(req.params("id")));
            String answer = req.queryParams("responseTo" + questionId);
            String acceptableAnswer = Arrays.toString(req.queryParamsValues("acceptable" + questionId));
//            questionDao.addUsertoUsersWhoHaveAnsweredThisQuestion(userId,question);
            Answer answerObject = new Answer(userId, questionId, answer, acceptableAnswer);
            answerDao.add(answerObject);
            answerDao.setAnswerBooleans(question, answer);

            System.out.println(question.getAnswerIs1());
            System.out.println(question.isAnswerIs2());
            System.out.println(question.isAnswerIs3());
            System.out.println(question.isAnswerIs4());

            res.redirect("/users/" + userId + "/questions");
            return null;
        });

        //evaluate compatibility
        get("users/:userId/matches/:matchid", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int userId = Integer.parseInt(req.params("userId"));
            int matchId = Integer.parseInt(req.params("matchId"));
            User matchedUser = userDao.findById(matchId);
            List<Integer> questionIdsOfViewingUsersAnswers = answerDao.getQuestionIdsFromUsersAnsweredQuestions(userId);
            List<Answer> matchedUserAnswers = userDao.getAllAnswers(matchId);
            int compScore = userDao.evaluateCompatibility(questionIdsOfViewingUsersAnswers, matchedUserAnswers, userId);
            model.put("matchedUser",matchedUser);
            model.put("compScore",compScore);
            return new ModelAndView(model, "match-detail.hbs");
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
            String bio = request.queryParams("inputBio");
            User newUser = new User(name, age, gender, genderPreference, minAge, maxAge, zip, email, password, bio);
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
