package by.epamtc.bakulin.dao.io.xlsx;

import by.epamtc.bakulin.dao.exception.general.FileAccessException;
import by.epamtc.bakulin.entity.User;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static by.epamtc.bakulin.entity.Role.*;

public class UserXLSXConnector {

    private static final Properties PROPERTIES;

    private static final List<String> XLSX_TABLE_HEADERS = Arrays.asList("userId", "userName", "lastName", "firstName", "Role", "Password");

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
        propertyName = PROPERTIES.getProperty(propertyName);
        List<User> data = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(propertyName)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() > 0) {
                    Iterator<Cell> cellIterator = row.cellIterator();
                    int cellNumber = 0;
                    User user = new User();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
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
                                if (userRole.equalsIgnoreCase(USER.name())) {
                                    user.setUserRole(USER);
                                }
                                if (userRole.equalsIgnoreCase(ADMIN.name())) {
                                    user.setUserRole(ADMIN);
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

    public static void writeDataLine(String sourceConnectionProperty, String cacheConnectionProperty, List<User> data) throws FileAccessException {
        String cache_path = PROPERTIES.getProperty(cacheConnectionProperty);
        String source_path = PROPERTIES.getProperty(sourceConnectionProperty);

        File cacheFile = new File(cache_path);
        File sourceFile = new File(source_path);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("users");
        tableHeaderInit(workbook, sheet);
        int rowIndex = 1;
        for (User user : data) {
            Row row = sheet.createRow(rowIndex);
            row.createCell(0, CellType.NUMERIC).setCellValue(user.getUserId());
            row.createCell(1, CellType.STRING).setCellValue(user.getUserName());
            row.createCell(2, CellType.STRING).setCellValue(user.getLastName());
            row.createCell(3, CellType.STRING).setCellValue(user.getFirstName());
            if (user.getUserRole().equals(USER)) {
                row.createCell(4, CellType.STRING).setCellValue(USER.name());
            }
            if (user.getUserRole().equals(ADMIN)) {
                row.createCell(4, CellType.STRING).setCellValue(ADMIN.name());
            }
            row.createCell(5, CellType.STRING).setCellValue(user.getPassword());
            rowIndex++;
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(cacheFile);
            workbook.write(fileOutputStream);
            workbook.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new FileAccessException(e);
        } catch (IOException e) {
            throw new FileAccessException(e);
        } finally {
            sourceFile.delete();
            cacheFile.renameTo(sourceFile);
        }
    }

    public static void updateDataLine(String sourceConnectionProperty, String cacheConnectionProperty, List<User> data, User updateUser) throws FileAccessException {
        for (User user : data) {
            if (user.getUserId().equals(updateUser.getUserId())) {
                user.setUserId(updateUser.getUserId());
                user.setUserName(updateUser.getUserName());
                user.setLastName(updateUser.getLastName());
                user.setFirstName(updateUser.getFirstName());
                user.setUserRole(updateUser.getUserRole());
                user.setPassword(updateUser.getPassword());
            }
        }
        try {
            writeDataLine(sourceConnectionProperty, cacheConnectionProperty, data);
        } catch (FileAccessException e) {
            throw new FileAccessException(e);
        }
    }

    public static void deleteDataLine(String sourceConnectionProperty, String cacheConnectionProperty, List<User> data, User deleteUser) throws FileAccessException {
        for (int i = 0; i < data.size(); i++) {
            User currentUser = data.get(i);
            if (currentUser.equals(deleteUser)) {
                data.remove(i);
            }
        }
        try {
            writeDataLine(sourceConnectionProperty, cacheConnectionProperty, data);
        } catch (FileAccessException e) {
            throw new FileAccessException(e);
        }
    }

    private static void tableHeaderInit(Workbook workbook, Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < XLSX_TABLE_HEADERS.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(XLSX_TABLE_HEADERS.get(i));

            CellStyle headerStyle = workbook.createCellStyle();

            Font font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight((short) 200);

            headerStyle.setFont(font);

            cell.setCellStyle(headerStyle);
            sheet.autoSizeColumn(i);
        }
    }

}
