package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.service.HelpList;

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

}
