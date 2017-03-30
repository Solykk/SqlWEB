package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.service.*;

import java.sql.SQLException;

public class Columns implements Command {

    private String commandName;
    private String description;

    public Columns(HelpList helpList) {
        this.commandName = "Columns";
        this.description = helpList.columns;
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
//    public Columns(DatabaseManager manager, Services services) {
//        this.manager = manager;
//        this.viewService = services.getViewService();
//        this.correctly = services.getCorrectly();
//        this.tablePrinter = services.getTablePrinter();
//    }
//
//    @Override
//    public boolean isProcessed(String command) {
//        return command.toLowerCase().startsWith("columns|");
//    }
//
//    @Override
//    public void process(String command) {
//
//        String tableName = correctly.expectedTwo(command);
//
//        try {
//            tablePrinter.printTable(manager.getColumnNames(tableName));
//            viewService.columnsComTry(tableName);
//        } catch (SQLException | NullPointerException e) {
//            viewService.columnsComCatch(tableName, e.getMessage());
//        }
//    }
}
