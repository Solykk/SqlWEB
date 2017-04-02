package ua.com.juja.sqlweb.control.web;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.model.Table;
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

        if (action.startsWith("/index")){
            req.setAttribute("items", backEndTie.commandsList());
            req.getSession().removeAttribute("table");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else if (action.startsWith("/Connect")){
            req.getRequestDispatcher("connect.jsp").forward(req, resp);
        } else if (action.startsWith("/Tables")){
            try {
                Table table = backEndTie.tables((DatabaseManager) req.getSession().getAttribute("manager"));
                req.setAttribute("table", table);
                req.getRequestDispatcher("tables.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }else if (action.startsWith("/Columns")){
            req.getSession().removeAttribute("table");
            req.getRequestDispatcher("columns.jsp").forward(req, resp);
        }else if (action.startsWith("/TableType")){
            req.getSession().removeAttribute("table");
            req.getRequestDispatcher("tabletype.jsp").forward(req, resp);
        }else if (action.startsWith("/ColumnType")){
            req.getSession().removeAttribute("table");
            req.getRequestDispatcher("columntype.jsp").forward(req, resp);
        }else if (action.startsWith("/Find")){
            req.getSession().removeAttribute("table");
            req.getRequestDispatcher("find.jsp").forward(req, resp);
        }else if (action.startsWith("/FileTable")){
            req.getRequestDispatcher("filetable.jsp").forward(req, resp);
        }else if (action.startsWith("/FindSettings")){
            req.getRequestDispatcher("findSettings.jsp").forward(req, resp);
        }else if (action.startsWith("/Clear")){
            req.getRequestDispatcher("clear.jsp").forward(req, resp);
        }else if (action.startsWith("/Create")){
            req.getRequestDispatcher("create.jsp").forward(req, resp);
        }else if (action.startsWith("/Delete")){
            req.getRequestDispatcher("delete.jsp").forward(req, resp);
        }else if (action.startsWith("/Drop")){
            req.getRequestDispatcher("drop.jsp").forward(req, resp);
        }else if (action.startsWith("/Insert")){
            req.getRequestDispatcher("insert.jsp").forward(req, resp);
        }else if (action.startsWith("/Update")){
            req.getRequestDispatcher("update.jsp").forward(req, resp);
        }else if (action.startsWith("/ReadQuery")){
            req.getSession().removeAttribute("table");
            req.getRequestDispatcher("readquery.jsp").forward(req, resp);
        }else if (action.startsWith("/CudQuery")){
            req.getRequestDispatcher("cudquery.jsp").forward(req, resp);
        }else if (action.startsWith("/History")){
            req.getRequestDispatcher("history.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);

        if (action.startsWith("/Connect")) {
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

        if (action.startsWith("/CudQuery")) {
            String cudQuery = req.getParameter("CudQuery");

            try {
                backEndTie.cudQuery((DatabaseManager) req.getSession().getAttribute("manager"), cudQuery);
                resp.sendRedirect(resp.encodeRedirectURL("index"));
            } catch (Exception e) {
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }

        if (action.startsWith("/Find")) {
            String tableName = req.getParameter("TableName");

            try {
                Table table = backEndTie.find((DatabaseManager) req.getSession().getAttribute("manager"), tableName);
                req.getSession().setAttribute("table", table);
                req.setAttribute("table", table);
                req.getRequestDispatcher("find.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }

        if (action.startsWith("/Columns")) {
            String tableName = req.getParameter("TableName");

            try {
                Table table = backEndTie.columns((DatabaseManager) req.getSession().getAttribute("manager"), tableName);
                req.getSession().setAttribute("table", table);
                req.setAttribute("table", table);
                req.getRequestDispatcher("columns.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }

        if (action.startsWith("/TableType")) {
            String tableName = req.getParameter("TableName");

            try {
                Table table = backEndTie.tableType((DatabaseManager) req.getSession().getAttribute("manager"), tableName);
                req.getSession().setAttribute("table", table);
                req.setAttribute("table", table);
                req.getRequestDispatcher("tabletype.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }

        if (action.startsWith("/ColumnType")) {
            String tableName = req.getParameter("TableName");
            String columnName = req.getParameter("ColumnName");

            try {
                Table table = backEndTie.columnType((DatabaseManager) req.getSession().getAttribute("manager"), tableName, columnName);
                req.getSession().setAttribute("table", table);
                req.setAttribute("table", table);
                req.getRequestDispatcher("columntype.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }

        if (action.startsWith("/ReadQuery")) {
            String readQuery = req.getParameter("readQuery");

            try {
                Table table = backEndTie.readQuery((DatabaseManager) req.getSession().getAttribute("manager"), readQuery);
                req.getSession().setAttribute("table", table);
                req.setAttribute("table", table);
                req.getRequestDispatcher("readquery.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }

        if (action.startsWith("/FileTable")) {
            String fileName = req.getParameter("FileName");
            String absolutePath = req.getParameter("AbsolutePath");
            Table table = (Table) req.getSession().getAttribute("table");

            try {
                backEndTie.fileTable(fileName, table, absolutePath);
                req.getSession().removeAttribute("table");
                req.getRequestDispatcher("find.jsp").forward(req, resp);
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
