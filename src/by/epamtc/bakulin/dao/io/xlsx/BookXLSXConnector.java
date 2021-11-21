package by.epamtc.bakulin.dao.io.xlsx;

import by.epamtc.bakulin.dao.io.IOEntityBuilder;
import by.epamtc.bakulin.entity.Book;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BookXLSXConnector implements IOEntityBuilder<Book> {

    private static final Properties PROPERTIES;

    private static String PROPERTY_PATH = "out/production/epam-training-java-web-task04-bakulin/resources/application.properties";

    public static Properties getProperties() {
        return PROPERTIES;
    }

    public static void setPropertyPath(String path) {
        PROPERTY_PATH = path;
    }

    static {
        PROPERTIES = new Properties();
        try {
            PROPERTIES.load(new FileInputStream(PROPERTY_PATH));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<String> readDocumentData(String propertyName) {
        propertyName = PROPERTIES.getProperty("books.xlsx.source.path");
        List<String> data = new ArrayList<>();

        try(Workbook workbook = new XSSFWorkbook(propertyName)) {


        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return data;
    }

    @Override
    public List<Book> parseFileData(List<String> fileData) {
        return null;
    }

    @Override
    public String[] parseStringLine(String line) {
        return new String[0];
    }

    @Override
    public Book buildEntity(String[] entityProps) {
        return null;
    }
}
