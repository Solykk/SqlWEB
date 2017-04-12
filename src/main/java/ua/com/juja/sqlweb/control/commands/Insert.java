package ua.com.juja.sqlweb.control.commands;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(12)
public class Insert implements Command {

    @Override
    public String getCommandName() {
        return "Insert";
    }

    @Override
    public String getDescription() {
        return "\tКоманда для вставки одной строки в заданную таблицу\n";
    }
}
