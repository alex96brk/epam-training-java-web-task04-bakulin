package by.epamtc.bakulin.dao.io.xlsx;

import by.epamtc.bakulin.dao.exception.general.FileAccessException;
import by.epamtc.bakulin.entity.Book;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class BookXLSXConnector {

    private static final Properties PROPERTIES;

    private static final List<String> XLSX_TABLE_HEADERS = Arrays.asList("bookId", "bookName", "bookAuthor", "bookGenre");

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

    public static List<Book> readDocumentData(String propertyName) throws FileAccessException {
        propertyName = PROPERTIES.getProperty(propertyName);
        List<Book> data = new ArrayList<>();

        try(Workbook workbook = new XSSFWorkbook(propertyName)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() > 0) {
                    Iterator<Cell> cellIterator = row.cellIterator();
                    int cellNumber = 0;
                    Book book = new Book();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        switch (cellNumber) {
                            case 0:
                                int bookId = (int) cell.getNumericCellValue();
                                book.setBookId(bookId);
                                break;
                            case 1:
                                String bookName = cell.getStringCellValue();
                                book.setBookName(bookName);
                                break;
                            case 2:
                                String bookAuthor = cell.getStringCellValue();
                                book.setBookAuthor(bookAuthor);
                            case 3:
                                String bookGenre = cell.getStringCellValue();
                                book.setBookGenre(bookGenre);
                                break;
                            default:
                                System.out.println("Error");
                                break;
                        }
                        cellNumber++;
                    }
                    data.add(book);
                }
            }

        } catch (IOException ioException) {
            throw new FileAccessException(ioException);
        }
        return data;
    }

    public static void writeDataLine(String sourceConnectionProperty, String cacheConnectionProperty, List<Book> data) throws FileAccessException {
        String cache_path = PROPERTIES.getProperty(cacheConnectionProperty);
        String source_path = PROPERTIES.getProperty(sourceConnectionProperty);

        File cacheFile = new File(cache_path);
        File sourceFile = new File(source_path);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("books");
        tableHeaderInit(workbook, sheet);
        int rowIndex = 1;
        for (Book book : data) {
            Row row = sheet.createRow(rowIndex);
            row.createCell(0, CellType.NUMERIC).setCellValue(book.getBookId());
            row.createCell(1, CellType.STRING).setCellValue(book.getBookName());
            row.createCell(2, CellType.STRING).setCellValue(book.getBookAuthor());
            row.createCell(3, CellType.STRING).setCellValue(book.getBookGenre());
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

    public static void updateDataLine(String sourceConnectionProperty, String cacheConnectionProperty, List<Book> data, Book updateBook) throws FileAccessException {
        for (Book book : data) {
            if (book.getBookId().equals(updateBook.getBookId())) {
                book.setBookId(updateBook.getBookId());
                book.setBookName(updateBook.getBookName());
                book.setBookAuthor(updateBook.getBookAuthor());
                book.setBookGenre(updateBook.getBookGenre());
            }
        }
        try {
            writeDataLine(sourceConnectionProperty, cacheConnectionProperty, data);
        } catch (FileAccessException e) {
            throw new FileAccessException(e);
        }
    }

    public static void deleteDataLine(String sourceConnectionProperty, String cacheConnectionProperty, List<Book> data, Book deleteBook) throws FileAccessException {
        for (int i = 0; i < data.size(); i++) {
            Book currentBook = data.get(i);
            if (currentBook.equals(deleteBook)) {
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
