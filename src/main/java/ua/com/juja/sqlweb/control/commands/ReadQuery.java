package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.service.HelpList;

public class ReadQuery implements Command {

    private String commandName;
    private String description;

    public ReadQuery(HelpList helpList) {
        this.commandName = "ReadQuery";
        this.description = helpList.readQuery;
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
