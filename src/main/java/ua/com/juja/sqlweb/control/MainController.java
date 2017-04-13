package ua.com.juja.sqlweb.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.model.Table;
import ua.com.juja.sqlweb.service.BackEndTie;
import ua.com.juja.sqlweb.service.SettingsHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private BackEndTie backEndTie;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest req) {
        req.setAttribute("items", backEndTie.getCommandsList());
        req.getSession().removeAttribute("table");
        req.getSession().removeAttribute("count");
        return "index";
    }

    @RequestMapping(value = "/connect", method = RequestMethod.GET)
    public String connect() {
        return "connect";
    }

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public String connectP(HttpServletRequest req, HttpServletResponse resp) {
        String ipAddress = req.getParameter("ipAddress");
        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            DatabaseManager manager = backEndTie.connect(ipAddress, userName, password);
            req.getSession().setAttribute("manager", manager);
            resp.sendRedirect(resp.encodeRedirectURL("index"));
            return "index";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/Tables", method = RequestMethod.GET)
    public String tables(HttpServletRequest req) {
        try {
            Table table = backEndTie.tables((DatabaseManager) req.getSession().getAttribute("manager"));
            req.setAttribute("table", table);
            return "tables";
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/Columns", method = RequestMethod.GET)
    public String columns(HttpServletRequest req) {
        req.getSession().removeAttribute("table");
        req.getSession().removeAttribute("count");
        return "columns";
    }

    @RequestMapping(value = "/columns", method = RequestMethod.POST)
    public String columnsP(HttpServletRequest req, HttpServletResponse resp) {
            String tableName = req.getParameter("TableName");

        try {
            Table table = backEndTie.columns((DatabaseManager) req.getSession().getAttribute("manager"), tableName);
            req.getSession().setAttribute("table", table);
            req.setAttribute("table", table);
            return "columns";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/TableType", method = RequestMethod.GET)
    public String tableType(HttpServletRequest req) {
        req.getSession().removeAttribute("table");
        req.getSession().removeAttribute("count");
        return "tabletype";
    }

    @RequestMapping(value = "/tabletype", method = RequestMethod.POST)
    public String tableTypeP(HttpServletRequest req, HttpServletResponse resp) {
        String tableName = req.getParameter("TableName");

        try {
            Table table = backEndTie.tableType((DatabaseManager) req.getSession().getAttribute("manager"), tableName);
            req.getSession().setAttribute("table", table);
            req.setAttribute("table", table);
            return "tabletype";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/ColumnType", method = RequestMethod.GET)
    public String columnType(HttpServletRequest req) {
        req.getSession().removeAttribute("table");
        req.getSession().removeAttribute("count");
        return "columntype";
    }

    @RequestMapping(value = "/columntype", method = RequestMethod.POST)
    public String columnTypeP(HttpServletRequest req, HttpServletResponse resp) {
        String tableName = req.getParameter("TableName");
        String columnName = req.getParameter("ColumnName");

        try {
            Table table = backEndTie.columnType((DatabaseManager) req.getSession().getAttribute("manager"), tableName, columnName);
            req.getSession().setAttribute("table", table);
            req.setAttribute("table", table);
            return "columntype";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/Find", method = RequestMethod.GET)
    public String find(HttpServletRequest req) {
        req.getSession().removeAttribute("table");
        req.getSession().removeAttribute("count");
        return "find";
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public String findP(HttpServletRequest req, HttpServletResponse resp){
        String tableName = req.getParameter("TableName");

        try {
            Table table = backEndTie.find((DatabaseManager) req.getSession().getAttribute("manager"), tableName);
            req.getSession().setAttribute("table", table);
            req.setAttribute("table", table);
//            req.getRequestDispatcher("find.jsp").forward(req, resp);
            return "find";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/Filetable", method = RequestMethod.GET)
    public String fileTable(HttpServletRequest req) {
        req.getSession().removeAttribute("table");
        req.getSession().removeAttribute("count");
        return "filetable";
    }

    @RequestMapping(value = "/filetable", method = RequestMethod.POST)
    public String fileTableP(HttpServletRequest req, HttpServletResponse resp) {
        String fileName = req.getParameter("FileName");
        String absolutePath = req.getParameter("AbsolutePath");
        Table table = (Table) req.getSession().getAttribute("table");

        try {
            backEndTie.fileTable(fileName, table, absolutePath);
            req.getSession().removeAttribute("table");
            return "find";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/Fsettings", method = RequestMethod.GET)
    public String findSettings(HttpServletRequest req) {
        return "findsettings";
    }

    @RequestMapping(value = "/filetable", method = RequestMethod.POST)
    public String findSettingsP(HttpServletRequest req, HttpServletResponse resp) {
        if(req.getParameter("add") != null){
            addInput(req, resp, 2);
            return "findsettings";
        }

        String tableName = req.getParameter("TableName");
        List<String[]> settings = new ArrayList<>();
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
            return "findsettings";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/Clear", method = RequestMethod.GET)
    public String clear(HttpServletRequest req) {
        return "clear";
    }

    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    public String clearP(HttpServletRequest req, HttpServletResponse resp) {
        String tableName = req.getParameter("TableName");
        try {
            backEndTie.clear((DatabaseManager) req.getSession().getAttribute("manager"), tableName);
            return "clear";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/Create", method = RequestMethod.GET)
    public String create(HttpServletRequest req) {
        return "create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createP(HttpServletRequest req, HttpServletResponse resp) {
        if(req.getParameter("add") != null){
            addInput(req, resp, 1);
            return "create";
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
            return "create";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest req) {
        return "delete";
    }

    @RequestMapping(value = "/Drop", method = RequestMethod.GET)
    public String drop(HttpServletRequest req) {
        return "drop";
    }

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    public String dropP(HttpServletRequest req, HttpServletResponse resp) {
        String tableName = req.getParameter("TableName");
        try {
            backEndTie.drop((DatabaseManager) req.getSession().getAttribute("manager"), tableName);
            return "drop";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/Insert", method = RequestMethod.GET)
    public String insert(HttpServletRequest req) {
        return "insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insertP(HttpServletRequest req, HttpServletResponse resp) {
        if(req.getParameter("add") != null){
            addInput(req, resp, 2);
            return "insert";
        }

        String tableName = req.getParameter("TableName");
        String columnNamePK = req.getParameter("ColumnNamePK");
        String[] array = req.getParameterValues("settings[]");
        SettingsHelper settingsHelper = backEndTie.getServices().getSettingsHelper();
        boolean isKey = false;

        List<String[]> settings = new ArrayList<>();
        if(req.getParameter("isKey") != null){
            isKey = true;
            settings.add(new String[]{columnNamePK, ""});
        }

        settings = settingsHelper.addSettings(array, settings);

        try {
            backEndTie.insert((DatabaseManager) req.getSession().getAttribute("manager"), tableName, settings, isKey);
            settings.remove(0);
            Table table = backEndTie.findSettings((DatabaseManager) req.getSession().getAttribute("manager"), tableName, settings);
            req.getSession().setAttribute("table", table);
            req.setAttribute("table", table);
            req.getSession().removeAttribute("count");
            return "insert";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(HttpServletRequest req) {
        return "update";
    }

    @RequestMapping(value = "/readquery", method = RequestMethod.GET)
    public String readQuery(HttpServletRequest req) {
        req.getSession().removeAttribute("count");
        req.getSession().removeAttribute("table");
        return "readquery";
    }

    @RequestMapping(value = "/ReadQuery", method = RequestMethod.POST)
    public String readQueryP(HttpServletRequest req, HttpServletResponse resp) {
        String readQuery = req.getParameter("readQuery");

        try {
            Table table = backEndTie.readQuery((DatabaseManager) req.getSession().getAttribute("manager"), readQuery);
            req.getSession().setAttribute("table", table);
            req.setAttribute("table", table);
            return "readquery";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }
    @RequestMapping(value = "/cudquery", method = RequestMethod.GET)
    public String cudQuery(HttpServletRequest req) {
        return "cudquery";
    }

    @RequestMapping(value = "/cudquery", method = RequestMethod.POST)
    public String cudQueryP(HttpServletRequest req, HttpServletResponse resp) {
        String cudQuery = req.getParameter("CudQuery");

        try {
            backEndTie.cudQuery((DatabaseManager) req.getSession().getAttribute("manager"), cudQuery);
            resp.sendRedirect(resp.encodeRedirectURL("index"));
            return "cudquery";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/error",  method = RequestMethod.GET)
    public String error(HttpServletRequest req) {
        return "error";
    }

    private void addInput(HttpServletRequest req, HttpServletResponse resp, Integer counter){
        if(req.getSession().getAttribute("count") != null) {
            counter += (Integer) req.getSession().getAttribute("count");
        }
        req.getSession().setAttribute("count", counter);
        Integer count = (Integer) req.getSession().getAttribute("count");
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i < count; i++){
                array.add(i);
        }
        req.setAttribute("inputVal", array);
    }
}
