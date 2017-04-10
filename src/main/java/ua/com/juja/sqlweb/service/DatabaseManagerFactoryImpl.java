package ua.com.juja.sqlweb.service;

import ua.com.juja.sqlweb.model.DatabaseManager;

public class DatabaseManagerFactoryImpl implements DatabaseManagerFactory{

    private String className;

    private Query query;

    @Override
    public DatabaseManager createDatabaseManager() {
        try {
            return (DatabaseManager)Class.forName(className).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
}
