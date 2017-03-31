package ua.com.juja.sqlweb.service;

import ua.com.juja.sqlweb.model.Table;

import java.util.ArrayList;

public class TableToString {

    public  String tableToString (Table table){

        if(table == null) { return ""; }

        StringBuilder tableString = new StringBuilder();
        ArrayList<Integer> maxLength = new ArrayList<>();
        int lengthSum = calculatesLength(table, maxLength);

        char[] line = lineCreator(lengthSum);

        char [] containerTN = new char[lengthSum - 1];
        char [] reBuildTN = table.getTableName().toCharArray();
        reBuilder(containerTN, reBuildTN);

        lineString(tableString, line);

        tableNameString(tableString, containerTN);

        lineString(tableString, line);

        columnNameString(table, tableString, maxLength);

        lineString(tableString, line);

        valueString(table, tableString, maxLength);

        lineString(tableString, line);

        return tableString.toString();
    }

    private void tableNameString(StringBuilder tableString, char[] containerTN) {

        tableString.append('|' + new String(containerTN) + '|' + "\n");

    }

    private void columnNameString(Table table, StringBuilder tableString, ArrayList<Integer> maxLength) {
        for (int i = 0; i < table.getTableData().size(); i++){

            String columnName = table.getTableData().get(i).columnName();

            char [] containerCN = new char[maxLength.get(i) + 2];
            char [] reBuildCN = columnName.toCharArray();

            reBuilder(containerCN, reBuildCN);

            printData(table, tableString, i, containerCN);
        }

        lineBreak(tableString);
    }

    private void valueString(Table table, StringBuilder tableString, ArrayList<Integer> maxLength) {
        for (int i = 0; i < table.getTableData().get(0).getValue().size(); i++){

            for (int j = 0; j < table.getTableData().size(); j++) {

                String dataValue = table.getTableData().get(j).getValue().get(i);
                dataValue = nullable(dataValue);

                char [] containerDV = new char[maxLength.get(j) + 2];
                char [] reBuildDV = dataValue.toCharArray();

                reBuilder(containerDV, reBuildDV);

                printData(table, tableString, j, containerDV);

            }

            lineBreak(tableString);
        }
    }

    private void lineString(StringBuilder tableString, char[] line) {
        tableString.append(new String(line) + "\n");
    }

    private void printData(Table table, StringBuilder tableString, int index, char[] container) {
        if(index == table.getTableData().size() - 1){
            tableString.append('|' + new String(container) + '|');
        } else {
            tableString.append('|' + new String(container));
        }
    }

    private void lineBreak(StringBuilder tableString) {
        tableString.append("\n");
    }

    private int calculatesLength(Table table, ArrayList<Integer> maxLength) {

        int lengthSum = 0;
        Integer tableNameLength = table.getTableName().length();

        for (int j = 0; j < table.getTableData().size(); j++) {

            String tempDataObject;
            if(table.getTableData().get(j).getValue().size() == 0){
                tempDataObject = "";
            } else {

                tempDataObject = table.getTableData().get(j).getValue().get(0);
                tempDataObject = nullable(tempDataObject);
            }

            Integer dataLength = tempDataObject.length();
            Integer columnNameLength = table.getTableData().get(j).columnName().length();

            for (int i = 0; i < table.getTableData().get(j).getValue().size(); i++) {

                String tempObj = table.getTableData().get(j).getValue().get(i);
                tempObj = nullable(tempObj);

                Integer tempIns = tempObj.length();
                if (dataLength < tempIns) {
                    dataLength = tempIns;
                }
            }

            if(columnNameLength > dataLength){
                dataLength = columnNameLength;
            }
            maxLength.add(dataLength);
            lengthSum += dataLength + 3;
        }

        int opportunityBag = 0;

        for (int c = 0; c < maxLength.size(); c++){
            opportunityBag += maxLength.get(c);
        }

        if(tableNameLength > opportunityBag){
            lengthSum = tableNameLength + 3;
            maxLength.clear();
            maxLength.add(tableNameLength);
        }

        return lengthSum;
    }

    private char[] lineCreator(int lengthSum) {
        char [] line = new char[lengthSum + 1];
        for (int q = 0; q < line.length; q++) {
            line[q] = '-';
        }
        return line;
    }

    private void reBuilder(char[] container, char[] reBuild) {
        for (int i = 0; i < container.length; i++) {
            container [i] = ' ';
        }

        int fromTo = (container.length - reBuild.length)/2;

        for (int i = fromTo, j = 0; j < reBuild.length; i++, j++) {
            container[i] = reBuild[j];
        }
    }

    private String nullable(String string) {
        if (string == null){
            string = "null";
        }
        return string;
    }

}
