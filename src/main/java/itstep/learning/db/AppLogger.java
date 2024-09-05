package itstep.learning.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class AppLogger {
    public void logStart() {
        String insertSQL = "INSERT INTO application_log (run_timestamp) VALUES (NOW())";
        try{
            ConfigReader reader = new ConfigReader("db.ini");
            Connection conn = reader.getConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate(insertSQL);
            System.out.println("Application start logged.");
            statement.close();
            conn.close();
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Error logging application start", e);
        }
    }
}
