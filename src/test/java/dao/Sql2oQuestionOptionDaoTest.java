package dao;

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
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        questionOptionDao = new Sql2oQuestionOptionDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    @Test
    public void addingQuestionOptions() throws Exception {
        QuestionOption questionOption = setupNew();
        int originalQuestionId = questionOption.getId();
        questionOptionDao.add(questionOption);
        assertNotEquals(originalQuestionId, questionOption.getId());
    }


    //Helpers
    public QuestionOption setupNew(){
        return  new QuestionOption ("none",1);
    }
}