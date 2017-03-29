package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.service.Correctly;
import ua.com.juja.sqlweb.service.Services;
import ua.com.juja.sqlweb.service.ViewService;

import java.sql.SQLException;

public class CudQuery implements Command {

    private DatabaseManager manager;
    private ViewService viewService;
    private Correctly correctly;

    public CudQuery(DatabaseManager manager, Services services) {
        this.manager = manager;
        this.viewService = services.getViewService();
        this.correctly = services.getCorrectly();
    }

    @Override
    public boolean isProcessed(String command) {
        return command.toLowerCase().startsWith("cudquery|");
    }

    @Override
    public void process(String command) {

        String query = correctly.expectedTwoCRUD(command);

        try {
            manager.cudQuery(query);
            viewService.cudQueryComTry(query);
        } catch (SQLException | NullPointerException e) {
            viewService.cudQueryComCatch(query, e.getMessage());
        }
    }
}
