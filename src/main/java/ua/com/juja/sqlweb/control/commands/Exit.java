package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.view.View;

public class Exit implements Command {

    private View view;

    public Exit(View view) {
        this.view = view;
    }

    @Override
    public boolean isProcessed(String command) {
        return command.equalsIgnoreCase("exit");
    }

    @Override
    public void process(String command) {
        view.write("До встречи!");
    }
}
