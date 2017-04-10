package ua.com.juja.sqlweb.control.commands;

import org.springframework.stereotype.Component;

@Component
public class Clear implements Command {

    @Override
    public String getCommandName() {
        return "Clear";
    }

    @Override
    public String getDescription() {
        return "\tКоманда создает новую таблицу с заданными полями\n";
    }
}
