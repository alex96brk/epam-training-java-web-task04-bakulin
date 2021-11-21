package by.epamtc.bakulin.io;

import by.epamtc.bakulin.dao.exception.DAOException;
import by.epamtc.bakulin.dao.exception.general.FileAccessException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class IOConnectorTXT {

    private static final Properties PROPERTIES;

    private static String PROPERTY_PATH = "out/production/epam-training-java-web-task04-bakulin/resources/application.properties";

    public static Properties getProperties() {
        return PROPERTIES;
    }

    static {
        PROPERTIES = new Properties();
        try {
            PROPERTIES.load(new FileInputStream(PROPERTY_PATH));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setPropertyPath(String path) {
        PROPERTY_PATH = path;
    }

    public static List<String> readDocumentData(String propertyName) throws FileAccessException {
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

        } catch (IOException exception) {
            throw new FileAccessException(exception);
        }
        return data;
    }

    public static void writeDataLine(String sourceConnectionProperty, String stringData) throws FileAccessException {
        writeDataLine(sourceConnectionProperty, stringData, true);
    }

    public static void writeDataLine(String sourceConnectionProperty, String data, boolean isAppend) throws FileAccessException {
        String path = PROPERTIES.getProperty(sourceConnectionProperty);
        File file = new File(path);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, isAppend))) {
            if (!file.exists()) {
                throw new FileNotFoundException(String.format("File not found: %s", path));
            }
            bufferedWriter.write(data);
        } catch (IOException exception) {
            throw new FileAccessException(exception);
        }
    }

    public static void deleteDataLine(String sourceConnectionProperty, String cacheConnectionProperty, String dataToDelete) throws FileAccessException {
        updateDataLine(sourceConnectionProperty, cacheConnectionProperty, dataToDelete, "");
    }

    public static void updateDataLine(String sourceConnectionProperty, String cacheConnectionProperty, String dataToUpdate, String newDataLine) throws FileAccessException {
        String source_path = PROPERTIES.getProperty(sourceConnectionProperty);
        String cache_path = PROPERTIES.getProperty(cacheConnectionProperty);

        File sourceFile = new File(source_path);
        File cacheFile = new File(cache_path);

        BufferedReader bufferedReaderSource = null;
        BufferedWriter bufferedWriterCache = null;

        try {
            bufferedReaderSource = new BufferedReader(new FileReader(source_path));
            bufferedWriterCache = new BufferedWriter(new FileWriter(cacheFile, true));
            String line = null;
            while ((line = bufferedReaderSource.readLine()) != null) {
                if (!line.equalsIgnoreCase(dataToUpdate)) {
                    bufferedWriterCache.write(line);
                    bufferedWriterCache.newLine();
                }
            }
            bufferedWriterCache.write(newDataLine);
        } catch (IOException exception) {
            throw new FileAccessException(exception);
        } finally {
            try {
                bufferedReaderSource.close();
                bufferedWriterCache.close();
            } catch (IOException exception) {
                throw new FileAccessException(exception);
            }
            sourceFile.delete();
            cacheFile.renameTo(sourceFile);
        }
    }

}
