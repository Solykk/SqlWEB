package ua.com.juja.sqlweb.service;

import ua.com.juja.sqlweb.control.commands.Command;
import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.model.Table;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BackEndTie {

    Services getServices();

    List<Command> getCommandsList();

    DatabaseManager connect(String databaseName, String userName, String password) throws SQLException;

    Table tables(DatabaseManager manager) throws SQLException;

    Table columns(DatabaseManager manager, String tableName) throws SQLException;

    Table tableType(DatabaseManager manager, String tableName) throws SQLException;

    Table columnType(DatabaseManager manager, String tableName, String columnName) throws SQLException;

    Table find(DatabaseManager manager, String tableName) throws SQLException;

    void fileTable(String fileName, Table table, String path) throws IOException;

    Table findSettings(DatabaseManager manager, String tableName,  List<String[]> settings) throws SQLException;

    void clear(DatabaseManager manager, String tableName) throws SQLException;

    void create(DatabaseManager manager, String tableName, List<String> settings, String columnNamePK, Long startWith) throws SQLException;

    void delete(DatabaseManager manager, String tableName, List<String[]> settings) throws SQLException;

    void drop(DatabaseManager manager, String tableName) throws SQLException;

    void insert(DatabaseManager manager, String tableName, List<String[]> settings, boolean isKey) throws SQLException;

    void update(DatabaseManager manager, String tableName, List<String[]> forUpdate, List<String[]> howUpdate) throws SQLException;

    Table readQuery(DatabaseManager manager, String query) throws SQLException;

    void cudQuery(DatabaseManager manager, String query) throws  SQLException;

    void disconnect(DatabaseManager manager);

}
