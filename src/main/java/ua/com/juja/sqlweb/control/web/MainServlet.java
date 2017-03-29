package ua.com.juja.sqlweb.control.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqURL = req.getRequestURI();
        String action = reqURL.substring(req.getContextPath().length(), reqURL.length());

        if (action.startsWith("/index")){
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else if (action.startsWith("/menu")){
            req.getRequestDispatcher("menu.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("help.jsp").forward(req, resp);
        }
    }
}
