package by.epamtc.bakulin.dao.impl;

import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.dao.exception.DAOException;
import by.epamtc.bakulin.dao.exception.general.IncorrectStateException;
import by.epamtc.bakulin.dao.io.xlsx.BookXLSXConnector;
import by.epamtc.bakulin.entity.Book;

import java.util.List;

public class XLSXBookDAO extends BookXLSXConnector implements BookDAO{

    private static final String BOOKS_SOURCE_PATH = "books.xlsx.source.path";
    private static final String BOOKS_CACHE_PATH = "books.xlsx.source.cache.path";

    @Override
    public void add(Book entity) throws DAOException {
        parameterNullCheck(entity);
        List<Book> books = findAll();
        books.add(entity);
        writeDataLine(BOOKS_SOURCE_PATH, BOOKS_CACHE_PATH, books);
    }

    @Override
    public Book findById(Integer id) throws DAOException {
        parameterNullCheck(id);
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
    public List<Book> findAll() throws DAOException {
        return readDocumentData(BOOKS_SOURCE_PATH);
    }

    @Override
    public void update(Book entity) throws DAOException {
        parameterNullCheck(entity);
        List<Book> books = findAll();
        updateDataLine(BOOKS_SOURCE_PATH, BOOKS_CACHE_PATH, books, entity);
    }

    @Override
    public void delete(Integer id) throws DAOException {
        parameterNullCheck(id);
        List<Book> books = findAll();
        deleteDataLine(BOOKS_SOURCE_PATH, BOOKS_CACHE_PATH, books, findById(id));
    }

    @Override
    public Book findByAuthor(String author) throws DAOException {
        parameterNullCheck(author);
        List<Book> books = findAll();
        Book result = null;
        for (Book book : books) {
            if (book.getBookAuthor().equals(author)) {
                result = book;
            }
        }
        return result;
    }

    private void parameterNullCheck(Object parameter) throws IncorrectStateException {
        if (parameter == null) {
            throw new IncorrectStateException("Parameter - can not be null");
        }
    }
}
