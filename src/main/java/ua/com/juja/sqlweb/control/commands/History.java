package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.service.HelpList;
import ua.com.juja.sqlweb.view.View;

public class History implements Command {

    private String commandName;
    private String description;

    public History(HelpList helpList) {
        this.commandName = "History";
        this.description = helpList.history;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public String getDescription() {
        return description;
    }

//    private View view;
//
//    public History(View view) {
//        this.view = view;
//    }
//
//    @Override
//    public boolean isProcessed(String command) {
//        return  command.equalsIgnoreCase("history");
//    }
//
//    @Override
//    public void process(String command) {
//        view.printHistory();
//    }
}
