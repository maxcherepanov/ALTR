import java.sql.*;
import com.github.javafaker.Faker;

public class TableCreation {

    static Faker faker = new Faker();
    public static void main(String[] args) throws SQLException {



        String url = "jdbc:mysql://localhost:3306/ALTR";
        String username = "root";
        String password = "1234";


        Connection connection = DriverManager.getConnection(url, username, password);

        String createTableSql = "CREATE TABLE users " +
                "(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), " +
                "age INT, address VARCHAR(255), email VARCHAR(255), INDEX idx_name (name), INDEX idx_email (email))";
        try (PreparedStatement createTableStmt = connection.prepareStatement(createTableSql)) {
            createTableStmt.execute();
        }

        String insertDataSql = "INSERT INTO users (name, age, address, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement insertDataStmt = connection.prepareStatement(insertDataSql)) {
            for (int i = 0; i < 10; i++) {
                insertDataStmt.setString(1, faker.name().fullName() );
                insertDataStmt.setInt(2, (int)(Math.random() * (50) + 1));
                insertDataStmt.setString(3, faker.address().fullAddress());
                insertDataStmt.setString(4, faker.name().firstName() + "@gmail.com");
                insertDataStmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}




