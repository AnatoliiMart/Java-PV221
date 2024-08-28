package itstep.learning.IoC;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbModule extends AbstractModule {
    private Connection connection = null;
    private Driver mySqlDriver = null;
    @Provides // методи провайдери -- керована інжекція
    private Connection getConnection() {
        // кожна точка інжекції типу даних "Connection" буде запускати цей метод
        // i його повернення інжектувати як залежність
        if (connection == null) {
            try{
                mySqlDriver = new com.mysql.cj.jdbc.Driver();
                // реєструємо його
                DriverManager.registerDriver(mySqlDriver);

                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3308/java_pv222" +
                                "?useUnicode=true&characterEncoding=utf8",
                        "user221",
                        "pass222");
            } catch (SQLException e) {
                System.err.println("DbModule::getConnection() "+e.getMessage());
            }
        }
        return connection;
    }
}
