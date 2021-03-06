package ua.com.juja.sqlweb.control.commands;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
public class ColumnType implements Command {

    @Override
    public String getCommandName() {
        return "ColumnType";
    }

    @Override
    public String getDescription() {
        return  "\tКоманда выводит  колоноку, \n" +
                "\t\t\t\t\tтип данных для соответсвующей колонки, \n" +
                "\t\t\t\t\tвозможность содержать null значение соответсвующей колонки\n" +
                "\t\tв запрашиваемой таблице.\n";
    }
}
