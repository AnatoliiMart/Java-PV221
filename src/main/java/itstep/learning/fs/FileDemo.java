package itstep.learning.fs;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileDemo {
    public void run(){
        //Ресурси - файли, шо автоматично переносяться у target/classes
        // і доступні при запуску через ClassLoader
        try(InputStream rs = this.getClass().getClassLoader().getResourceAsStream("db.ini")){
            String content = readStream(rs);
            Map<String,String> ini = new HashMap<>();
            String[] lines = content.split("\n");
            for(String line : lines){
                String[] parts = line.split("=");
                ini.put(parts[0].trim(),parts[1].trim());
            }
            System.out.printf("jdbc:%s://%s:%s/%s",
                    ini.get("dbms"),
                    ini.get("host"),
                    ini.get("port"),
                    ini.get("schema"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void runFile(){
        System.out.println("FileDemo");
        File file = new File("Test.txt");
        // !! Створення об'єку File ні як не впливає на саму файлову систему
        // !! File - об'єкти відповідають як за файли, так і за директорії


        // !! Робота з даними у файлах здійснюється через потоки даних (Stream)
        // !! У Java багаторівнева ієрархія засобів роботи з потоками
        // !! Основні: InputStream / OutputStream - вища абстракція
        // Імплементації: FileInputStream / ...
        ByteArrayOutputStream byteBuilder =  new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int len;
        //try() - try with resource -- аналог using - блок з автоматичним вивільненням некерованих ресурсів
        try(InputStream is = new FileInputStream(file);) {
            System.out.println(readStream(is));
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private String readStream(InputStream inputStream) throws IOException {
        return readStream(inputStream, StandardCharsets.UTF_8);
    }

    private String readStream(InputStream inputStream, Charset charset) throws IOException {
        byte[] buffer = new byte[4096];
        try (ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream();
             BufferedInputStream bis = new BufferedInputStream(inputStream);)
        {
            int len;
            while ((len = bis.read(buffer)) != -1) {
                byteBuilder.write(buffer, 0, len);
            }
            return byteBuilder.toString(charset.name());
        }
    }
    public void createDbIniFile(String filePath, String dbms, String host, String port, String schema, String encoding) {
        String content = String.format(
                        "dbms=%s\n" +
                        "host=%s\n" +
                        "port=%s\n" +
                        "schema=%s\n" +
                        "encoding=%s\n",
                dbms, host, port, schema, encoding
        );

        // Запись в файл
        try (OutputStream os = new BufferedOutputStream(Files.newOutputStream(Paths.get(filePath)))) {
            os.write(content.getBytes());
            System.out.println("Configuration written to " + filePath);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to " + filePath, e);
        }
    }
}
/*
Робота з файлами. Ресурси
*/