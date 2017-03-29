package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.service.Correctly;
import ua.com.juja.sqlweb.service.Services;
import ua.com.juja.sqlweb.service.SettingsHelper;
import ua.com.juja.sqlweb.service.ViewService;
import ua.com.juja.sqlweb.view.View;

import java.sql.SQLException;
import java.util.ArrayList;

public class Insert implements Command {

    private DatabaseManager manager;
    private View view;
    private ViewService viewService;
    private Correctly correctly;
    private SettingsHelper settingsHelper;
    private final int parametersCount = 4;

    public Insert(DatabaseManager manager, View view, Services services) {
        this.manager = manager;
        this.view = view;
        this.viewService = services.getViewService();
        this.correctly = services.getCorrectly();
        this.settingsHelper = services.getSettingsHelper();
    }

    @Override
    public boolean isProcessed(String command) {
        return command.toLowerCase().startsWith("insert|");
    }

    @Override
    public void process(String command) {

        String[] data = correctly.expectedMinEven(command, parametersCount);

        String tableName = data[1];
        boolean isKey = getKeySet();
        ArrayList<String[]> settings = getSettings(data, isKey);

        try {
            manager.insert(tableName, settings, isKey);
            viewService.insertComTry(tableName, command);
        } catch (SQLException | NullPointerException e) {
            viewService.insertComCatch(tableName, command, e.getMessage());
        }
    }

    private String keyAction() {
        view.write("Добавить Seq генератор, если такой имеется? Y/N");
        return view.read();
    }

    private boolean getKeySet() {
        String key = keyAction();
        return key.equalsIgnoreCase("y");
    }

    private String seqAction() {
        view.write("Введите название колонки: ");
        return view.read();
    }

    private ArrayList<String[]> getSettings(String[] data, boolean isKey) {
        ArrayList<String[]> settings = new ArrayList<>();
        if(isKey){
            String seqName = seqAction();
            settings.add(new String[]{seqName, ""});
        }
        settingsHelper.addSettings(data, settings);
        return settings;
    }
}
