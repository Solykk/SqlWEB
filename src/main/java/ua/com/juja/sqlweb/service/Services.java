package ua.com.juja.sqlweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Services {

    @Autowired
    private TableToString tableToString;

    @Autowired
    private SettingsHelper settingsHelper;

    public TableToString getTableToString() {
        return tableToString;
    }

    public void setTableToString(TableToString tableToString) {
        this.tableToString = tableToString;
    }

    public SettingsHelper getSettingsHelper() {
        return settingsHelper;
    }

    public void setSettingsHelper(SettingsHelper settingsHelper) {
        this.settingsHelper = settingsHelper;
    }

}
