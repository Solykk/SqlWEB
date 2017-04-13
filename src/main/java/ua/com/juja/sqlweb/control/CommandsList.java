package ua.com.juja.sqlweb.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.juja.sqlweb.control.commands.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CommandsList {

    @Autowired
    private List<Command> commands;

    public  CommandsList(){}

    public CommandsList(Command ... command){
        this.commands = new ArrayList<>();
        Collections.addAll(commands, command);
    }

    public List<Command> getCommands() {
        return commands;
    }
}
