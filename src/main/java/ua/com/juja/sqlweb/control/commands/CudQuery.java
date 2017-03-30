package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.service.HelpList;

public class CudQuery implements Command {

    private String commandName;
    private String description;

    public CudQuery(HelpList helpList) {
        this.commandName = "CudQuery";
        this.description = helpList.cudQuery;
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
