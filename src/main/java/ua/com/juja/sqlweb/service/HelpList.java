package ua.com.juja.sqlweb.service;

public class HelpList {

    public String connect = "\tКоманда для подключения к соответствующей БД\n";

    public String tables =  "\tКоманда выводит список всех пользовательских таблиц \n" +
                            "\t\tсодержащихся в БД к которой вы подключены.\n";

    public String columns = "\tКоманда выводит список всех колонок  \n" +
                            "\t\tсодержащихся в запрашиваемой таблице.\n";

    public String tabletype = "\tКоманда выводит список всех колонок, \n" +
                              "\t\t\t\t\tтип данных для соответсвующей колонки, \n" +
                              "\t\t\t\t\tвозможность содержать null значение соответсвующей колонки\n" +
                              "\t\tв запрашиваемой таблице.\n";

    public String columntype = "\tКоманда выводит  колоноку, \n" +
                                "\t\t\t\t\tтип данных для соответсвующей колонки, \n" +
                                "\t\t\t\t\tвозможность содержать null значение соответсвующей колонки\n" +
                                "\t\tв запрашиваемой таблице.\n";

    public String clear = "\tКоманда очищает содержимое указанной (всей) таблицы\n";

    public String drop = "\tКоманда удаляет заданную таблицу\n";

    public String create = "\tКоманда создает новую таблицу с заданными полями\n";

    public String find = "\tКоманда для получения содержимого указанной таблицы\n";

    public String filetable = "\tКоманда для сохранения содержимого указанной таблицы в файл\n";

    public String findsettings = "\tКоманда для получения содержимого указанной таблицы по определенным критериям\n";

    public String insert = "\tКоманда для вставки одной строки в заданную таблицу\n";

    public String update = "\tКоманда обновит запись, установив значение column2 = value2, для которой соблюдается условие column1 = value1\n";

    public String delete = "\tКоманда удаляет одну или несколько записей для которых соблюдается условие column = value\n";

    public String readQuery = "\tКоманда для ввода SQL запроса\n" + "(Только чтение из БД)";

    public String cudQuery = "\tКоманда для ввода SQL запроса\n" + "Для внесения изменений в БД";

    public String history = "\tКоманда для получения истории работы с приложением\n";

}
