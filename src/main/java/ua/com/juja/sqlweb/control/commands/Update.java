package ua.com.juja.sqlweb.control.commands;

import org.springframework.stereotype.Component;

@Component
public class Update implements Command {

    @Override
    public String getCommandName() {
        return "Update";
    }

    @Override
    public String getDescription() {
        return "\tКоманда обновит запись, установив значение column2 = value2, для которой соблюдается условие column1 = value1\n";
    }
}
