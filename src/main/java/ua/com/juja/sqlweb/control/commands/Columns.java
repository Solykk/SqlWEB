package ua.com.juja.sqlweb.control.commands;

import org.springframework.stereotype.Component;

@Component
public class Columns implements Command {

    @Override
    public String getCommandName() {
        return "Columns";
    }

    @Override
    public String getDescription() {
        return  "\tКоманда выводит список всех колонок  \n" +
                "\t\tсодержащихся в запрашиваемой таблице.\n";
    }
}
