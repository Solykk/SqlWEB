package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.service.HelpList;
import ua.com.juja.sqlweb.view.View;

public class Help implements Command {

    private View view;
    private final HelpList helpList = new HelpList();

    public Help(View view){
        this.view = view;
    }

    @Override
    public boolean isProcessed(String command) {
        return  command.equalsIgnoreCase("help");
    }

    @Override
    public void process(String command) {
         view.write(helpList.connect);
         view.write(helpList.tables);
         view.write(helpList.columns);
         view.write(helpList.tabletype);
         view.write(helpList.columntype);
         view.write(helpList.clear);
         view.write(helpList.drop);
         view.write(helpList.create);
         view.write(helpList.find);
         view.write(helpList.filetable);
         view.write(helpList.findsettings);
         view.write(helpList.insert);
         view.write(helpList.update);
         view.write(helpList.delete);
         view.write(helpList.readQuery);
         view.write(helpList.cudQuery);
         view.write(helpList.history);
         view.write(helpList.help);
         view.write(helpList.exit);
    }
}
