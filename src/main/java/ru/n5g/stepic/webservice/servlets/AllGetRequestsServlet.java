package ru.n5g.stepic.webservice.servlets;

import ru.n5g.stepic.webservice.model.HttpRequestInfo;
import ru.n5g.stepic.webservice.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static javax.servlet.http.HttpServletResponse.SC_OK;

/**
 * @author Gleb Belyaev
 * @version 1.0
 * @since 22.12.15
 */
public class AllGetRequestsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpRequestInfo httpRequestInfo = new HttpRequestInfo();
        httpRequestInfo.setMessage("Hello World ");
        httpRequestInfo.setMethod(req.getMethod());
        httpRequestInfo.setURL(req.getRequestURL().toString());
        httpRequestInfo.setPathInfo(req.getPathInfo());
        httpRequestInfo.setSessionId(req.getSession().getId());
        httpRequestInfo.setParameters(req.getParameterMap().toString());

        Map<String, Object> pageVariables = new HashMap<String, Object>();
        pageVariables.put("httpRequestInfo", httpRequestInfo);

        resp.getWriter().println(PageGenerator.getInstance().getPage("page.ftl", pageVariables));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(SC_OK);
    }
}
