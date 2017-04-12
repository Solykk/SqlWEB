package ua.com.juja.sqlweb.control.commands;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(11)
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
