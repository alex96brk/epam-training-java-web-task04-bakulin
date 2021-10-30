package by.epamtc.bakulin.io.impl;

import by.epamtc.bakulin.io.IOManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class IOManagerTXT implements IOManager {

    private static Properties PROPERTIES;

    public IOManagerTXT() {}

    public static Properties getProperties() {
        return PROPERTIES;
    }

    static {
        PROPERTIES = new Properties();
        try {
            PROPERTIES.load(new FileInputStream("src/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> readDocumentData(String propertyName) {
        String path = PROPERTIES.getProperty(propertyName);
        List<String> data = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            File textFile = new File(path);
            if (!textFile.exists()) {
                throw new FileNotFoundException(String.format("File not found: %s", path));
            }
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                data.add(line);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return data;
    }

    public void appendDataLine(String propertyName, String data, boolean isAppend) {
        String path = PROPERTIES.getProperty(propertyName);
        File file = new File(path);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, isAppend))) {
            if (!file.exists()) {
                throw new FileNotFoundException(String.format("File not found: %s", path));
            }
            bufferedWriter.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void replaceDataLine(String sourceProperty, String cacheProperty, String oldData, String newData) {
        String source_path = PROPERTIES.getProperty(sourceProperty);
        String cache_path = PROPERTIES.getProperty(cacheProperty);

        File sourceFile = new File(source_path);
        File cacheFile = new File(cache_path);

        BufferedReader bufferedReaderSource = null;
        BufferedWriter bufferedWriterCache = null;

        try {
            bufferedReaderSource = new BufferedReader(new FileReader(source_path));
            bufferedWriterCache = new BufferedWriter(new FileWriter(cacheFile, true));
            String line = null;
            while ((line = bufferedReaderSource.readLine()) != null) {
                if (!line.equalsIgnoreCase(oldData)) {
                    bufferedWriterCache.write(line);
                    bufferedWriterCache.newLine();
                }
            }
            bufferedWriterCache.write(newData);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                bufferedReaderSource.close();
                bufferedWriterCache.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            sourceFile.delete();
            cacheFile.renameTo(sourceFile);
        }
    }

}
