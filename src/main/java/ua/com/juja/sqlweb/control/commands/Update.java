package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.service.HelpList;

public class Update implements Command {

    private String commandName;
    private String description;

    public Update(HelpList helpList) {
        this.commandName = "Update";
        this.description = helpList.update;
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
