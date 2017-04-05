package ua.com.juja.sqlweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Services {

    @Autowired
    private TableToString tableToString;

    @Autowired
    private SettingsHelper settingsHelper;

    @Autowired
    private HelpList helpList;

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

    public HelpList getHelpList() {
        return helpList;
    }

    public void setHelpList(HelpList helpList) {
        this.helpList = helpList;
    }

}
