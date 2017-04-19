package ua.com.juja.sqlweb.model;

import org.junit.*;
import ua.com.juja.sqlweb.service.DropAllHelper;
import ua.com.juja.sqlweb.service.Query;
import ua.com.juja.sqlweb.service.TableToString;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JDBCDatabaseManagerTest {

    private DatabaseManager manager;
    private TableToString tableToString = new TableToString();

    @BeforeClass
    public static void dropAll(){
        DropAllHelper.dropAll();
    }

    @AfterClass
    public static  void purge() {
        DropAllHelper.purgeRecycle();
    }

    @Before
    public void start(){
            manager = new JDBCDatabaseManager(new Query());
        }

    private void connectTestPass() {
        try {
            manager.connect("localhost","test", "pass");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void dropTable() throws SQLException {
        manager.drop(getTableName());
    }

    private void tryDropTable() {
        try {
            dropTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dropSEQ() throws SQLException {
        manager.cudQuery("DROP SEQUENCE FIRST_SEQ");
    }

    private List<String> getNewTable1col() {
        List<String> settings = new ArrayList<>();
        settings.add("TEST VARCHAR2(20 BYTE) NOT NULL");
        return settings;
    }

    private List<String> getNewTable2col() {
        List<String> settings = new ArrayList<>();
        settings.add("TEST VARCHAR2(20 BYTE) NOT NULL");
        settings.add("TEST1  NUMBER(10) NULL");
        return settings;
    }

    private List<String> getNewTable3col() {
        List<String> settings = new ArrayList<>();
        settings.add("TEST VARCHAR2(20 BYTE) NOT NULL");
        settings.add("TEST1  NUMBER (10) NOT NULL");
        settings.add("TEST2  VARCHAR2 (10 BYTE) NULL");
        return settings;
    }

    private List<String> getNewTAble5col() {
        List<String> settings = new ArrayList<>();
        settings.add("TEST VARCHAR2(20 BYTE) NOT NULL");
        settings.add("TEST1  NUMBER (10) NULL");
        settings.add("TEST2 VARCHAR2(10 BYTE) NULL");
        settings.add("TEST3 DATE NULL");
        settings.add("TEST4 NUMBER(10) NOT NULL");
        return settings;
    }

    private String getTableName(){return "FIRST";}

    private List<String[]> getInsertFor5Col() {
        List<String[]> insert = new ArrayList<>();
        insert.add(new String[]{"TEST","'Hello'"});
        insert.add(new String[]{"TEST1","21"});
        insert.add(new String[]{"TEST3","to_date('19960321','YYYYMMDD')"});
        insert.add(new String[]{"TEST4","50"});
        return insert;
    }

    private List<String[]> getInsertFor5ColOther() {
        List<String[]> insert = new ArrayList<>();
        insert.add(new String[]{"TEST","'Go'"});
        insert.add(new String[]{"TEST1","5556"});
        insert.add(new String[]{"TEST2","'Point'"});
        insert.add(new String[]{"TEST3","to_date('20160501','YYYYMMDD')"});
        insert.add(new String[]{"TEST4","59"});
        return insert;
    }

    private List<String[]> getInsertFor3Col() {
        List<String[]> insert = new ArrayList<>();
        insert.add(new String[]{"TEST","'Hello'"});
        insert.add(new String[]{"TEST1","21"});
        insert.add(new String[]{"TEST2","'World'"});
        return insert;
    }

    @Test
    public void test_connection(){
        connectTestPass();
        Assert.assertTrue(manager.isConnect());
        }

    @Test
    public void test_connectionNull(){
        Assert.assertFalse(manager.isConnect());
    }

    @Test
    public void test_connectionFail() {
        try {
            manager.connect("ewe", "werer", "localhost");
        } catch (SQLException e) {
            Assert.assertFalse(manager.isConnect());
        }

    }

    @Test
    public void test_etAllTableNames() throws SQLException {
        connectTestPass();
        List<String> settings = getNewTable1col();
        manager.createWithoutPK(getTableName(), settings);
        Table table = manager.getTableNames();
        String result = tableToString.tableTS(table);
        String actualResult =
                "--------------\n" +
                "| ALL_TABLES |\n" +
                "--------------\n" +
                "| TABLE_NAME |\n" +
                "--------------\n" +
                "|   FIRST    |\n" +
                "--------------\n";

        dropTable();
        assertEquals(actualResult, result);

    }

    @Test
    public void test_getAllTableNamesEmpty() throws SQLException {

        connectTestPass();

        Table table = manager.getTableNames();
        String result = tableToString.tableTS(table);
        String actualResult =
                "--------------\n" +
                "| ALL_TABLES |\n" +
                "--------------\n" +
                "| TABLE_NAME |\n" +
                "--------------\n" +
                "--------------\n";
        assertEquals(actualResult, result);

    }

    @Test
    public void test_getAllColumnNamesFromTableWrongInput() throws SQLException {
        connectTestPass();

        Table  table = manager.getColumnNames("CCC");
        String result = tableToString.tableTS(table);
        String actualResult =
                "---------------\n" +
                "|     CCC     |\n" +
                "---------------\n" +
                "| COLUMN_NAME |\n" +
                "---------------\n" +
                "---------------\n";
        assertEquals(actualResult, result);

    }

    @Test
    public void test_getAllColumnNamesFromTable() throws SQLException {
        connectTestPass();
        List<String> settings = getNewTable1col();
        manager.createWithoutPK(getTableName(), settings);

        Table table = manager.getColumnNames(getTableName());

        String result = tableToString.tableTS(table);
        String actualResult =
                "---------------\n" +
                "|    FIRST    |\n" +
                "---------------\n" +
                "| COLUMN_NAME |\n" +
                "---------------\n" +
                "|    TEST     |\n" +
                "---------------\n";

        dropTable();
        assertEquals(actualResult, result);

    }

    @Test
    public void test_getDataTypeAllColumnsFromTable() throws SQLException {
        connectTestPass();
        List<String> settings = getNewTAble5col();
        manager.createWithoutPK(getTableName(), settings);
        Table table = manager.getAllTypeColumns(getTableName());
        String result = tableToString.tableTS(table);
        String actualResult =
                        "-----------------------------------------\n" +
                        "|                 FIRST                 |\n" +
                        "-----------------------------------------\n" +
                        "| COLUMN_NAME |  DATA_TYPE   | NULLABLE |\n" +
                        "-----------------------------------------\n" +
                        "|    TEST     | VARCHAR2(20) |    N     |\n" +
                        "|    TEST1    |  NUMBER(22)  |    Y     |\n" +
                        "|    TEST2    | VARCHAR2(10) |    Y     |\n" +
                        "|    TEST3    |   DATE(7)    |    Y     |\n" +
                        "|    TEST4    |  NUMBER(22)  |    N     |\n" +
                        "-----------------------------------------\n";
        dropTable();
        assertEquals(actualResult, result);

    }

    @Test
    public void test_getDataTypeAllColumnsFromTableWrongInput() throws SQLException {
        connectTestPass();

        Table  table = manager.getAllTypeColumns("CCC");
        String result = tableToString.tableTS(table);
        String actualResult =
                "--------------------------------------\n" +
                "|                CCC                 |\n" +
                "--------------------------------------\n" +
                "| COLUMN_NAME | DATA_TYPE | NULLABLE |\n" +
                "--------------------------------------\n" +
                "--------------------------------------\n";

        assertEquals(actualResult, result);
    }

    @Test
    public void test_getDataTypeColumnFromTable() throws SQLException {

        connectTestPass();
        List<String> settings = getNewTAble5col();
        manager.createWithoutPK(getTableName(), settings);

        Table table = manager.getTypeColumn(getTableName(), "TEST1");

        String result = tableToString.tableTS(table);
        String actualResult =
                        "---------------------------------------\n" +
                        "|                FIRST                |\n" +
                        "---------------------------------------\n" +
                        "| COLUMN_NAME | DATA_TYPE  | NULLABLE |\n" +
                        "---------------------------------------\n" +
                        "|    TEST1    | NUMBER(22) |    Y     |\n" +
                        "---------------------------------------\n";

        dropTable();
        assertEquals(actualResult, result);
    }

    @Test
    public void test_getDataTypeColumnFromTableWrongInput() throws SQLException {
        connectTestPass();

        Table table = manager.getTypeColumn("CCC", "TAT");

        String result = tableToString.tableTS(table);
        String actualResult =
                "--------------------------------------\n" +
                "|                CCC                 |\n" +
                "--------------------------------------\n" +
                "| COLUMN_NAME | DATA_TYPE | NULLABLE |\n" +
                "--------------------------------------\n" +
                "--------------------------------------\n";

        assertEquals(actualResult, result);
    }

    @Test
    public void test_createTableWithoutPK() {
        connectTestPass();
        try {
            List<String> settings = getNewTable1col();
            manager.createWithoutPK(getTableName(), settings);
            dropTable();
            assertTrue(true);
        } catch (SQLException e) {
            tryDropTable();
            assertTrue(false);
        }
    }

    @Test
    public void test_createTableWithoutPKWrongInput() {
        connectTestPass();
        try {
            manager.createWithoutPK("hs69", new ArrayList<String>());
            assertTrue(false);
        } catch (SQLException e) {
            assertTrue(true);
        }
    }

    @Test
    public void test_createTableCreatePK() {
        connectTestPass();
        try {
            List<String> settings = getNewTable2col();
            manager.createWithoutPK(getTableName(), settings);
            manager.createCreatePK(getTableName(), "TEST1");
            dropTable();
            assertTrue(true);
        } catch (SQLException e) {
            tryDropTable();
            assertTrue(false);
        }
    }

    @Test
    public void test_createTableCreatePKWrongInput() {
        connectTestPass();
        try {
            List<String> settings = getNewTable2col();
            manager.createWithoutPK(getTableName(), settings);
            manager.createCreatePK(getTableName(), "TEST23");
            dropTable();
            assertTrue(false);
        } catch (SQLException e) {
            tryDropTable();
            assertTrue(true);
        }
    }

    @Test
    public void test_createTableSequenceForPK() {
        connectTestPass();
        try {
            List<String> settings = getNewTable3col();
            manager.createWithoutPK(getTableName(), settings);
            manager.createCreatePK(getTableName(), "TEST1");
            manager.createSequencePK(getTableName(),1L);
            dropTable();
            dropSEQ();
            assertTrue(true);
        } catch (SQLException e) {
            assertTrue(false);
        }
    }

    @Test
    public void test_createTableSequenceForPKWrongInput() {
        connectTestPass();
        try {
            List<String> settings = getNewTable3col();
            manager.createWithoutPK(getTableName(), settings);
            manager.createCreatePK(getTableName(), "TEST2");
            manager.createSequencePK(getTableName(), null);
            dropTable();
            dropSEQ();
            assertTrue(false);
        } catch (SQLException e) {
            tryDropTable();
            assertTrue(true);
        }
    }

    @Test
    public void test_clear() throws SQLException {
        connectTestPass();
        List<String> settings = getNewTable3col();
        List<String[]> insert = getInsertFor3Col();
        manager.createWithoutPK(getTableName(), settings);
        manager.insert(getTableName(), insert, false);
        manager.clear(getTableName());
        Table table = manager.read(getTableName());
        String result = tableToString.tableTS(table);
        String actualResult =
                "------------------------\n" +
                "|        FIRST         |\n" +
                "------------------------\n" +
                "| TEST | TEST1 | TEST2 |\n" +
                "------------------------\n" +
                "------------------------\n";
        dropTable();
        assertEquals(actualResult, result);
    }

    @Test
    public void test_clearPK() throws SQLException {
        connectTestPass();
        List<String> settings = getNewTable3col();
        List<String[]> insert = getInsertFor3Col();
        manager.createWithoutPK(getTableName(), settings);
        manager.createCreatePK(getTableName(), "TEST1");
        manager.createSequencePK(getTableName(), 2L);
        manager.insert(getTableName(), insert, true);
        manager.clear(getTableName());
        Table table = manager.read(getTableName());
        String result = tableToString.tableTS(table);
        String actualResult =
                        "------------------------\n" +
                        "|        FIRST         |\n" +
                        "------------------------\n" +
                        "| TEST | TEST1 | TEST2 |\n" +
                        "------------------------\n" +
                        "------------------------\n";
        dropSEQ();
        dropTable();
        assertEquals(actualResult, result);
    }

    @Test
    public void test_drop() throws SQLException {
        connectTestPass();
        List<String> settings = getNewTable3col();
        manager.createWithoutPK(getTableName(), settings);
        manager.createWithoutPK("SECOND", settings);
        manager.drop("SECOND");
        Table table = manager.getTableNames();
        String result = tableToString.tableTS(table);
        String actualResult =
                "--------------\n" +
                "| ALL_TABLES |\n" +
                "--------------\n" +
                "| TABLE_NAME |\n" +
                "--------------\n" +
                "|   FIRST    |\n" +
                "--------------\n";
        dropTable();
        assertEquals(actualResult, result);
    }

    @Test
    public void test_dropWrongImport() throws SQLException {
        connectTestPass();
        List<String> settings = getNewTable3col();
        manager.createWithoutPK(getTableName(), settings);
        manager.createWithoutPK("SECOND", settings);
        try {
            manager.drop("Gdhd");
        } catch (SQLException e){
            manager.drop("SECOND");
            dropTable();
            Assert.assertTrue(true);
        }
    }

    @Test
    public void test_readInsert() throws SQLException {
        connectTestPass();
        List<String> settings = getNewTAble5col();
        List<String[]> insert = getInsertFor5Col();
        List<String[]> insert1 = getInsertFor5ColOther();
        manager.createWithoutPK(getTableName(), settings);
        manager.insert(getTableName(), insert, false);
        manager.insert(getTableName(), insert1, false);
        Table table = manager.read(getTableName());
        String result = tableToString.tableTS(table);
        String actualResult =
                                        "-------------------------------------------------------\n" +
                                        "|                        FIRST                        |\n" +
                                        "-------------------------------------------------------\n" +
                                        "| TEST  | TEST1 | TEST2 |        TEST3        | TEST4 |\n" +
                                        "-------------------------------------------------------\n" +
                                        "| Hello |  21   | null  | 1996-03-21 00:00:00 |  50   |\n" +
                                        "|  Go   | 5556  | Point | 2016-05-01 00:00:00 |  59   |\n" +
                                        "-------------------------------------------------------\n";

        dropTable();
        assertEquals(actualResult, result);

    }

    @Test
    public void test_readWrongImport() {
        connectTestPass();
        try {
            manager.read("SomeNAme");
        } catch (SQLException e) {
            assertEquals("ORA-00942: table or view does not exist", e.getMessage());
        }
    }

    @Test
    public void test_readSettings() throws SQLException {
        connectTestPass();
        List<String> settings = getNewTAble5col();
        List<String[]> insert = getInsertFor5Col();
        List<String[]> insert1 = getInsertFor5ColOther();
        ArrayList<String[]> settingsFine = new ArrayList<>();
        settingsFine.add(new String[]{"TEST", "'Hello'"});
        manager.createWithoutPK(getTableName(), settings);
        manager.insert(getTableName(), insert, false);
        manager.insert(getTableName(), insert1, false);
        Table table = manager.readSet(getTableName(), settingsFine);
        String result = tableToString.tableTS(table);
        String actualResult =
                                "-------------------------------------------------------\n" +
                                "|                        FIRST                        |\n" +
                                "-------------------------------------------------------\n" +
                                "| TEST  | TEST1 | TEST2 |        TEST3        | TEST4 |\n" +
                                "-------------------------------------------------------\n" +
                                "| Hello |  21   | null  | 1996-03-21 00:00:00 |  50   |\n" +
                                "-------------------------------------------------------\n";

        dropTable();
        assertEquals(actualResult, result);

    }

    @Test
    public void test_reedSettingsWrongImport() throws SQLException {
        connectTestPass();
        List<String> settings = getNewTAble5col();
        List<String[]> insert = getInsertFor5Col();
        List<String[]> insert1 = getInsertFor5ColOther();
        List<String[]> settingsFine = new ArrayList<>();
        settingsFine.add(new String[]{"TEST", "'GGTHhhh'"});
        manager.createWithoutPK(getTableName(), settings);
        manager.insert(getTableName(), insert, false);
        manager.insert(getTableName(), insert1, false);
        Table table = manager.readSet(getTableName(), settingsFine);
        String result = tableToString.tableTS(table);
        String actualResult =
                                "----------------------------------------\n" +
                                "|                FIRST                 |\n" +
                                "----------------------------------------\n" +
                                "| TEST | TEST1 | TEST2 | TEST3 | TEST4 |\n" +
                                "----------------------------------------\n" +
                                "----------------------------------------\n";

        dropTable();
        assertEquals(actualResult, result);

    }

    @Test
    public void test_delete() throws SQLException {
        connectTestPass();
        List<String> settings = getNewTAble5col();
        List<String[]> insert = getInsertFor5Col();
        List<String[]> insert1 = getInsertFor5ColOther();
        List<String[]> settingsFine = new ArrayList<>();
        settingsFine.add(new String[]{"TEST", "'Hello'"});
        manager.createWithoutPK(getTableName(), settings);
        manager.insert(getTableName(), insert, false);
        manager.insert(getTableName(), insert1, false);
        manager.delete(getTableName(), settingsFine);
        Table table = manager.read(getTableName());
        String result = tableToString.tableTS(table);
        String actualResult =
                                        "------------------------------------------------------\n" +
                                        "|                       FIRST                        |\n" +
                                        "------------------------------------------------------\n" +
                                        "| TEST | TEST1 | TEST2 |        TEST3        | TEST4 |\n" +
                                        "------------------------------------------------------\n" +
                                        "|  Go  | 5556  | Point | 2016-05-01 00:00:00 |  59   |\n" +
                                        "------------------------------------------------------\n";

        dropTable();
        assertEquals(actualResult, result);

    }

    @Test
    public void test_deleteWrongImport() throws SQLException {
        connectTestPass();
        List<String> settings = getNewTAble5col();
        List<String[]> insert = getInsertFor5Col();
        List<String[]> insert1 = getInsertFor5ColOther();
        List<String[]> settingsFine = new ArrayList<>();
        settingsFine.add(new String[]{"TEST", "'FFCVvvv'"});
        manager.createWithoutPK(getTableName(), settings);
        manager.insert(getTableName(), insert, false);
        manager.insert(getTableName(), insert1, false);
        try {
            manager.delete(getTableName(), settingsFine);
        } catch (Exception e){
            //do nothing
        }
        Table table = manager.read(getTableName());
        String result = tableToString.tableTS(table);
        String actualResult =
                                        "-------------------------------------------------------\n" +
                                        "|                        FIRST                        |\n" +
                                        "-------------------------------------------------------\n" +
                                        "| TEST  | TEST1 | TEST2 |        TEST3        | TEST4 |\n" +
                                        "-------------------------------------------------------\n" +
                                        "| Hello |  21   | null  | 1996-03-21 00:00:00 |  50   |\n" +
                                        "|  Go   | 5556  | Point | 2016-05-01 00:00:00 |  59   |\n" +
                                        "-------------------------------------------------------\n";

        dropTable();
        assertEquals(actualResult, result);

    }

    @Test
    public void test_update() throws SQLException {
        connectTestPass();
        List<String> settings = getNewTAble5col();
        List<String[]> insert = getInsertFor5Col();
        List<String[]> insert1 = getInsertFor5ColOther();
        List<String[]> settingsFine = new ArrayList<>();
        settingsFine.add(new String[]{"TEST", "'Hello'"});
        List<String[]> settingsHowUpdate = new ArrayList<>();
        settingsHowUpdate.add(new String[]{"TEST", "'World'"});
        manager.createWithoutPK(getTableName(), settings);
        manager.insert(getTableName(), insert, false);
        manager.insert(getTableName(), insert1, false);
        manager.update(getTableName(), settingsHowUpdate, settingsFine);
        Table table = manager.read(getTableName());
        String result = tableToString.tableTS(table);
        String actualResult =
                                "-------------------------------------------------------\n" +
                                "|                        FIRST                        |\n" +
                                "-------------------------------------------------------\n" +
                                "| TEST  | TEST1 | TEST2 |        TEST3        | TEST4 |\n" +
                                "-------------------------------------------------------\n" +
                                "| World |  21   | null  | 1996-03-21 00:00:00 |  50   |\n" +
                                "|  Go   | 5556  | Point | 2016-05-01 00:00:00 |  59   |\n" +
                                "-------------------------------------------------------\n";

        dropTable();
        assertEquals(actualResult, result);

    }

    @Test
    public void test_updateWrongImport() throws SQLException {
        connectTestPass();
        List<String> settings = getNewTAble5col();
        List<String[]> insert = getInsertFor5Col();
        List<String[]> insert1 = getInsertFor5ColOther();
        List<String[]> settingsFine = new ArrayList<>();
        settingsFine.add(new String[]{"TEST", "'Ghjk'"});
        ArrayList<String[]> settingsHowUpdate = new ArrayList<>();
        settingsHowUpdate.add(new String[]{"TEST", "'World'"});
        manager.createWithoutPK(getTableName(), settings);
        manager.insert(getTableName(), insert, false);
        manager.insert(getTableName(), insert1, false);
        manager.update(getTableName(), settingsHowUpdate, settingsFine);
        Table table = manager.read(getTableName());
        String result = tableToString.tableTS(table);
        String actualResult =
                                "-------------------------------------------------------\n" +
                                "|                        FIRST                        |\n" +
                                "-------------------------------------------------------\n" +
                                "| TEST  | TEST1 | TEST2 |        TEST3        | TEST4 |\n" +
                                "-------------------------------------------------------\n" +
                                "| Hello |  21   | null  | 1996-03-21 00:00:00 |  50   |\n" +
                                "|  Go   | 5556  | Point | 2016-05-01 00:00:00 |  59   |\n" +
                                "-------------------------------------------------------\n";

        dropTable();
        assertEquals(actualResult, result);

    }
}
