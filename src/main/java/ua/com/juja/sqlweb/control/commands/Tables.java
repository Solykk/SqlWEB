package ua.com.juja.sqlweb.control.commands;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class Tables implements Command {

    @Override
    public String getCommandName() {
        return "Tables";
    }

    @Override
    public String getDescription() {
        return  "\tКоманда выводит список всех пользовательских таблиц \n" +
                "\t\tсодержащихся в БД к которой вы подключены.\n";
    }
}
