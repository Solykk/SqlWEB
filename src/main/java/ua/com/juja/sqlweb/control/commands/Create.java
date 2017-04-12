package ua.com.juja.sqlweb.control.commands;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(9)
public class Create implements Command {

    @Override
    public String getCommandName() {
        return "Create";
    }

    @Override
    public String getDescription() {
        return "\tКоманда создает новую таблицу с заданными полями\n";
    }
}
