package ua.com.juja.sqlweb.service;

import ua.com.juja.sqlweb.view.View;

public class Services {

    private ViewService viewService = new ViewService();
    private Correctly correctly = new Correctly();
    private TablePrinter tablePrinter = new TablePrinter();
    private SettingsHelper settingsHelper = new SettingsHelper();

    public void setView(View view) {
        this.viewService.setView(view);
    }

    public ViewService getViewService() {
        return viewService;
    }

    public Correctly getCorrectly() {
        return correctly;
    }

    public TablePrinter getTablePrinter() {
        return tablePrinter;
    }

    public SettingsHelper getSettingsHelper() {
        return settingsHelper;
    }
}
