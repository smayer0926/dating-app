package dao;

import models.Question;
import models.QuestionOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


import static org.junit.Assert.*;

/**
 * Created by Guest on 8/28/17.
 */
public class Sql2oQuestionOptionDaoTest {
private Sql2oQuestionOptionDao questionOptionDao;
private Sql2oQuestionDao questionDao;

    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        questionOptionDao = new Sql2oQuestionOptionDao(sql2o);
        questionDao = new Sql2oQuestionDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    @Test
    public void addingQuestionOptions() throws Exception {
        QuestionOption questionOption = setupNew();
        int questionOptionId = questionOption.getId();
        questionOptionDao.add(questionOption);
        assertNotEquals(questionOptionId, questionOption.getId());
    }
    @Test
    public void getAllForSpecificQuestion_ReturnAllQuestionResponsesForQuestion_2() throws Exception {
        Question testQuestion = setupTestQuestion();
        QuestionOption questionOption = setupNew();
        QuestionOption questionOption2 = setupNew2();
        questionDao.add(testQuestion);
        questionOptionDao.add(questionOption);
        questionOptionDao.add(questionOption2);
        int questionOptionId = questionOption.getId();
        assertEquals(2, questionOptionDao.getAllForSpecificQuestion(1).size());
    }


    //Helpers
    public QuestionOption setupNew(){
        return  new QuestionOption ("Flight",1);
    }
    public QuestionOption setupNew2(){
        return  new QuestionOption ("Invisibility",1);
    }
    public static Question setupTestQuestion(){
        return new Question("Flight or invisibility?");
    }
    public static Question setupTestQuestion2 (){
        return new Question("Snickers or Twix?");
    }
}