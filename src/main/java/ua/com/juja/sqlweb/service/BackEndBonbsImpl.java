package ua.com.juja.sqlweb.service;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.model.Table;

import java.util.List;

public class BackEndBonbsImpl implements BackEndBonbs{

    @Override
    public List<String> commandsList() {
        return null;
    }

    @Override
    public DatabaseManager connect(String databaseName, String userName, String password) {
        return null;
    }

    @Override
    public Table find(DatabaseManager manager, String tableName) {
        return null;
    }
}
