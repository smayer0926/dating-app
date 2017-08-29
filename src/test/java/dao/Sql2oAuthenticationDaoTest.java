package dao;

import models.Authentication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

/**
 * Created by Guest on 8/29/17.
 */
public class Sql2oAuthenticationDaoTest {

    private Sql2oAuthenticationDao authenticationDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        authenticationDao = new Sql2oAuthenticationDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add_InstantiatesUsername() throws Exception {
        Authentication authentication = setupTestUsername();
        assertTrue(authentication instanceof Authentication);

    }

    public static Authentication setupTestUsername(){
        return new Authentication("shyamal", "sffvvfsfd");
    }

}