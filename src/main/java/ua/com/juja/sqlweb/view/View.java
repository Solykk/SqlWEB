package ua.com.juja.sqlweb.view;

public interface View {

    void write(String message);
    String read();
    void printHistory();
    void writeAndHistory(String toWrite, String toHistory);
    void addHistory(String toHistory);

}
