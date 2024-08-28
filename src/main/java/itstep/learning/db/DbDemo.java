package itstep.learning.db;

import com.google.inject.Inject;

import java.sql.*;

public class DbDemo {
    @Inject
    private Connection connection;
    public void run(){
        System.out.println("DbDemo");
//        Driver mySqlDriver;
        try {
//            //створюємо об'єкт драйвера СУБД
//            mySqlDriver = new com.mysql.cj.jdbc.Driver();
//            // реєструємо його
//            DriverManager.registerDriver(mySqlDriver);
//
//            Connection connection = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3308/java_pv222" +
//                            "?useUnicode=true&characterEncoding=utf8",
//                    "user221",
//                    "pass222");
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SHOW DATABASES");
            while (res.next()) {
                System.out.println(res.getString(1));  //!!!
                // У JDBC нумерація полів починається з 1
            }
            res.close();
            statement.close();
            // у кінці роботи звільняємо його
//            DriverManager.deregisterDriver(mySqlDriver);
//            mySqlDriver = null;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
/*
    JDBC - Java DB Connectivity - тезнологія доступу до данних, еквівалентна ADO.NET (C#) або PDO (PHP)
        - попередньо створюємо БД та користувача до неї
        - підбираємо конектор (драйвер БД) для відповідної СУБД (MySQL), додаємо його до проекту (pom.xml)
        -

*/
