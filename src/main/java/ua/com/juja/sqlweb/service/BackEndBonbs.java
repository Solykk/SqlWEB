package ua.com.juja.sqlweb.service;

import ua.com.juja.sqlweb.control.commands.Command;
import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.model.Table;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BackEndBonbs {

    ArrayList<Command> commandsList();

    DatabaseManager connect(String databaseName, String userName, String password) throws SQLException;

    Table find(DatabaseManager manager, String tableName) throws SQLException;
}
