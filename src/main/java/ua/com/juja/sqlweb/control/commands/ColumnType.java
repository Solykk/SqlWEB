package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.service.HelpList;

public class ColumnType implements Command {

    private String commandName;
    private String description;

    public ColumnType(HelpList helpList) {
        this.commandName = "ColumnType";
        this.description = helpList.columntype;
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
