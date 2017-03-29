package ua.com.juja.sqlweb.control.commands;

public interface Command  {

    boolean isProcessed(String command);
    void  process(String command);

}
