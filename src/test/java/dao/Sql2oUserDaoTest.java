package dao;

import models.Question;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {
    private Sql2oUserDao userDao;
    private Sql2oQuestionDao questionDao;
    private Connection conn; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        userDao = new Sql2oUserDao(sql2o);
        questionDao = new Sql2oQuestionDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add_InstantiatesNewUser() throws Exception {
        User test1 = setupTestUser();
        assertTrue(test1 instanceof User);
    }

    @Test
    public void getAll_RetrieveAllUsers_2() throws Exception {
        User test1 = setupTestUser();
        User test2 = setupTestUser2();
        userDao.add(test1);
        userDao.add(test2);
        assertEquals(2,userDao.getAll().size());
    }

    @Test
    public void findById_FindASpecificUser_TrevorGill() throws Exception {
        User test1 = setupTestUser();
        userDao.add(test1);
        User foundUser = userDao.findById(1);
        assertEquals("Trevor Gill",foundUser.getName());
    }

    @Test
    public void getAllQuestionsAnsweredByUserReturnsCorrectly() throws Exception {
        Question testQuestion  = setupTestQuestion();
        questionDao.add(testQuestion);

        Question testQuestion2  = setupTestQuestion2();
        questionDao.add(testQuestion2);

        User testUser = setupTestUser();
        userDao.add(testUser);

        userDao.addUserToQuestion(testUser,testQuestion);
        userDao.addUserToQuestion(testUser,testQuestion2);

        Question[] questions = {testQuestion, testQuestion2};

        assertEquals(userDao.getAllQuestionsAnsweredByUser(testUser.getId()), Arrays.asList(questions));
    }
    @Test
    public void getAllMatchesReturnsCorrectly() throws Exception {
        User test1 = setupTestUser();
        User test2 = setupTestUser2();
        userDao.add(test1);
        userDao.add(test2);



        assertEquals(0 ,userDao.getAllMatches(1,24,50, "female").size());
    }

    //helper method
    public static User setupTestUser (){
        return new User("Trevor Gill", 30, "male", "female" , 24, 32, "97214", "ph@gmail.com", "smoothtalker");
    }
    public static User setupTestUser2 () {
        return new User("Stuart Gill", 34, "male", "female", 26, 35, "97456", "og@gmail.com", "lover");
    }
    public static Question setupTestQuestion(){
        return new Question("Flight or invisibility?","none","flight", "invisibility", "both", false, false, false, false);
    }
    public static Question setupTestQuestion2 (){
        return new Question("Snickers or Twix?", "none", "snickers", "twix", "both",false, false, false, false);
    }

}