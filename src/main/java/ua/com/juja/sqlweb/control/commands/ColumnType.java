package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.service.*;

import java.sql.SQLException;

public class ColumnType implements Command {

    private String commandName;
    private String description;

    public ColumnType(HelpList helpList) {
        this.commandName = "ColumnType";
        this.description = helpList.columntype;
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
//    private TablePrinter tablePrinter;
//
//    public ColumnType(DatabaseManager manager, Services services) {
//        this.manager = manager;
//        this.viewService = services.getViewService();
//        this.correctly = services.getCorrectly();
//        this.tablePrinter = services.getTablePrinter();
//    }
//
//    @Override
//    public boolean isProcessed(String command) {
//        return command.toLowerCase().startsWith("columntype|");
//    }
//
//    @Override
//    public void process(String command) {
//
//        String[] data = correctly.expectedThree(command);
//
//        String tableName = data[1];
//        String columnName = data[2];
//
//        try {
//            tablePrinter.printTable(manager.getTypeColumn(tableName, columnName));
//            viewService.columnTypeComTry(tableName, columnName);
//        } catch (SQLException |  NullPointerException e) {
//            viewService.columnTypComCatch(tableName, columnName, e.getMessage());
//        }
//    }
}
