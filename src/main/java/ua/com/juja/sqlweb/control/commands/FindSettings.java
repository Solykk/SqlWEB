package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.service.HelpList;

public class FindSettings implements Command {

    private String commandName;
    private String description;

    public FindSettings(HelpList helpList) {
        this.commandName = "FSettings";
        this.description = helpList.findsettings;
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
