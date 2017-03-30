package ua.com.juja.sqlweb.service;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.model.Table;

import java.util.List;

public interface BackEndBonbs {

    List<String> commandsList();

    DatabaseManager connect(String databaseName, String userName, String password);

    Table find(DatabaseManager manager, String tableName);
}
