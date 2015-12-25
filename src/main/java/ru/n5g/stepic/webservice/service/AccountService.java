package ru.n5g.stepic.webservice.service;

import org.h2.jdbcx.JdbcDataSource;
import ru.n5g.stepic.webservice.model.UserProfile;

import java.sql.Connection;
import java.sql.Statement;

/**
 * @author Gleb Belyaev
 * @version 1.0
 * @since 24.12.15
 */
public class AccountService {

    private JdbcDataSource ds;

    public AccountService(JdbcDataSource ds) {

        this.ds = ds;
    }

    public void saveUser(UserProfile user) {
        if (isCorrectUser(user)) {
            return;
        }

        try (Connection con = ds.getConnection();
             Statement st = con.createStatement();) {

            st.execute("INSERT INTO USERS(LOGIN, PASSWORD) VALUES ('" + user.getLogin() + "', '" + user.getPassword() + "')");

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean isCorrectUser(UserProfile user) {
        try (Connection con = ds.getConnection();
             Statement st = con.createStatement();) {

            st.execute("SELECT count(*) as count FROM USERS WHERE LOGIN='" + user.getLogin() + "' AND PASSWORD='" + user.getPassword() + "'");
            return st.getResultSet().next() && st.getResultSet().getInt("count") > 1;

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
