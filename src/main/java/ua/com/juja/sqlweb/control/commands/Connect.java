package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.service.Correctly;
import ua.com.juja.sqlweb.service.HelpList;
import ua.com.juja.sqlweb.service.Services;
import ua.com.juja.sqlweb.service.ViewService;

import java.sql.SQLException;

public class Connect implements Command {

    private String commandName;
    private String description;

    public Connect(HelpList helpList) {
        this.commandName = "Connect";
        this.description = helpList.connect;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public String getDescription() {
        return description;
    }

//        try {
//            manager.connect(userName, password);
//            viewService.connectComTry();
//        } catch (SQLException e) {
//            viewService.connectComCatch(e.getMessage());
//        }
//    }
}
