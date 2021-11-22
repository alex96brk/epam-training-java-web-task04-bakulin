package by.epamtc.bakulin.dao.io.xlsx;

import by.epamtc.bakulin.dao.exception.general.FileAccessException;
import by.epamtc.bakulin.entity.Role;
import by.epamtc.bakulin.entity.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UserXLSXConnector {

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

    public static List<User> readDocumentData(String propertyName) throws FileAccessException {
        propertyName = PROPERTIES.getProperty("users.xlsx.source.path");
        List<User> data = new ArrayList<>();
        try(Workbook workbook = new XSSFWorkbook(propertyName)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() > 0) {
                    Iterator<Cell> cellIterator = row.cellIterator();
                    int cellNumber = 0;
                    User user = new User();
                    while (cellIterator.hasNext()) {
                        Cell cell =cellIterator.next();
                        switch (cellNumber) {
                            case 0:
                                int userId = (int) cell.getNumericCellValue();
                                user.setUserId(userId);
                                break;
                            case 1:
                                String userName = cell.getStringCellValue();
                                user.setUserName(userName);
                                break;
                            case 2:
                                String userLastName = cell.getStringCellValue();
                                user.setLastName(userLastName);
                                break;
                            case 3:
                                String userFirstName = cell.getStringCellValue();
                                user.setFirstName(userFirstName);
                                break;
                            case 4:
                                String userRole = cell.getStringCellValue();
                                if (userRole.equalsIgnoreCase(Role.USER.name())) {
                                    user.setUserRole(Role.USER);
                                }
                                if (userRole.equalsIgnoreCase(Role.ADMIN.name())) {
                                    user.setUserRole(Role.ADMIN);
                                }
                                break;
                            case 5:
                                String password = cell.getStringCellValue();
                                user.setPassword(password);
                                break;
                            default:
                                System.out.println("Error");
                                break;
                        }
                        cellNumber++;
                    }
                    data.add(user);
                }
            }
        } catch (IOException ioException) {
            throw new FileAccessException(ioException);
        }
        return data;
    }

}
