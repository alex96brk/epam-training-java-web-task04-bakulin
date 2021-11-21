package by.epamtc.bakulin.dao.impl;

import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.dao.exception.DAOException;
import by.epamtc.bakulin.dao.exception.general.FileAccessException;
import by.epamtc.bakulin.dao.exception.general.IncorrectStateException;
import by.epamtc.bakulin.dao.io.IOEntityBuilder;
import by.epamtc.bakulin.entity.Book;
import by.epamtc.bakulin.dao.io.txt.IOConnectorTXT;

import java.util.ArrayList;
import java.util.List;

public class TXTBookDAO extends IOConnectorTXT implements BookDAO, IOEntityBuilder<Book> {

    private static final String BOOKS_SOURCE_PATH = "books.txt.source.path";
    private static final String BOOKS_CACHE_PATH = "books.txt.source.cache.path";

    public TXTBookDAO() {
    }

    @Override
    public void add(Book entity) throws IncorrectStateException, FileAccessException {
        entityNullCheck(entity);
        writeDataLine(BOOKS_SOURCE_PATH, entity + "\n");
    }

    @Override
    public Book findById(Integer id) throws IncorrectStateException, FileAccessException {
        idNullCheck(id);
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
    public List<Book> findAll() throws FileAccessException {
        return parseFileData(readDocumentData(BOOKS_SOURCE_PATH));
    }

    @Override
    public void update(Book entity) throws FileAccessException, IncorrectStateException {
        entityNullCheck(entity);
        updateDataLine(BOOKS_SOURCE_PATH, BOOKS_CACHE_PATH, findById(entity.getBookId()).toString(), entity.toString());
    }

    @Override
    public void delete(Integer id) throws IncorrectStateException, FileAccessException {
        idNullCheck(id);
        deleteDataLine(BOOKS_SOURCE_PATH, BOOKS_CACHE_PATH, findById(id).toString());
    }

    @Override
    public Book findByAuthor(String author) throws DAOException {
        stringNullCheck(author);
        List<Book> books = findAll();
        Book result = null;
        for (Book book : books) {
            if (book.getBookAuthor().equals(author)) {
                result = book;
            }
        }
        return result;
    }

    public List<Book> parseFileData(List<String> fileData) {
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

    private void idNullCheck(Integer id) throws IncorrectStateException { //заменить на Object
        if (id == null) {
            throw new IncorrectStateException("Parameter - id, can not be null or less then 0; id = " + id);
        }
    }

    private void entityNullCheck(Book entity) throws IncorrectStateException {
        if (entity == null) {
            throw new IncorrectStateException("Parameter entity can not be null");
        }
    }

    private void stringNullCheck(String userName) throws IncorrectStateException {
        if (userName == null) {
            throw new IncorrectStateException("Method string parameter can not be null");
        }
    }

}
