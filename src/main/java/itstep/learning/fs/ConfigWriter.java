package itstep.learning.fs;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ConfigWriter {

    public void createDbIniFile(String filePath, String dbms, String host, String port, String schema, String encoding, String user, String password) {
        // Формирование содержимого файла db.ini
        String content = String.format(
                "dbms=%s\n" +
                        "host=%s\n" +
                        "port=%s\n" +
                        "schema=%s\n" +
                        "encoding=%s\n" +
                        "user=%s\n" +
                        "password=%s\n",
                dbms, host, port, schema, encoding, user, password
        );

        try (OutputStream os = new BufferedOutputStream(new FileOutputStream(filePath))) {
            os.write(content.getBytes());
            System.out.println("Configuration written to " + filePath);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to " + filePath, e);
        }
    }
}

