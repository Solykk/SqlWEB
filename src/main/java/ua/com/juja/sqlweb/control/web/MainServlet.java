package ua.com.juja.sqlweb.control.web;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.service.BackEndTie;
import ua.com.juja.sqlweb.service.BackEndTieImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {

    private BackEndTie backEndTie;

    @Override
    public void init() throws ServletException {
        super.init();
        backEndTie = new BackEndTieImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = getAction(req);

        DatabaseManager manager = (DatabaseManager) req.getSession().getAttribute("manager");

        if (action.startsWith("/index")){
            req.setAttribute("items", backEndTie.commandsList());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else if (action.startsWith("/connect")){
            req.getRequestDispatcher("connect.jsp").forward(req, resp);
        } else if (action.startsWith("/tables")){
            req.getRequestDispatcher("tables.jsp").forward(req, resp);
        }else if (action.startsWith("/columns")){
            req.getRequestDispatcher("columns.jsp").forward(req, resp);
        }else if (action.startsWith("/tableType")){
            req.getRequestDispatcher("tableType.jsp").forward(req, resp);
        }else if (action.startsWith("/columnType")){
            req.getRequestDispatcher("columnType.jsp").forward(req, resp);
        }else if (action.startsWith("/find")){
            req.getRequestDispatcher("find.jsp").forward(req, resp);
        }else if (action.startsWith("/fileTable")){
            req.getRequestDispatcher("fileTable.jsp").forward(req, resp);
        }else if (action.startsWith("/findSettings")){
            req.getRequestDispatcher("findSettings.jsp").forward(req, resp);
        }else if (action.startsWith("/clear")){
            req.getRequestDispatcher("clear.jsp").forward(req, resp);
        }else if (action.startsWith("/create")){
            req.getRequestDispatcher("create.jsp").forward(req, resp);
        }else if (action.startsWith("/delete")){
            req.getRequestDispatcher("delete.jsp").forward(req, resp);
        }else if (action.startsWith("/drop")){
            req.getRequestDispatcher("drop.jsp").forward(req, resp);
        }else if (action.startsWith("/insert")){
            req.getRequestDispatcher("insert.jsp").forward(req, resp);
        }else if (action.startsWith("/update")){
            req.getRequestDispatcher("update.jsp").forward(req, resp);
        }else if (action.startsWith("/readQuery")){
            req.getRequestDispatcher("readQuery.jsp").forward(req, resp);
        }else if (action.startsWith("/cudQuery")){
            req.getRequestDispatcher("cudQuery.jsp").forward(req, resp);
        }else if (action.startsWith("/history")){
            req.getRequestDispatcher("history.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);

        if (action.startsWith("/connect")) {
            String ipAddress = req.getParameter("ipAddress");
            String userName = req.getParameter("username");
            String password = req.getParameter("password");

            try {
                DatabaseManager manager = backEndTie.connect(ipAddress, userName, password);
                req.getSession().setAttribute("manager", manager);
                resp.sendRedirect(resp.encodeRedirectURL("index"));
            } catch (Exception e) {
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }
    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length(), requestURI.length());
    }
}
