package ua.com.juja.sqlweb.control.commands;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(10)
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
