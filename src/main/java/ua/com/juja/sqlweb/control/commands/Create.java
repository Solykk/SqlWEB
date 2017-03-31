package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.service.HelpList;

public class Create implements Command {

    private String commandName;
    private String description;

    public Create(HelpList helpList) {
        this.commandName = "Create";
        this.description = helpList.create;
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
