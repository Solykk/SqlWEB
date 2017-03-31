package ua.com.juja.sqlweb.service;

public class Services {

    private ViewService viewService = new ViewService();
    private Correctly correctly = new Correctly();
    private TableToString tableToString = new TableToString();
    private SettingsHelper settingsHelper = new SettingsHelper();

    public ViewService getViewService() {
        return viewService;
    }

    public Correctly getCorrectly() {
        return correctly;
    }

    public TableToString getTableToString() {
        return tableToString;
    }

    public SettingsHelper getSettingsHelper() {
        return settingsHelper;
    }
}
