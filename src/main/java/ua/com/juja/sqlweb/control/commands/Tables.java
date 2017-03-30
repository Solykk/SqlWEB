package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.service.HelpList;
import ua.com.juja.sqlweb.service.Services;
import ua.com.juja.sqlweb.service.TablePrinter;
import ua.com.juja.sqlweb.service.ViewService;

import java.sql.SQLException;

public class Tables implements Command {

    private String commandName;
    private String description;

    public Tables(HelpList helpList) {
        this.commandName = "Tables";
        this.description = helpList.tables;
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
//    private TablePrinter tablePrinter;
//
//    public Tables(DatabaseManager manager, Services services) {
//        this.manager = manager;
//        this.viewService = services.getViewService();
//        this.tablePrinter = services.getTablePrinter();
//    }
//
//    @Override
//    public boolean isProcessed(String command) {
//        return command.equalsIgnoreCase("tables");
//    }
//
//    @Override
//    public void process(String command) {
//
//        try {
//            tablePrinter.printTable(manager.getTableNames());
//            viewService.tablesComTry();
//        } catch (SQLException | NullPointerException e) {
//            viewService.tablesComCatch(e.getMessage());
//        }
//    }
}
