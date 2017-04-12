package ua.com.juja.sqlweb.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.model.Table;
import ua.com.juja.sqlweb.service.BackEndTie;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value = "/Connect", method = RequestMethod.GET)
    public String connect() {
        return "connect";
    }

    @RequestMapping(value = "/tables", method = RequestMethod.GET)
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

    @RequestMapping(value = "/TableType", method = RequestMethod.GET)
    public String tableType(HttpServletRequest req) {
        req.getSession().removeAttribute("table");
        req.getSession().removeAttribute("count");
        return "tabletype";
    }

    @RequestMapping(value = "/ColumnType", method = RequestMethod.GET)
    public String columnType(HttpServletRequest req) {
        req.getSession().removeAttribute("table");
        req.getSession().removeAttribute("count");
        return "columntype";
    }

    @RequestMapping(value = "/Find", method = RequestMethod.GET)
    public String find(HttpServletRequest req) {
        req.getSession().removeAttribute("table");
        req.getSession().removeAttribute("count");
        return "find";
    }

    @RequestMapping(value = "/FileTable", method = RequestMethod.GET)
    public String fileTable(HttpServletRequest req) {
        req.getSession().removeAttribute("table");
        req.getSession().removeAttribute("count");
        return "filetable";
    }

    @RequestMapping(value = "/FSettings", method = RequestMethod.GET)
    public String findSettings(HttpServletRequest req) {
        return "findsettings";
    }

    @RequestMapping(value = "/Clear", method = RequestMethod.GET)
    public String clear(HttpServletRequest req) {
        return "clear";
    }

    @RequestMapping(value = "/Create", method = RequestMethod.GET)
    public String create(HttpServletRequest req) {
        return "create";
    }

    @RequestMapping(value = "/Delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest req) {
        return "delete";
    }

    @RequestMapping(value = "/Drop", method = RequestMethod.GET)
    public String drop(HttpServletRequest req) {
        return "drop";
    }

    @RequestMapping(value = "/Insert", method = RequestMethod.GET)
    public String insert(HttpServletRequest req) {
        return "insert";
    }

    @RequestMapping(value = "/Update", method = RequestMethod.GET)
    public String update(HttpServletRequest req) {
        return "update";
    }

    @RequestMapping(value = "/ReadQuery", method = RequestMethod.GET)
    public String readQuery(HttpServletRequest req) {
        req.getSession().removeAttribute("count");
        req.getSession().removeAttribute("table");
        return "readquery";
    }

    @RequestMapping(value = "/CudQuery", method = RequestMethod.GET)
    public String cudQuery(HttpServletRequest req) {
        return "cudquery";
    }

    @RequestMapping(value = "/",  method = RequestMethod.GET)
    public String error(HttpServletRequest req) {
        return "error";
    }

}
