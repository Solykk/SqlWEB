package ua.com.juja.sqlweb.control.commands;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(14)
public class ReadQuery implements Command {

    @Override
    public String getCommandName() {
        return "ReadQuery";
    }

    @Override
    public String getDescription() {
        return "\tКоманда для ввода SQL запроса\n" + "(Только чтение из БД)";
    }
}
