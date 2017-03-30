package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.service.HelpList;

public class Tables implements Command {

    private String commandName;
    private String description;

    public Tables(HelpList helpList) {
        this.commandName = "Tables";
        this.description = helpList.tables;
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
