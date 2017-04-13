package ua.com.juja.sqlweb.model;

import java.util.List;

public class Table {

    private String tableName;
    private List<ColumnData> tableData;

    public  Table (String tableName, List<ColumnData> tableData){
        this.tableName = tableName;
        this.tableData = tableData;
    }

    public String getTableName() {
        return tableName;
    }

    public List<ColumnData> getTableData() {
        return tableData;
    }

}
