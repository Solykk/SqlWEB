package ua.com.juja.sqlweb.control.commands;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(15)
public class CudQuery implements Command {

    @Override
    public String getCommandName() {
        return "CudQuery";
    }

    @Override
    public String getDescription() {
        return "\tКоманда для ввода SQL запроса\n" + "Для внесения изменений в БД";
    }
}
