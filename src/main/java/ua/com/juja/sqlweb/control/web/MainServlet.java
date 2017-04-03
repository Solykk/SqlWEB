package ua.com.juja.sqlweb.control.web;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.model.Table;
import ua.com.juja.sqlweb.service.BackEndTie;
import ua.com.juja.sqlweb.service.BackEndTieImpl;
import ua.com.juja.sqlweb.service.SettingsHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

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
            req.getSession().removeAttribute("count");
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
            req.getSession().removeAttribute("count");
            req.getRequestDispatcher("columns.jsp").forward(req, resp);
        }else if (action.startsWith("/TableType")){
            req.getSession().removeAttribute("table");
            req.getSession().removeAttribute("count");
            req.getRequestDispatcher("tabletype.jsp").forward(req, resp);
        }else if (action.startsWith("/ColumnType")){
            req.getSession().removeAttribute("table");
            req.getSession().removeAttribute("count");
            req.getRequestDispatcher("columntype.jsp").forward(req, resp);
        }else if (action.startsWith("/Find")){
            req.getSession().removeAttribute("table");
            req.getSession().removeAttribute("count");
            req.getRequestDispatcher("find.jsp").forward(req, resp);
        }else if (action.startsWith("/FileTable")){
            req.getSession().removeAttribute("count");
            req.getRequestDispatcher("filetable.jsp").forward(req, resp);
        }else if (action.startsWith("/FSettings")){
            req.getRequestDispatcher("findsettings.jsp").forward(req, resp);
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
            req.getSession().removeAttribute("count");
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

        if (action.startsWith("/FSettings")) {

            if(req.getParameter("add") != null){
                Integer counter = 2;
                if(req.getSession().getAttribute("count") != null) {
                    counter += (Integer) req.getSession().getAttribute("count");
                }
                req.getSession().setAttribute("count", counter);
                Integer count = (Integer) req.getSession().getAttribute("count");
                int[] array = new int [count];
                for (int i = 0; i < array.length; i++){
                    array[i] = i;
                }
                req.setAttribute("inputVal", array);
                req.getRequestDispatcher("findsettings.jsp").forward(req, resp);
                return;
            }

            String tableName = req.getParameter("TableName");
            ArrayList<String[]> settings = new ArrayList<>();
            String[] array = req.getParameterValues("settings[]");
            for (int i = 0; i < array.length; ) {
                settings.add(new String[]{array[i], array[i + 1]});
                i += 2;
            }

            try {
                Table table = backEndTie.findSettings((DatabaseManager) req.getSession().getAttribute("manager"), tableName, settings);
                req.getSession().setAttribute("table", table);
                req.setAttribute("table", table);
                req.getSession().removeAttribute("count");
                req.getRequestDispatcher("findsettings.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }

        if (action.startsWith("/Drop")) {
            String tableName = req.getParameter("TableName");

            try {
                backEndTie.drop((DatabaseManager) req.getSession().getAttribute("manager"), tableName);
                resp.sendRedirect(resp.encodeRedirectURL("drop"));
            } catch (Exception e) {
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }

        if (action.startsWith("/Clear")) {
            String tableName = req.getParameter("TableName");

            try {
                backEndTie.clear((DatabaseManager) req.getSession().getAttribute("manager"), tableName);
                resp.sendRedirect(resp.encodeRedirectURL("clear"));
            } catch (Exception e) {
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }

        if (action.startsWith("/Create")) {

            if(req.getParameter("add") != null){
                Integer counter = 1;
                if(req.getSession().getAttribute("count") != null) {
                    counter += (Integer) req.getSession().getAttribute("count");
                }
                req.getSession().setAttribute("count", counter);
                Integer count = (Integer) req.getSession().getAttribute("count");
                ArrayList<Integer> array = new ArrayList<>();
                for (int i = 0; i < count; i++){
                    array.add(i);
                }
                req.setAttribute("inputVal", array);
                req.getRequestDispatcher("create.jsp").forward(req, resp);
                return;
            }

            String tableName = req.getParameter("TableName");
            String columnNamePK = null;
            String[] arraySt = req.getParameterValues("settings[]");
            String[] arrayN = req.getParameterValues("nullable[]");
            Integer arrayPk = Integer.valueOf(req.getParameter("pk"));
            if(arrayPk != null){
                columnNamePK = arraySt[arrayPk];
            }

            Long startWith = null;
            if(req.getParameter("SeqTrue").equals("on")) {
                if(req.getParameter("StartWith") != null) {
                    startWith = Long.valueOf(req.getParameter("StartWith"));
                } else {
                    startWith = 1l;
                }
            }

            ArrayList<String> settings = new ArrayList<>();
            for (int i = 0, k = 0; i < arraySt.length;) {
                String temp = arraySt[i] + " " + arraySt[i + 1];
                if((i != 0 && Integer.valueOf(arrayN[k]) == i/2)||(i == 0 && Integer.valueOf(arrayN[k]) == 0)){
                    temp += " NULL";
                    k++;
                } else {
                    temp += " NOT NULL";
                }
                settings.add(temp);
                i += 2;
            }

            try {
                backEndTie.create((DatabaseManager) req.getSession().getAttribute("manager"), tableName, settings, columnNamePK, startWith);
                req.getSession().removeAttribute("count");
                req.getRequestDispatcher("create.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }

        if (action.startsWith("/Insert")) {

            if(req.getParameter("add") != null){
                Integer counter = 2;
                if(req.getSession().getAttribute("count") != null) {
                    counter += (Integer) req.getSession().getAttribute("count");
                }
                req.getSession().setAttribute("count", counter);
                Integer count = (Integer) req.getSession().getAttribute("count");
                int[] array = new int [count];
                for (int i = 0; i < array.length; i++){
                    array[i] = i;
                }
                req.setAttribute("inputVal", array);
                req.getRequestDispatcher("insert.jsp").forward(req, resp);
                return;
            }

            SettingsHelper settingsHelper = new SettingsHelper();
            String columnNamePK = req.getParameter("ColumnNamePK");
            String[] array = req.getParameterValues("settings[]");
            ArrayList<String[]> settings = new ArrayList<>();
            boolean isKey = false;
            if(req.getParameter("isKey") != null){
                isKey = true;
                settings.add(new String[]{columnNamePK, ""});
            }

            settings = settingsHelper.addSettings(array, settings);
            String tableName = req.getParameter("TableName");

            try {
                backEndTie.insert((DatabaseManager) req.getSession().getAttribute("manager"), tableName, settings, isKey);
                settings.remove(0);
                Table table = backEndTie.findSettings((DatabaseManager) req.getSession().getAttribute("manager"), tableName, settings);
                req.getSession().setAttribute("table", table);
                req.setAttribute("table", table);
                req.getSession().removeAttribute("count");
                req.getRequestDispatcher("insert.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }

        if (action.startsWith("/Delete")) {

            if(req.getParameter("add") != null){
                Integer counter = 2;
                if(req.getSession().getAttribute("count") != null) {
                    counter += (Integer) req.getSession().getAttribute("count");
                }
                req.getSession().setAttribute("count", counter);
                Integer count = (Integer) req.getSession().getAttribute("count");
                int[] array = new int [count];
                for (int i = 0; i < array.length; i++){
                    array[i] = i;
                }
                req.setAttribute("inputVal", array);
                req.getRequestDispatcher("delete.jsp").forward(req, resp);
                return;
            }

            String[] array = req.getParameterValues("settings[]");
            String tableName = req.getParameter("TableName");
            ArrayList<String[]> settings = new ArrayList<>();
            for (int i = 0; i < array.length; ) {
                settings.add(new String[]{array[i], array[i + 1]});
                i += 2;
            }

            try {
                backEndTie.delete((DatabaseManager) req.getSession().getAttribute("manager"), tableName, settings);
                req.getSession().removeAttribute("count");
                req.getRequestDispatcher("delete.jsp").forward(req, resp);
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
