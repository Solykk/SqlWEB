package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.service.HelpList;

public class Clear implements Command {

    private String commandName;
    private String description;

    public Clear(HelpList helpList) {
        this.commandName = "Clear";
        this.description = helpList.clear;
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
