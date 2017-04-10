package ua.com.juja.sqlweb.control.commands;

import org.springframework.stereotype.Component;

@Component
public class Connect implements Command {

    @Override
    public String getCommandName() {
        return "Connect";
    }

    @Override
    public String getDescription() {
        return "\tКоманда для подключения к соответствующей БД\n";
    }
}
