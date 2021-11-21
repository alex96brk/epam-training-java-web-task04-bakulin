package by.epamtc.bakulin.dao.impl;

import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.dao.exception.DAOException;
import by.epamtc.bakulin.dao.io.xlsx.BookXLSXConnector;
import by.epamtc.bakulin.entity.Book;

import java.util.List;

public class XLSXBookDAO extends BookXLSXConnector implements BookDAO{

    private static final String BOOKS_SOURCE_PATH = "books.xlsx.source.path";
    private static final String BOOKS_CACHE_PATH = "books.xlsx.source.cache.path";

    @Override
    public Book findByAuthor(String author) throws DAOException {
        return null;
    }

    @Override
    public void add(Book entity) throws DAOException {

    }

    @Override
    public Book findById(Integer id) throws DAOException {
        return null;
    }

    @Override
    public List<Book> findAll() throws DAOException {
        return null;
    }

    @Override
    public void update(Book entity) throws DAOException {

    }

    @Override
    public void delete(Integer id) throws DAOException {

    }

}
