package itstep.learning.db;

import com.google.inject.Inject;
import itstep.learning.fs.ConfigWriter;

import java.io.IOException;
import java.sql.*;

public class DbDemo {
    @Inject
    private Connection connection;
    public void run(){
        ConfigWriter configWriter = new ConfigWriter();
        configWriter.createDbIniFile( "src/main/resources/db.ini",
                "mysql",
                "localhost",
                "3308",
                "java_pv222",
                "UTF-8",
                "user221",
                "pass222");
        try {
            ConfigReader reader = new ConfigReader("db.ini");
            Connection conn = reader.getConnection();
            System.out.println("Connected to the database.");
            Statement statement = conn.createStatement();
            String querySQL = "SELECT * FROM application_log ORDER BY run_timestamp DESC";
            ResultSet res = statement.executeQuery(querySQL);
            while (res.next()) {
                int id = res.getInt("id");
                String timestamp = res.getString("run_timestamp");
                System.out.println("ID: " + id + ", Timestamp: " + timestamp);
            }
            res.close();
            statement.close();
            conn.close();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }


//        System.out.println("DbDemo");
////        Driver mySqlDriver;
//        try {
////            //створюємо об'єкт драйвера СУБД
////            mySqlDriver = new com.mysql.cj.jdbc.Driver();
////            // реєструємо його
////            DriverManager.registerDriver(mySqlDriver);
////
////            Connection connection = DriverManager.getConnection(
////                    "jdbc:mysql://localhost:3308/java_pv222" +
////                            "?useUnicode=true&characterEncoding=utf8",
////                    "user221",
////                    "pass222");
//            Statement statement = connection.createStatement();
//            ResultSet res = statement.executeQuery("SHOW DATABASES");
//            while (res.next()) {
//                System.out.println(res.getString(1));  //!!!
//                // У JDBC нумерація полів починається з 1
//            }
//            res.close();
//            statement.close();
//            // у кінці роботи звільняємо його
////            DriverManager.deregisterDriver(mySqlDriver);
////            mySqlDriver = null;
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
    }
}
/*
    JDBC - Java DB Connectivity - тезнологія доступу до данних, еквівалентна ADO.NET (C#) або PDO (PHP)
        - попередньо створюємо БД та користувача до неї
        - підбираємо конектор (драйвер БД) для відповідної СУБД (MySQL), додаємо його до проекту (pom.xml)
        -

*/
