package ua.com.juja.sqlweb.control;

import ua.com.juja.sqlweb.control.commands.*;
import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.service.Services;
import ua.com.juja.sqlweb.view.View;

import java.util.ArrayList;

public class CommandsList {

    private ArrayList<Command> commands;

    public CommandsList(View view, DatabaseManager manager, Services services){
        this.commands = new ArrayList<>();
        commands.add(new Connect(manager, services));
        commands.add(new Tables(manager, services));
        commands.add(new Columns(manager, services));
        commands.add(new TableType(manager, services));
        commands.add(new ColumnType(manager, services));
        commands.add(new Find(manager, services));
        commands.add(new FileTable(manager, view, services));
        commands.add(new FindSettings(manager, services));
        commands.add(new Clear(manager, services));
        commands.add(new Create(manager, view, services));
        commands.add(new Delete(manager, services));
        commands.add(new Drop(manager, services));
        commands.add(new Insert(manager, view, services));
        commands.add(new Update(manager, services));
        commands.add(new ReadQuery(manager, services));
        commands.add(new CudQuery(manager, services));
        commands.add(new History(view));
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }
}
