package ua.com.juja.sqlweb.model;

import java.util.List;

public class ColumnData {

    private String columnName;
    private List<String> value;

    public ColumnData(String columnName, List<String> value){
        this.columnName = columnName;
        this.value = value;
    }

    public String getColumnName(){
        return columnName;
    }

    public List<String> getValue(){
        return value;
    }
}
