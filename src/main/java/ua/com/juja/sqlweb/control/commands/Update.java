package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.service.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class Update implements Command {

    private String commandName;
    private String description;

    public Update(HelpList helpList) {
        this.commandName = "Update";
        this.description = helpList.update;
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
//    private SettingsHelper settingsHelper;
//    private final int parametersCount = 6;
//
//    public Update(DatabaseManager manager, Services services) {
//        this.manager = manager;
//        this.viewService = services.getViewService();
//        this.correctly = services.getCorrectly();
//        this.tablePrinter = services.getTablePrinter();
//        this.settingsHelper = services.getSettingsHelper();
//    }
//
//    @Override
//    public boolean isProcessed(String command) {
//        return command.toLowerCase().startsWith("update|");
//    }
//
//    @Override
//    public void process(String command) {
//
//        String[] data = correctly.expectedMinEven(command, parametersCount);
//
//        String tableName = data[1];
//        ArrayList<String[]> forUpdate = new ArrayList<>();
//        ArrayList<String[]> howUpdate = new ArrayList<>();
//
//        settingsHelper.getSetUpdate(data, forUpdate, howUpdate);
//
//        try {
//            manager.update(tableName, forUpdate, howUpdate);
//            tablePrinter.printTable(manager.read(tableName));
//
//            viewService.updateComTry(tableName, command);
//        } catch (SQLException | NullPointerException e) {
//            viewService.updateComCatch(tableName, command, e.getMessage());
//        }
//    }
}
