package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.service.HelpList;

public class Insert implements Command {

    private String commandName;
    private String description;

    public Insert(HelpList helpList) {
        this.commandName = "Insert";
        this.description = helpList.insert;
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
