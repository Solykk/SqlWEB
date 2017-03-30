package ua.com.juja.sqlweb.service;

import ua.com.juja.sqlweb.control.CommandsList;
import ua.com.juja.sqlweb.control.commands.Command;
import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.model.JDBCDatabaseManager;
import ua.com.juja.sqlweb.model.Table;

import java.sql.SQLException;
import java.util.ArrayList;

public class BackEndBonbsImpl implements BackEndBonbs{

    @Override
    public ArrayList<Command> commandsList() {
        HelpList helpList = new HelpList();
        return new CommandsList(helpList).getCommands();
    }

    @Override
    public DatabaseManager connect(String ipAddress, String userName, String password) throws SQLException {
        DatabaseManager manager = new JDBCDatabaseManager();
        manager.connect(ipAddress, userName, password);
        return manager;
    }

    @Override
    public Table find(DatabaseManager manager, String tableName) throws SQLException {
        return manager.read(tableName);
    }
}
