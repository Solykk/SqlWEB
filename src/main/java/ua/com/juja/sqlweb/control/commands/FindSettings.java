package ua.com.juja.sqlweb.control.commands;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(7)
public class FindSettings implements Command {

    @Override
    public String getCommandName() {
        return "FSettings";
    }

    @Override
    public String getDescription() {
        return "\tКоманда для получения содержимого указанной таблицы по определенным критериям\n";
    }
}
