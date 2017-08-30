package dao;

import models.Answer;
import models.Question;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.*;


public class Sql2oAnswerDaoTest {
    private Sql2oQuestionDao questionDao;
    private Sql2oUserDao userDao;
    private Sql2oAnswerDao answerDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        questionDao = new Sql2oQuestionDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        answerDao = new Sql2oAnswerDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add_InstantiatesNewQuestion() throws Exception {
        Question testQuestion1 = setupTestQuestion();
        User testUser1 = setupTestUser();
        Answer testAnswer1 = setupTestAnswer();
        assertTrue(testAnswer1 instanceof Answer);
    }

    @Test
    public void getAllAnswersForUser() throws Exception {
        Question testQuestion  = setupTestQuestion();
        questionDao.add(testQuestion);

        User testUser = setupTestUser();
        userDao.add(testUser);

        Answer testAnswer = setupTestAnswer();
        answerDao.add(testAnswer);
        Answer testAnswer2 = setupTestAnswer2();
        answerDao.add(testAnswer2);


        assertEquals(2,answerDao.getAllForAnswersSpecificUser(testUser.getId()).size());
    }

    @Test
    public void setAnswerBooleans_BooleansReturnCorrectValues() throws Exception {
        Question testQuestion  = setupTestQuestion();
        questionDao.add(testQuestion);

        User testUser = setupTestUser();
        userDao.add(testUser);

        Answer testAnswer = setupTestAnswer2();
        answerDao.add(testAnswer);

        answerDao.setAnswerBooleans(testQuestion, testAnswer.getAnswer());
        assertEquals(true,testQuestion.isAnswerIs3 ());
    }


    //helper method
    public static User setupTestUser (){
        return new User("Trevor Gill", 30, "male", "female" , 24, 32, "97214", "ph@gmail.com", "smoothtalker");
    }
    public static User setupTestUser2 (){
        return new User("Stuart Gill", 34, "male","female", 26, 35, "97456", "og@gmail.com", "lover");
    }
    public static Question setupTestQuestion(){
        return new Question("Flight or invisibility?","none","flight", "invisibility", "both", false, false, false, false , "");
    }
    public static Question setupTestQuestion2 (){
        return new Question("Snickers or Twix?", "none", "snickers", "twix", "both", false, false, false, false,"");
    }
    public static Answer setupTestAnswer(){
        return new Answer(1,1,"1","124");
    }
    public static Answer setupTestAnswer2(){
        return new Answer(1,2,"3","34");
    }

}