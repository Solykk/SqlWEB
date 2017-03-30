package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.service.HelpList;

public class Find implements Command {

    private String commandName;
    private String description;

    public Find(HelpList helpList) {
        this.commandName = "Find";
        this.description = helpList.find;
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
