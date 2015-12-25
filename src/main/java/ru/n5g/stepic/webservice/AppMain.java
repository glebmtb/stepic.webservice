package ru.n5g.stepic.webservice;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.h2.jdbcx.JdbcDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.n5g.stepic.webservice.service.AccountService;
import ru.n5g.stepic.webservice.servlets.AllGetRequestsServlet;
import ru.n5g.stepic.webservice.servlets.SignInServlet;
import ru.n5g.stepic.webservice.servlets.SignUpServlet;

import java.sql.Connection;
import java.sql.Statement;

import static org.eclipse.jetty.servlet.ServletContextHandler.SESSIONS;

/**
 * @author Gleb Belyaev
 * @version 1.0
 * @since 22.12.15
 */

public class AppMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppMain.class);

    public static void main(String[] args) throws Exception {

        AccountService accountService = new AccountService(createDS());

        ServletContextHandler context = new ServletContextHandler(SESSIONS);
        context.addServlet(new ServletHolder(new AllGetRequestsServlet()), "/*");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
        LOGGER.info("Server started");
        server.join();
    }

    private static JdbcDataSource createDS() throws Exception {
        Class.forName("org.h2.Driver");

        String url = "jdbc:h2:./h2db;MV_STORE=FALSE";
        String name = "test";
        String pass = "test";

        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL(url);
        ds.setUser(name);
        ds.setPassword(pass);

        try (Connection con = ds.getConnection(); Statement st = con.createStatement();) {
            String createTable = "CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT, login VARCHAR(256), password VARCHAR(256), PRIMARY KEY (id));";
            st.execute(createTable);
        }

        return ds;
    }
}
