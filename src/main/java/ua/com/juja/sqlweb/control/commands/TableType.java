package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.service.HelpList;

public class TableType implements Command {

    private String commandName;
    private String description;

    public TableType(HelpList helpList) {
        this.commandName = "TableType";
        this.description = helpList.tabletype;
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
