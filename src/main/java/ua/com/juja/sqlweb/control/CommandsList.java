package ua.com.juja.sqlweb.control;

import ua.com.juja.sqlweb.control.commands.*;

import java.util.ArrayList;

public class CommandsList {

    private ArrayList<Command> commands;

    public  CommandsList(){}

    public CommandsList(Command ... command){
        this.commands = new ArrayList<>();
        for (Command c: command) {
            commands.add(c);
        }
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }
}
