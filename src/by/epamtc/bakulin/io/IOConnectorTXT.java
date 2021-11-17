package by.epamtc.bakulin.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class IOConnectorTXT {

    private static final Properties PROPERTIES;

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

    public static List<String> readDocumentData(String propertyName) {
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

    public static void writeDataLine(String sourceConnectionProperty, String stringData) {
        writeDataLine(sourceConnectionProperty, stringData, true);
    }

    public static void writeDataLine(String sourceConnectionProperty, String data, boolean isAppend) {
        String path = PROPERTIES.getProperty(sourceConnectionProperty);
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

    public static void deleteDataLine(String sourceConnectionProperty, String cacheConnectionProperty, String dataToDelete) {
        updateDataLine(sourceConnectionProperty, cacheConnectionProperty, dataToDelete, "");
    }

    public static void updateDataLine(String sourceConnectionProperty, String cacheConnectionProperty, String dataToUpdate, String newDataLine) {
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
