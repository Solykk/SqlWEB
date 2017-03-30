package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.service.*;

import java.sql.SQLException;

public class TableType implements Command {

    private String commandName;
    private String description;

    public TableType(HelpList helpList) {
        this.commandName = "TableType";
        this.description = helpList.tabletype;
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
//    public TableType(DatabaseManager manager, Services services) {
//        this.manager = manager;
//        this.viewService = services.getViewService();
//        this.correctly = services.getCorrectly();
//        this.tablePrinter = services.getTablePrinter();
//    }
//
//    @Override
//    public boolean isProcessed(String command) {
//        return command.toLowerCase().startsWith("tabletype|");
//    }
//
//    @Override
//    public void process(String command) {
//
//        String tableName = correctly.expectedTwo(command);
//
//        try {
//            tablePrinter.printTable(manager.getAllTypeColumns(tableName));
//            viewService.tablesTypeComTry(tableName);
//        } catch (SQLException | NullPointerException e) {
//            viewService.tablesTypeComCatch(tableName, e.getMessage());
//        }
//    }
}
