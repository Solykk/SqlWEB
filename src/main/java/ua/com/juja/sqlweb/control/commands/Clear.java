package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.service.Correctly;
import ua.com.juja.sqlweb.service.Services;
import ua.com.juja.sqlweb.service.ViewService;

import java.sql.SQLException;

public class Clear implements Command {

    private DatabaseManager manager;
    private ViewService viewService;
    private Correctly correctly;

    public Clear(DatabaseManager manager, Services services) {
        this.manager = manager;
        this.viewService = services.getViewService();
        this.correctly = services.getCorrectly();
    }

    @Override
    public boolean isProcessed(String command) {
        return command.toLowerCase().startsWith("clear|");
    }

    @Override
    public void process(String command) {

        String tableName = correctly.expectedTwo(command);

        try {
            manager.clear(tableName);
            viewService.clearComTry(tableName);
        } catch (SQLException | NullPointerException e) {
            viewService.clearComCatch(tableName, e.getMessage());
        }
    }
}
