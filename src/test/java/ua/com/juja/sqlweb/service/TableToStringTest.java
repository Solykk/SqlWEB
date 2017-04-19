package ua.com.juja.sqlweb.service;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.sqlweb.model.ColumnData;
import ua.com.juja.sqlweb.model.Table;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TableToStringTest {

    private TableToString tableToString;

    @Before
    public void start(){
        tableToString = new TableToString();
    }

    @Test
    public void test_printTableNull(){
        assertEquals("", tableToString.tableTS(null));
    }

    @Test
    public void test_printTableBigQuery(){
        List<String> first = new ArrayList<>();
        first.add("value");
        ColumnData one = new ColumnData("COLUMN_NAME", first);
        ColumnData two = new ColumnData("CO", first);
        ColumnData three = new ColumnData("TE", first);
        List<ColumnData> columnDatas = new ArrayList<>();
        columnDatas.add(one);
        columnDatas.add(two);
        columnDatas.add(three);

        assertEquals(
                "-------------------------------\n" +
                        "|       BIG_TABLE_NAME        |\n" +
                        "-------------------------------\n" +
                        "| COLUMN_NAME |  CO   |  TE   |\n" +
                        "-------------------------------\n" +
                        "|    value    | value | value |\n" +
                        "-------------------------------\n", tableToString.tableTS(
                        new Table("BIG_TABLE_NAME",
                                columnDatas)));
    }

    @Test
    public void test_printTableBigQueryOneColumn(){
        List<String> first = new ArrayList<>();
        first.add("value");
        ColumnData one = new ColumnData("COLUMN_NAME", first);
        List<ColumnData> columnDatas = new ArrayList<>();
        columnDatas.add(one);

        assertEquals(
                "------------------\n" +
                         "| BIG_TABLE_NAME |\n" +
                         "------------------\n" +
                         "|  COLUMN_NAME   |\n" +
                         "------------------\n" +
                         "|     value      |\n" +
                         "------------------\n", tableToString.tableTS(
                        new Table("BIG_TABLE_NAME",
                                columnDatas)));
    }

    @Test
    public void test_printTableLittleTableNameBigColumnName(){
        List<String> first = new ArrayList<>();
        first.add("value");
        ColumnData one = new ColumnData("COLUMN_NAME", first);
        List<ColumnData> columnDatas = new ArrayList<>();
        columnDatas.add(one);

        assertEquals(
                "---------------\n" +
                         "|    NAME     |\n" +
                         "---------------\n" +
                         "| COLUMN_NAME |\n" +
                         "---------------\n" +
                         "|    value    |\n" +
                         "---------------\n", tableToString.tableTS(
                        new Table("NAME",
                                columnDatas)));
    }

    @Test
    public void test_printTableLittleTableNameLittleColumnNameBigValue(){
        List<String> first = new ArrayList<>();
        first.add("BigBigBigValue");
        ColumnData one = new ColumnData("COLUMN", first);
        List<ColumnData> columnDatas = new ArrayList<>();
        columnDatas.add(one);

        assertEquals(
                "------------------\n" +
                         "|     TABLE      |\n" +
                         "------------------\n" +
                         "|     COLUMN     |\n" +
                         "------------------\n" +
                         "| BigBigBigValue |\n" +
                         "------------------\n", tableToString.tableTS(
                        new Table("TABLE",
                                columnDatas)));
    }
}
