import org.junit.*;
import utility.DB;

public class Test {
    @BeforeClass
    public static void setup() {
        String url = "jdbc:mysql://localhost:3306/ALTR";
        String username = "root";
        String password = "1234";
        DB.createConnection(url, username, password);
    }

    @AfterClass
    public static void teardown(){
        // tear down the connection only once after all the tests in this class
        DB.destroy();
    }

    @org.junit.Test
    public void testColumnCount(){
        DB.runQuery("SELECT * from users");
        int expectedColumnCount = 5;
        // assertion
        Assert.assertEquals(expectedColumnCount, DB.getColumnCount());
    }

    @org.junit.Test
    public void testRowCount(){
        DB.runQuery("SELECT * from users");
        int expectedRowCount = 10;
        // assertion
        Assert.assertEquals(expectedRowCount, DB.getRowCount());
    }






}
