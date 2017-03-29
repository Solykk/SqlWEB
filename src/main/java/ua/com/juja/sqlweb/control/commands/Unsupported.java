package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.view.View;

public class Unsupported implements Command {

    private View view;

    public Unsupported(View view){
        this.view = view;
    }

    @Override
    public boolean isProcessed(String command) {
        return true;
    }

    @Override
    public void process(String command) {
        view.write("Несуществующая команда: " + command);
    }
}
