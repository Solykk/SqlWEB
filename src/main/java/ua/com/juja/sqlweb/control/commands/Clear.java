package ua.com.juja.sqlweb.control.commands;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(8)
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
