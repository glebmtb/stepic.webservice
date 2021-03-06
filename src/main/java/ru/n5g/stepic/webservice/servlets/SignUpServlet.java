package ru.n5g.stepic.webservice.servlets;

import ru.n5g.stepic.webservice.model.UserProfile;
import ru.n5g.stepic.webservice.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.n5g.stepic.webservice.servlets.ParamsConstant.LOGIN;
import static ru.n5g.stepic.webservice.servlets.ParamsConstant.PASSWORD;

/**
 * @author Gleb Belyaev
 * @version 1.0
 * @since 24.12.15
 */
public class SignUpServlet extends HttpServlet {
    private AccountService accountService;


    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(LOGIN);
        String pass = req.getParameter(PASSWORD);

        accountService.saveUser(new UserProfile(login, pass));
    }
}
