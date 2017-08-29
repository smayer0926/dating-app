package dao;


import models.Authentication;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

public class Sql2oAuthenticationDao implements AuthenticationDao {
    private final Sql2o sql2o;

    public Sql2oAuthenticationDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Authentication authentication) {
        String sql = "INSERT INTO authentications (username, password) VALUES (:username, :password)";

        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("username", authentication.getUsername())
                    .addParameter("password", authentication.getPassword())
                    .addColumnMapping("USERNAME", "username")
                    .addColumnMapping("PASSWORD", "password")
                    .executeUpdate()
                    .getKey();
            authentication.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


}
