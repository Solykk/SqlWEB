package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.service.Correctly;
import ua.com.juja.sqlweb.service.HelpList;
import ua.com.juja.sqlweb.service.Services;
import ua.com.juja.sqlweb.service.ViewService;

import java.sql.SQLException;

public class Drop implements Command {

    private String commandName;
    private String description;

    public Drop(HelpList helpList) {
        this.commandName = "Drop";
        this.description = helpList.drop;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public String getDescription() {
        return description;
    }

//    private DatabaseManager manager;
//    private ViewService viewService;
//    private Correctly correctly;
//
//    public Drop(DatabaseManager manager, Services services) {
//        this.manager = manager;
//        this.viewService = services.getViewService();
//        this.correctly = services.getCorrectly();
//    }
//
//    @Override
//    public boolean isProcessed(String command) {
//        return command.toLowerCase().startsWith("drop|");
//    }
//
//    @Override
//    public void process(String command) {
//
//        String tableName = correctly.expectedTwo(command);
//
//        try {
//            manager.drop(tableName);
//            viewService.dropComTry(tableName);
//        } catch (SQLException | NullPointerException e) {
//            viewService.dropComCatch(tableName, e.getMessage());
//        }
//    }
}
