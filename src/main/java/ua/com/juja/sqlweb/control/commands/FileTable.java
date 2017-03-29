package ua.com.juja.sqlweb.control.commands;

import ua.com.juja.sqlweb.model.DatabaseManager;
import ua.com.juja.sqlweb.model.Table;
import ua.com.juja.sqlweb.service.Correctly;
import ua.com.juja.sqlweb.service.Services;
import ua.com.juja.sqlweb.service.TablePrinter;
import ua.com.juja.sqlweb.service.ViewService;
import ua.com.juja.sqlweb.view.View;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileTable implements Command{

    private DatabaseManager manager;
    private View view;
    private ViewService viewService;
    private Correctly correctly;
    private TablePrinter tablePrinter;

    public FileTable(DatabaseManager manager, View view, Services services) {
        this.manager = manager;
        this.view = view;
        this.viewService = services.getViewService();
        this.correctly = services.getCorrectly();
        this.tablePrinter = services.getTablePrinter();
    }

    @Override
    public boolean isProcessed(String command) {
        return command.toLowerCase().startsWith("filetable|");
    }

    @Override
    public void process(String command) {

        String tableName = correctly.expectedTwo(command);

        try {
            Table request = manager.read(tableName);
            String dataTable = tablePrinter.printTable(request);

            saveConsent(tableName, dataTable);
        } catch (Exception e) {
            viewService.fileTabComCatch(tableName, e.getMessage());
        }
    }

    private void saveConsent(String tableName, String dataTable) throws IOException {
        String response = saveAction();
        if (response.equalsIgnoreCase("y")){

            String reName = nameAction();
            if (reName.equalsIgnoreCase("y")){

                saveFile(tableName, dataTable);
            } else {
                reNameWriter(dataTable);
            }
        } else {
            viewService.fileTabComTryAbort(tableName);
        }
    }

    private void saveFile(String tableName, String dataTable) throws IOException {
        File file = new File(tableName + ".txt");
        fileExistChecker(dataTable, tableName, file);
    }

    private String nameAction() {
        view.write("Имя файла будет соответствовать имени таблицы. Если согланы, введите " + "Y");
        view.write("Если хотите переименовать файл, нажмите Enter");
        return view.read();
    }

    private String saveAction() {
        view.write("Сохранить эти данные в файл? Y/N");
        return view.read();
    }

    private void reNameWriter(String dataTable) throws IOException {
        view.write("Введите название файла: ");
        String name = view.read();
        if (name.equals("")){
            view.write("Название файла не может быть пустым! ");
            reNameWriter(dataTable);
        }

        CharSequence [] taboo = new CharSequence[]{"/", "\\", "<", ">", "?",":", "|" , "*", "\""};
        boolean isCont = false;

        for (CharSequence chars: taboo) {
            if (name.contains(chars)){
                isCont = true;
                break;
            }
        }

        if (isCont){
            view.write("Название файла не мжет содержать " + "/\\<>?:|*\"");
            reNameWriter(dataTable);
            return;
        }
        saveFile(name, dataTable);
    }

    private void fileExistChecker(String dataTable, String name, File file) throws IOException {
        if(file.exists()){

            String read = existAction(name);
            if(read.equalsIgnoreCase("y")){

                nameOkWriter(name, dataTable, file);
            } else {
                reNameWriter(dataTable);
            }
        } else {
            nameOkWriter(name, dataTable, file);
        }
    }

    private String existAction(String name) {
        view.write("Файл с именем " + name + " уже существует! Перезаписать его? Если да, введите " + "Y");
        view.write("Если хотите переименовать файл, нажмите Enter");
        return view.read();
    }

    private void nameOkWriter(String tableName, String dataTable, File file) throws IOException {
        fileInput(dataTable, tableName, file);
        viewService.fileTabComTrySuc(tableName);
    }

    private void fileInput(String dataTable, String name, File file) throws IOException {

        if(file.createNewFile()) {
            try (FileWriter writer = new FileWriter(name + ".txt")) {
                writer.write(dataTable);
                writer.flush();
            }
        }
    }
}
