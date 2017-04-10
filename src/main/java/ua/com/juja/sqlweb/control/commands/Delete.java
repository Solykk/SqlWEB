package ua.com.juja.sqlweb.control.commands;

import org.springframework.stereotype.Component;

@Component
public class Delete implements Command {

    @Override
    public String getCommandName() {
        return "Delete";
    }

    @Override
    public String getDescription() {
        return "\tКоманда удаляет одну или несколько записей для которых соблюдается условие column = value\n";
    }
}
