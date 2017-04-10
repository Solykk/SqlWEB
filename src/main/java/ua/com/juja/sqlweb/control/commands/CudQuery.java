package ua.com.juja.sqlweb.control.commands;

import org.springframework.stereotype.Component;

@Component
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
