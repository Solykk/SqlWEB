package ua.com.juja.sqlweb.control.commands;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(6)
public class Find implements Command {

    @Override
    public String getCommandName() {
        return "Find";
    }

    @Override
    public String getDescription() {
        return "\tКоманда для получения содержимого указанной таблицы\n";
    }
}
