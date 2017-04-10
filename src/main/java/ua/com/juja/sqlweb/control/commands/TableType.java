package ua.com.juja.sqlweb.control.commands;

import org.springframework.stereotype.Component;

@Component
public class TableType implements Command {

    @Override
    public String getCommandName() {
        return "TableType";
    }

    @Override
    public String getDescription() {
        return  "\tКоманда выводит список всех колонок, \n" +
                "\t\t\t\t\tтип данных для соответсвующей колонки, \n" +
                "\t\t\t\t\tвозможность содержать null значение соответсвующей колонки\n" +
                "\t\tв запрашиваемой таблице.\n";
    }
}
