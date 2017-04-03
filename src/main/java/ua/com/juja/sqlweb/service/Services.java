package ua.com.juja.sqlweb.service;

public class Services {

    private ViewService viewService = new ViewService();
    private TableToString tableToString = new TableToString();
    private SettingsHelper settingsHelper = new SettingsHelper();

    public ViewService getViewService() {
        return viewService;
    }

    public TableToString getTableToString() {
        return tableToString;
    }

    public SettingsHelper getSettingsHelper() {
        return settingsHelper;
    }
}
