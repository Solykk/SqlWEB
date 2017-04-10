package ua.com.juja.sqlweb.control.commands;

import org.springframework.stereotype.Component;

@Component
public class Drop implements Command {

    @Override
    public String getCommandName() {
        return "Drop";
    }

    @Override
    public String getDescription() {
        return "\tКоманда удаляет заданную таблицу\n";
    }
}
