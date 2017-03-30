package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.service.HelpList;

public class Drop implements Command {

    private String commandName;
    private String description;

    public Drop(HelpList helpList) {
        this.commandName = "Drop";
        this.description = helpList.drop;
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
