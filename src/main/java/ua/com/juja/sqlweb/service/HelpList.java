package ua.com.juja.sqlweb.service;

public class HelpList {

    public String connect = "connect" + "\n" +
            "\tКоманда для подключения к соответствующей БД\n" +
            "\tФормат команды: " + "connect|username|password" + "\n" +
            "\tгде: \n" +
            "\t\t" + "username" + " -  имя пользователя БД (совпвдает с именем БД)\n" +
            "\t\t" + "password" + " - пароль пользователя БД\n" +
            "\tФормат вывода: " + "текстовое сообщение с результатом выполнения операции.";
    public String tables = "tables" + "\n" +
            "\tКоманда выводит список всех пользовательских таблиц \n" +
            "\t\tсодержащихся в БД к которой вы подключены.\n" +
            "\tФормат команды: " + "tables"  + "\n" +
            "\tФормат вывода: " + "табличка в консольном формате";
    public String columns = "columns" + "\n" +
            "\tКоманда выводит список всех колонок  \n" +
            "\t\tсодержащихся в запрашиваемой таблице.\n" +
            "\tФормат команды: " + "columns|tableName" + "\n" +
            "\tгде " + "tableName" + " - имя запрашиваемой таблицы\n" +
            "\tФормат вывода: " + "табличка в консольном формате";
    public String tabletype = "tabletype" + "\n" +
            "\tКоманда выводит список всех колонок, \n" +
            "\t\t\t\t\tтип данных для соответсвующей колонки, \n" +
            "\t\t\t\t\tвозможность содержать null значение соответсвующей колонки\n" +
            "\t\tв запрашиваемой таблице.\n" +
            "\tФормат команды: " + "tabletype|tableName" + "\n" +
            "\tгде " + "tableName" + " - имя запрашиваемой таблицы\n" +
            "\tФормат вывода: " + "табличка в консольном формате";
    public String columntype = "columntype" + "\n" +
            "\tКоманда выводит  колоноку, \n" +
            "\t\t\t\t\tтип данных для соответсвующей колонки, \n" +
            "\t\t\t\t\tвозможность содержать null значение соответсвующей колонки\n" +
            "\t\tв запрашиваемой таблице.\n" +
            "\tФормат команды: " + "columntype|tableName|columnName" + "\n" +
            "\tгде " + "tableName" + " - имя запрашиваемой таблицы\n" +
            "\t\t" + "colummnName" + " - имя столбца в запрашиваемой таблице\n" +
            "\tФормат вывода: " + "табличка в консольном формате";
    public String clear = "clear" + "\n" +
            "\tКоманда очищает содержимое указанной (всей) таблицы\n" +
            "\tФормат команды: " + "clear|tableName" + "\n" +
            "\tгде " + "tableName" + " - имя очищаемой таблицы\n" +
            "\tФормат вывода: " + "текстовое сообщение с результатом выполнения операции";
    public String drop = "drop" + "\n" +
            "\tКоманда удаляет заданную таблицу\n" +
            "\tФормат команды: " + "drop|tableName" + "\n" +
            "\tгде " + "tableName" + " - имя удаляемой таблицы\n" +
            "\tФормат вывода: " + "текстовое сообщение с результатом выполнения операции";
    public String create = "create" + "\n" +
            "\tКоманда создает новую таблицу с заданными полями\n" +
            "\tФормат команды: " + "create|tableName|column1(data type(data size)) nullable|column2(data type(data size)) \n" +
            "nullable|...|columnN(data type(data size)) nullable" + "\n" +
            "\tгде: " + "tableName" + " - имя таблицы\n" +
            "\t" + "column1" + " - имя первого столбца записи\n" +
            "\t\t" + "(data type(data size))"+ " - (тип данных колонки(максимальный размер данных для колонки))\n" +
            "\t\t\t" + "nullable:" + "\n" +
            "\t\t\t\t" + "null" + " - при создании записи в столбце значение может содержать null\n" +
            "\t\t\t\t" + "not null" + " - при создании записи в столбце значение НЕ может содержать null\n" +
            "\t" + "column2" + " - имя второго столбца записи\n" +
            "\t\t-//-\n" +
            "\t" + "columnN" + " - имя n-го столбца записи\n" +
            "\t\t-//-\n" +
            "\tФормат вывода: " + "текстовое сообщение с результатом выполнения операции";
    public String find = "find" + "\n" +
            "\tКоманда для получения содержимого указанной таблицы\n" +
            "\tФормат команды: " + "find|tableName" + "\n" +
            "\tгде: " + "tableName" + " - имя таблицы\n" +
            "\tФормат вывода: " + "табличка в консольном формате";
    public String filetable = "filetable" + "\n" +
            "\tКоманда для сохранения содержимого указанной таблицы в файл\n" +
            "\tФормат команды: " + "filetable|tableName" + "\n" +
            "\tгде: " + "tableName" + " - имя таблицы\n" +
            "\tФормат вывода: " + "табличка в консольном формате + текстовое сообщение с результатом выполнения операции";
    public String findsettings = "findsettings" + "\n" +
            "\tКоманда для получения содержимого указанной таблицы по определенным критериям\n" +
            "\tФормат команды: " + "find|tableName|columnName|value|column2|value2|...|columnN|valueN" + "\n" +
            "\tгде: " + "tableName" + " - имя таблицы\n" +
            "\t\t" + "column1" + " - имя первого столбца\n" +
            "\t\t" + "value1" + " - значение первого столбца\n" +
            "\t\t" + "column2" + " - имя второго столбца\n" +
            "\t\t" + "value2" + " - значение второго столбца\n" +
            "\t\t" + "columnN" + " - имя n-го столбца\n" +
            "\t\t" + "valueN" + " - имя n-го столбца\n" +
            "\tФормат вывода: " + "табличка в консольном формате";
    public String insert = "insert" + "\n" +
            "\tКоманда для вставки одной строки в заданную таблицу\n" +
            "\tФормат команды: " + "insert|tableName|column1|value1|column2|value2|...|columnN|valueN" + "\n" +
            "\tгде: " + "tableName" + " - имя таблицы\n" +
            "\t" + "column1" + " - имя первого столбца записи\n" +
            "\t" + "value1" + " - значение первого столбца записи\n" +
            "\t" + "column2" + " - имя второго столбца записи\n" +
            "\t" + "value2" + " - значение второго столбца записи\n" +
            "\t" + "columnN" + " - имя n-го столбца записи\n" +
            "\t" + "valueN" + " - имя n-го столбца записи\n" +
            "\t\tNUMBER --- записывается число без ковычек   columnName|123\n" +
            "\t\tVARCHAR2 - строковое значение записывается в ковычках   columnName|'Hello'\n" +
            "\t\tDATE ----- даты записываются следующим образом  to_date('20160321','YYYYMMDD')\n" +
            "\tФормат вывода: " + "текстовое сообщение с результатом выполнения операции";
    public String update = "update" + "\n" +
            "\tКоманда обновит запись, установив значение column2 = value2, для которой соблюдается условие column1 = value1\n" +
            "\tФормат команды: " + "update|tableName|column1|value1|column2|value2" + "\n" +
            "\tгде: " + "tableName" + " - имя таблицы\n" +
            "\t" + "column1" + " - имя столбца записи которое проверяется\n" +
            "\t" + "value1" + " - значение которому должен соответствовать столбец column1 для обновляемой записи\n" +
            "\t" + "column2" + " - имя обновляемого столбца записи\n" +
            "\t" + "value2" + " - значение обновляемого столбца записи\n" +
            "\t" + "columnN" + " - имя n-го обновляемого столбца записи\n" +
            "\t" + "valueN"+ " - значение n-го обновляемого столбца записи\n" +
            "\t\tNUMBER --- записывается число без ковычек   columnName|123\n" +
            "\t\tVARCHAR2 - строковое значение записывается в ковычках   columnName|'Hello'\n" +
            "\t\tDATE ----- даты записываются следующим образом  to_date('20160321','YYYYMMDD')\n" +
            "\t" + "Количесьво обновляемых параметров и параметров по которым обновлять должно быть одинаковым!" + "\n" +
            "\tФормат вывода: " + "табличный, как при find со старыми значениями обновленных записей.";
    public String delete = "delete" + "\n" +
            "\tКоманда удаляет одну или несколько записей для которых соблюдается условие column = value\n" +
            "\tФормат команды: " + "delete|tableName|column|value" + "\n" +
            "\tгде: " + "tableName" + " - имя таблицы\n" +
            "\t" + "column" + " - имя столбца записи которое проверяется\n" +
            "\t" + "value" + " - значение которому должен соответствовать столбец column1 для удаляемой записи\n" +
            "\tФормат вывода: " + "табличный, как при find со старыми значениями удаляемых записей.";
    public String readQuery = "readQuery" + "\n" +
            "\tКоманда для ввода SQL запроса\n" +
            "\tФормат команды: " + "readQuery|SQLQuery" + "(Только чтение из БД)" +  "\n" +
            "\tгде: SQLQuery - ваш SQL запрос\n" +
            "\tФормат вывода: " + "табличка в консольном формате";
    public String cudQuery = "cudQuery" + "\n" +
            "\tФормат команды: " + "cudQuery|SQLQuery" + " (Для внесения изменений в таблицу)\n" +
            "\tгде: " + "SQLQuery" + " - ваш SQL запрос\n" +
            "\tФормат вывода: " + "текстовое сообщение с результатом выполнения операции";
    public String history = "history" + "\n" +
            "\tКоманда для получения истории работы с приложением\n" +
            "\tФормат команды: " + "history" + "\n" +
            "\tФормат вывода: " + "дата и время использования команды -> действия" + "\n" +
            "\t\t\t\t\t\t\t" + "результат выполнения команды";
    public String help = "help" + "\n" +
            "\tКоманда выводит в консоль список всех доступных команд\n" +
            "\tФормат команды: " + "help" + "\n" +
            "\tФормат вывода: " + "текст, описания команд";
    public String exit = "exit" + "\n" +
            "\tКоманда для отключения от БД и выход из приложения\n" +
            "\tФормат команды: " + "exit" + "\n" +
            "\tФормат вывода: " + "текстовое сообщение";
}
