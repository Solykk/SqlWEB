package ua.com.juja.sqlweb.control.commands;

import org.springframework.stereotype.Component;

@Component
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
