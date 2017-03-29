package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.service.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class FindSettings implements Command {

    private DatabaseManager manager;
    private ViewService viewService;
    private Correctly correctly;
    private TablePrinter tablePrinter;
    private SettingsHelper settingsHelper;
    private final int parametersCount = 4;

    public FindSettings(DatabaseManager manager, Services services) {
        this.manager = manager;
        this.viewService = services.getViewService();
        this.correctly = services.getCorrectly();
        this.tablePrinter = services.getTablePrinter();
        this.settingsHelper = services.getSettingsHelper();
    }

    @Override
    public boolean isProcessed(String command) {
        return command.toLowerCase().startsWith("findsettings|");
    }

    @Override
    public void process(String command) {

        String[] data = correctly.expectedMinEven(command, parametersCount);

        String tableName = data[1];
        ArrayList<String[]> settings = settingsHelper.getSettings(data);

        try {
            tablePrinter.printTable(manager.readSet(tableName, settings));
            viewService.findSetComTry(tableName, command);
        } catch (SQLException | NullPointerException e) {
            viewService.findSetComCatch(tableName, command, e.getMessage());
        }
    }
}
