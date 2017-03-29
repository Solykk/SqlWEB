package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.service.Correctly;
import ua.com.juja.sqlweb.service.Services;
import ua.com.juja.sqlweb.service.ViewService;

import java.sql.SQLException;

public class Connect implements Command {

    private DatabaseManager manager;
    private ViewService viewService;
    private Correctly correctly;

    public Connect(DatabaseManager manager, Services services) {
        this.manager = manager;
        this.viewService = services.getViewService();
        this.correctly = services.getCorrectly();
    }

    @Override
    public boolean isProcessed(String command) {
        return command.toLowerCase().startsWith("connect|");
    }

    @Override
    public void process(String command) {

        String[] data = correctly.expectedThreeConnect(command);

        String userName = data[1];
        String password = data[2];

        try {
            manager.connect(userName, password);
            viewService.connectComTry();
        } catch (SQLException e) {
            viewService.connectComCatch(e.getMessage());
        }
    }
}
