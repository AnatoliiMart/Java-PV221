package itstep.learning.db;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConfigReader {
    private Map<String, String> properties = new HashMap<>();

    public ConfigReader(String resourcePath) throws IOException {
        // Загрузка параметров из ресурса db.ini
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }
            try (BufferedInputStream bis = new BufferedInputStream(is)) {
                byte[] buffer = new byte[bis.available()];
                bis.read(buffer);
                String content = new String(buffer);
                String[] lines = content.split("\n");
                for (String line : lines) {
                    if (!line.trim().isEmpty() && line.contains("=")) {
                        String[] parts = line.split("=", 2);
                        if (parts.length == 2) {
                            properties.put(parts[0].trim(), parts[1].trim());
                        }
                    }
                }
            }
        }
    }
    public Connection getConnection() throws SQLException {
        String dbms = properties.get("dbms");
        String host = properties.get("host");
        String port = properties.get("port");
        String schema = properties.get("schema");
        String encoding = properties.get("encoding");
        String url = String.format(
                "jdbc:%s://%s:%s/%s?characterEncoding=%s",
                dbms, host, port, schema, encoding
        );
        String user = properties.get("user");
        String password = properties.get("password");
        return DriverManager.getConnection(url, user, password);
    }
}
