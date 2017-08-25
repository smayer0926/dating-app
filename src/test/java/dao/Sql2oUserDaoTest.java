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

//    @Test
//    public void update_EvaluateIfNameChanged_Whataburger() throws Exception {
//        User test1 = setupTestUser();
//        userDao.add(test1);
//        userDao.update(1,"Whataburger","sdf","32421", "423-123-3252", "fdf", "sdfds", "sdf");
//        User foundRestaurant = userDao.findById(1);
//        assertEquals("Whataburger", foundRestaurant.getName());
//    }

//    @Test
//    public void deleteingFoodTypeAlsoUpdatesJoinTable() throws Exception {
//        Question testQuestion  = new Question("Seafood");
//        questionDao.add(testQuestion);
//
//        Question testFoodtype2  = new Question("Italian");
//        questionDao.add(testQuestion);
//
//        User testUser = setupTestUser();
//        userDao.add(testUser);
//
//        User altRestaurant = setupTestUser2();
//        userDao.add(altRestaurant);
//
//        questionDao.addFoodTypeToRestaurant(testQuestion,testUser);
//        questionDao.addFoodTypeToRestaurant(testFoodtype2, testUser);
//
//        questionDao.deleteById(testQuestion.getId());
//        assertEquals(0, questionDao.getAllRestaurantsForAFoodtype(testQuestion.getId()).size());
//    }

//    @Test
//    public void getAllFoodtypesForARestaurantReturnsFoodtypesCorrectly() throws Exception {
//        Question testQuestion  = new Question("Seafood");
//        questionDao.add(testQuestion);
//
//        Question otherFoodtype  = new Question("Bar Food");
//        questionDao.add(otherFoodtype);
//
//        User testUser = setupTestUser();
//        userDao.add(testUser);
//        userDao.addUserToQuestion(testUser,testQuestion);
//        userDao.addUserToQuestion(testUser,otherFoodtype);
//
//        Question[] foodtypes = {testQuestion, otherFoodtype}; //oh hi what is this?
//
//        assertEquals(userDao.getAllFoodtypesForARestaurant(testUser.getId()), Arrays.asList(foodtypes));
//    }

    //helper method
    public static User setupTestUser (){
        return new User("Trevor Gill", 30, "male", 24, 32);
    }
    public static User setupTestUser2 (){
        return new User("Stuart Gill", 34, "male", 26, 35);
    }

}