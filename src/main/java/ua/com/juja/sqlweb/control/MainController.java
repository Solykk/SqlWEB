package ua.com.juja.sqlweb.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.model.Table;
import ua.com.juja.sqlweb.service.Services;
import ua.com.juja.sqlweb.service.SettingsHelper;
import ua.com.juja.sqlweb.service.TableToString;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private DatabaseManager manager;

    @Autowired
    private Services services;

    @Autowired
    private CommandsList commandsList;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest req) {
        if(manager == null || !manager.isConnect()){
            req.setAttribute("items", Arrays.asList(commandsList.getCommands().get(0)));
        } else {
            req.setAttribute("items", commandsList.getCommands());
        }
        req.getSession().removeAttribute("table");
        req.getSession().removeAttribute("count");
        return "index";
    }

    @RequestMapping(value = "/Connect", method = RequestMethod.GET)
    public String connect() {
        return "connect";
    }

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public String connectP(HttpServletRequest req) {
        String ipAddress = req.getParameter("ipAddress");
        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            manager.connect(ipAddress, userName, password);
            req.getSession().setAttribute("manager", manager);
            return "redirect:index";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/Tables", method = RequestMethod.GET)
    public String tables(HttpServletRequest req) {
        try {
            Table table = manager.getTableNames();
            req.getSession().setAttribute("table", table);
            return "tables";
        } catch (Exception e) {
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
    public String columnsP(HttpServletRequest req) {
            String tableName = req.getParameter("TableName");

        try {
            Table table = manager.getColumnNames(tableName);
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
    public String tableTypeP(HttpServletRequest req) {
        String tableName = req.getParameter("TableName");

        try {
            Table table = manager.getAllTypeColumns(tableName);
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

    @RequestMapping(value = "/columnType", method = RequestMethod.POST)
    public String columnTypeP(HttpServletRequest req) {
        String tableName = req.getParameter("TableName");
        String columnName = req.getParameter("ColumnName");

        try {
            Table table = manager.getTypeColumn(tableName, columnName);
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
    public String findP(HttpServletRequest req){
        String tableName = req.getParameter("TableName");

        try {
            Table table = manager.read(tableName);
            req.getSession().setAttribute("table", table);
            req.setAttribute("table", table);
            return "find";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/FileTable", method = RequestMethod.GET)
    public String fileTable(HttpServletRequest req) {
        req.getSession().removeAttribute("count");
        return "filetable";
    }

    @RequestMapping(value = "/fileTable", method = RequestMethod.POST)
    public String fileTableP(HttpServletRequest req) {
        String fileName = req.getParameter("FileName");
        String absolutePath = req.getParameter("AbsolutePath");
        Table table = (Table) req.getSession().getAttribute("table");

        try {
            fileTable(fileName, table, absolutePath);
            req.getSession().removeAttribute("table");
            return "find";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/FSettings", method = RequestMethod.GET)
    public String findSettings() {
        return "findsettings";
    }

    @RequestMapping(value = "/fSettings", method = RequestMethod.POST)
    public String findSettingsP(HttpServletRequest req) {
        if(req.getParameter("add") != null){
            addInput(req, 2);
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
            Table table = manager.readSet(tableName, settings);
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
    public String clear() {
        return "clear";
    }

    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    public String clearP(HttpServletRequest req) {
        String tableName = req.getParameter("TableName");
        try {
            manager.clear(tableName);
            return "clear";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/Create", method = RequestMethod.GET)
    public String create() {
        return "create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createP(HttpServletRequest req) {
        if(req.getParameter("add") != null){
            addInput(req, 1);
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
                startWith = 1L;
            }
        }

        List<String> settings = new ArrayList<>();
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
            create(tableName, settings, columnNamePK, startWith);
            req.getSession().removeAttribute("count");
            return "create";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/Delete", method = RequestMethod.GET)
    public String delete() {
        return "delete";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteP(HttpServletRequest req) {
        if(req.getParameter("add") != null){
            addInput(req, 2);
            return "delete";
        }

        String[] array = req.getParameterValues("settings[]");
        String tableName = req.getParameter("TableName");
        List<String[]> settings = new ArrayList<>();
        for (int i = 0; i < array.length; ) {
            settings.add(new String[]{array[i], array[i + 1]});
            i += 2;
        }

        try {
            manager.delete(tableName,settings);
            req.getSession().removeAttribute("count");
            return "delete";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/Drop", method = RequestMethod.GET)
    public String drop() {
        return "drop";
    }

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    public String dropP(HttpServletRequest req) {
        String tableName = req.getParameter("TableName");
        try {
            manager.drop(tableName);
            return "drop";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/Insert", method = RequestMethod.GET)
    public String insert() {
        return "insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insertP(HttpServletRequest req) {
        if(req.getParameter("add") != null){
            addInput(req, 2);
            return "insert";
        }

        String tableName = req.getParameter("TableName");
        String columnNamePK = req.getParameter("ColumnNamePK");
        String[] array = req.getParameterValues("settings[]");
        SettingsHelper settingsHelper = services.getSettingsHelper();
        boolean isKey = false;

        List<String[]> settings = new ArrayList<>();
        if(req.getParameter("isKey") != null){
            isKey = true;
            settings.add(new String[]{columnNamePK, ""});
        }

        settings = settingsHelper.addSettings(array, settings);

        try {
            manager.insert(tableName, settings, isKey);
            settings.remove(0);
            Table table = manager.readSet(tableName, settings);
            req.getSession().setAttribute("table", table);
            req.setAttribute("table", table);
            req.getSession().removeAttribute("count");
            return "insert";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/Update", method = RequestMethod.GET)
    public String update() {
        return "update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateP(HttpServletRequest req) {
        if(req.getParameter("add") != null){
            addInput(req, 2);
            return "update";
        }

        String tableName = req.getParameter("TableName");
        String[] arrayFor = req.getParameterValues("settingsFor[]");
        List<String[]> forUpdate = new ArrayList<>();
        for (int i = 0; i < arrayFor.length; ) {
            forUpdate.add(new String[]{arrayFor[i], arrayFor[i + 1]});
            i += 2;
        }
        String[] arrayHow = req.getParameterValues("settingsHow[]");
        List<String[]> howUpdate  = new ArrayList<>();
        for (int i = 0; i < arrayHow .length; ) {
            howUpdate .add(new String[]{arrayHow [i], arrayHow [i + 1]});
            i += 2;
        }

        try {
            manager.update(tableName, howUpdate, forUpdate);
            Table table = manager.readSet(tableName, howUpdate);
            req.getSession().setAttribute("table", table);
            req.setAttribute("table", table);
            req.getSession().removeAttribute("count");
            return "update";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/ReadQuery", method = RequestMethod.GET)
    public String readQuery(HttpServletRequest req) {
        req.getSession().removeAttribute("count");
        req.getSession().removeAttribute("table");
        return "readquery";
    }

    @RequestMapping(value = "/readquery", method = RequestMethod.POST)
    public String readQueryP(HttpServletRequest req) {
        String readQuery = req.getParameter("readQuery");

        try {
            Table table = manager.readQuery(readQuery);
            req.getSession().setAttribute("table", table);
            req.setAttribute("table", table);
            return "readquery";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/CudQuery", method = RequestMethod.GET)
    public String cudQuery() {
        return "cudquery";
    }

    @RequestMapping(value = "/cudquery", method = RequestMethod.POST)
    public String cudQueryP(HttpServletRequest req) {
        String cudQuery = req.getParameter("CudQuery");

        try {
            manager.cudQuery(cudQuery);
            return "index";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/error",  method = RequestMethod.GET)
    public String error() {
        return "error";
    }

    private void addInput(HttpServletRequest req, Integer counter){
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

    private void fileTable(String fileName, Table table, String path) throws IOException {
        TableToString tTS = services.getTableToString();
        String tableToString = tTS.tableTS(table);
        File file = new File(path + fileName + ".txt");
        if (file.createNewFile()) {
            try (FileWriter writer = new FileWriter(path + fileName + ".txt")) {
                writer.write(tableToString);
                writer.flush();
            }
        }
    }

    private void create(String tableName, List<String> settings, String columnNamePK, Long startWith) throws SQLException {
        manager.createWithoutPK(tableName, settings);
        if(columnNamePK != null) {
            manager.createCreatePK(tableName, columnNamePK);
            if(startWith != null){
                manager.createSequencePK(tableName, startWith);
            }
        }
    }
}
