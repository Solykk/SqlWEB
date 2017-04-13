package ua.com.juja.sqlweb.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.model.Table;
import ua.com.juja.sqlweb.service.BackEndTie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
//            resp.sendRedirect(resp.encodeRedirectURL("index"));
            return "index";
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return "error";
        }
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

    @RequestMapping(value = "/columns", method = RequestMethod.GET)
    public String columns(HttpServletRequest req) {
        req.getSession().removeAttribute("table");
        req.getSession().removeAttribute("count");
        return "columns";
    }

    @RequestMapping(value = "/tabletype", method = RequestMethod.GET)
    public String tableType(HttpServletRequest req) {
        req.getSession().removeAttribute("table");
        req.getSession().removeAttribute("count");
        return "tabletype";
    }

    @RequestMapping(value = "/columntype", method = RequestMethod.GET)
    public String columnType(HttpServletRequest req) {
        req.getSession().removeAttribute("table");
        req.getSession().removeAttribute("count");
        return "columntype";
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(HttpServletRequest req) {
        req.getSession().removeAttribute("table");
        req.getSession().removeAttribute("count");
        return "find";
    }

    @RequestMapping(value = "/filetable", method = RequestMethod.GET)
    public String fileTable(HttpServletRequest req) {
        req.getSession().removeAttribute("table");
        req.getSession().removeAttribute("count");
        return "filetable";
    }

    @RequestMapping(value = "/fsettings", method = RequestMethod.GET)
    public String findSettings(HttpServletRequest req) {
        return "findsettings";
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public String clear(HttpServletRequest req) {
        return "clear";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(HttpServletRequest req) {
        return "create";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest req) {
        return "delete";
    }

    @RequestMapping(value = "/drop", method = RequestMethod.GET)
    public String drop(HttpServletRequest req) {
        return "drop";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insert(HttpServletRequest req) {
        return "insert";
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

    @RequestMapping(value = "/cudquery", method = RequestMethod.GET)
    public String cudQuery(HttpServletRequest req) {
        return "cudquery";
    }

    @RequestMapping(value = "/",  method = RequestMethod.GET)
    public String error(HttpServletRequest req) {
        return "error";
    }

}
