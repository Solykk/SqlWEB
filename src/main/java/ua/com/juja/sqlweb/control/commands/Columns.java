package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.service.HelpList;

public class Columns implements Command {

    private String commandName;
    private String description;

    public Columns(HelpList helpList) {
        this.commandName = "Columns";
        this.description = helpList.columns;
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
