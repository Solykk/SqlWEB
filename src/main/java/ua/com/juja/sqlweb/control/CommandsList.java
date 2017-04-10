package ua.com.juja.sqlweb.control;

import org.springframework.stereotype.Component;
import ua.com.juja.sqlweb.control.commands.*;

import java.util.ArrayList;

@Component
public class CommandsList {

    private ArrayList<Command> commands;

    public  CommandsList(){}

    public CommandsList(Command ... command){
        this.commands = new ArrayList<>();
        for (Command c: command) {
            commands.add(c);
        }
//        this.commands = new ArrayList<>();
//        commands.add(new Connect(helpList));
//        commands.add(new Tables(helpList));
//        commands.add(new Columns(helpList));
//        commands.add(new TableType(helpList));
//        commands.add(new ColumnType(helpList));
//        commands.add(new Find(helpList));
//        commands.add(new FindSettings(helpList));
//        commands.add(new Clear(helpList));
//        commands.add(new Create(helpList));
//        commands.add(new Delete(helpList));
//        commands.add(new Drop(helpList));
//        commands.add(new Insert(helpList));
//        commands.add(new Update(helpList));
//        commands.add(new ReadQuery(helpList));
//        commands.add(new CudQuery(helpList));
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }
}
