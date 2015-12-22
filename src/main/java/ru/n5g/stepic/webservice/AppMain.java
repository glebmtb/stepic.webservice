package ru.n5g.stepic.webservice;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.n5g.stepic.webservice.servlets.AllGetRequestsServlet;

import static org.eclipse.jetty.servlet.ServletContextHandler.SESSIONS;

/**
 * @author Gleb Belyaev
 * @version 1.0
 * @since 22.12.15
 */
public class AppMain {
    public static void main(String[] args) throws Exception {
        AllGetRequestsServlet allGetRequests = new AllGetRequestsServlet();

        ServletContextHandler context = new ServletContextHandler(SESSIONS);
        context.addServlet(new ServletHolder(allGetRequests), "/*");

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
        server.join();
    }
}
