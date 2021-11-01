package by.epamtc.bakulin.dao.impl;

import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.io.IOEntityCollector;
import by.epamtc.bakulin.io.IOConnector;
import by.epamtc.bakulin.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class TXTBookDAO implements BookDAO, IOEntityCollector<Book> {

    private static final String BOOKS_SOURCE_PATH = "books.txt.source.path";
    private static final String BOOKS_CACHE_PATH = "books.txt.source.cache.path";

    private IOConnector ioConnector;

    public TXTBookDAO() {
    }

    public TXTBookDAO(IOConnector ioConnector) {
        this.ioConnector = ioConnector;
    }

    public void setIoConnector(IOConnector ioConnector) {
        this.ioConnector = ioConnector;
    }

    @Override
    public void add(Book entity) {
        ioConnector.writeDataLine(BOOKS_SOURCE_PATH, entity.toString() + "\n");
    }

    @Override
    public Book findById(Integer id) {
        List<Book> books = findAll();
        Book result = null;
        for (Book book : books) {
            if (book.getBookId().equals(id)) {
                result = book;
            }
        }
        return result;
    }

    @Override
    public List<Book> findAll() {
        return collectFileData(ioConnector.readDocumentData(BOOKS_SOURCE_PATH));
    }

    @Override
    public void update(Book entity) {
        ioConnector.updateDataLine(BOOKS_SOURCE_PATH, BOOKS_CACHE_PATH, findById(entity.getBookId()).toString(), entity.toString());
    }

    @Override
    public void delete(Integer id) {
        ioConnector.deleteDataLine(BOOKS_SOURCE_PATH, BOOKS_CACHE_PATH, findById(id).toString());
    }

    @Override
    public Book findByAuthor(String author) {
        List<Book> books = findAll();
        Book result = null;
        for (Book book : books) {
            if (book.getBookAuthor().equals(author)) {
                result = book;
            }
        }
        return result;
    }

    public List<Book> collectFileData(List<String> fileData) {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < fileData.size(); i++) {
            Book book = buildEntity(parseStringLine(fileData.get(i)));
            books.add(book);
        }
        return books;
    }

    public String[] parseStringLine(String line) {
        String[] strings = line.replace("Book{", "")
                .replaceAll("bookId=", "")
                .replaceAll("bookName='", "")
                .replaceAll("bookAuthor='", "")
                .replaceAll("bookGenre='", "")
                .replaceAll("'", "")
                .replaceAll("\\}", "")
                .split(", ");
        return strings;
    }

    public Book buildEntity(String[] entityProps) {
        Book book = new Book();
        book.setBookId(Integer.parseInt(entityProps[0]));
        book.setBookName(entityProps[1]);
        book.setBookAuthor(entityProps[2]);
        book.setBookGenre(entityProps[3]);
        return book;
    }
}
