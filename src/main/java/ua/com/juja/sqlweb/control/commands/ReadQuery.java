package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.service.*;

import java.sql.SQLException;

public class ReadQuery implements Command {

    private String commandName;
    private String description;

    public ReadQuery(HelpList helpList) {
        this.commandName = "ReadQuery";
        this.description = helpList.readQuery;
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
//    public ReadQuery(DatabaseManager manager, Services services) {
//        this.manager = manager;
//        this.viewService = services.getViewService();
//        this.correctly = services.getCorrectly();
//        this.tablePrinter = services.getTablePrinter();
//    }
//
//    @Override
//    public boolean isProcessed(String command) {
//        return command.toLowerCase().startsWith("readquery|");
//    }
//
//    @Override
//    public void process(String command) {
//
//        String query = correctly.expectedTwoCRUD(command);
//
//        try {
//            tablePrinter.printTable(manager.readQuery(query));
//            viewService.readQueryComTry(query);
//        } catch (SQLException | NullPointerException e) {
//            viewService.readQueryComCatch(query, e.getMessage());
//        }
//    }
}
