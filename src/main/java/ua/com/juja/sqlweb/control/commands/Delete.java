package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.service.HelpList;

public class Delete implements Command {

    private String commandName;
    private String description;

    public Delete(HelpList helpList) {
        this.commandName = "Delete";
        this.description = helpList.delete;
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
