package ua.com.juja.sqlweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ua.com.juja.sqlweb.control.CommandsList;
import ua.com.juja.sqlweb.control.commands.Command;
import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.model.Table;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BackEndTieImpl implements BackEndTie {

    @Autowired
    private DatabaseManager manager;

    @Autowired
    private Services services;

    @Autowired
    private CommandsList commandsList;

    public BackEndTieImpl(){}

    @Override
    public Services getServices() {
        return services;
    }

    @Override
    public List<Command> getCommandsList() {
        return commandsList.getCommands();
    }

    @Override
    public DatabaseManager connect(String ipAddress, String userName, String password) throws SQLException {
        manager.connect(ipAddress, userName, password);
        return manager;
    }

    @Override
    public Table tables(DatabaseManager manager) throws SQLException {
        return manager.getTableNames();
    }

    @Override
    public Table columns(DatabaseManager manager, String tableName) throws SQLException {
        return manager.getColumnNames(tableName);
    }

    @Override
    public Table tableType(DatabaseManager manager, String tableName) throws SQLException {
        return manager.getAllTypeColumns(tableName);
    }

    @Override
    public Table columnType(DatabaseManager manager, String tableName, String columnName) throws SQLException {
        return manager.getTypeColumn(tableName, columnName);
    }

    @Override
    public Table find(DatabaseManager manager, String tableName) throws SQLException {
        return manager.read(tableName);
    }

    @Override
    public void fileTable(String fileName, Table table, String path) throws IOException {
        TableToString tTS = services.getTableToString();
        String tableToString = tTS.tableTS(table);
        System.out.println(tableToString);
        File file = new File(path + fileName + ".txt");
        if (file.createNewFile()) {
            try (FileWriter writer = new FileWriter(path + fileName + ".txt")) {
                writer.write(tableToString);
                writer.flush();
            }
        }
    }

    @Override
    public Table findSettings(DatabaseManager manager, String tableName, ArrayList<String[]> settings) throws SQLException {
        return manager.readSet(tableName, settings);
    }

    @Override
    public void clear(DatabaseManager manager, String tableName) throws SQLException {
        manager.clear(tableName);
    }

    @Override
    public void create(DatabaseManager manager, String tableName, ArrayList<String> settings, String columnNamePK, Long startWith) throws SQLException {
        manager.createWithoutPK(tableName, settings);
        if(columnNamePK != null) {
            manager.createCreatePK(tableName, columnNamePK);
            if(startWith != null){
                manager.createSequencePK(tableName, startWith);
            }
        }
    }

    @Override
    public void delete(DatabaseManager manager, String tableName, ArrayList<String[]> settings) throws SQLException {
        manager.delete(tableName,settings);
    }

    @Override
    public void drop(DatabaseManager manager, String tableName) throws SQLException {
        manager.drop(tableName);
    }

    @Override
    public void insert(DatabaseManager manager, String tableName, ArrayList<String[]> settings, boolean isKey) throws SQLException {
        manager.insert(tableName, settings, isKey);
    }

    @Override
    public void update(DatabaseManager manager, String tableName, ArrayList<String[]> forUpdate, ArrayList<String[]> howUpdate) throws SQLException {
        manager.update(tableName, forUpdate, howUpdate);
    }

    @Override
    public Table readQuery(DatabaseManager manager, String query) throws SQLException {
        return manager.readQuery(query);
    }

    @Override
    public void cudQuery(DatabaseManager manager, String query) throws SQLException {
        manager.cudQuery(query);
    }

    @Override
    public void disconnect(DatabaseManager manager) {
        manager.disconnect();
    }

}
