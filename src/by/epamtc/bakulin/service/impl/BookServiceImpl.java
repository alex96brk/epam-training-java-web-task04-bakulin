package by.epamtc.bakulin.service.impl;

import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.dao.exception.DAOException;
import by.epamtc.bakulin.entity.Book;
import by.epamtc.bakulin.service.BookService;
import by.epamtc.bakulin.service.exception.ServiceException;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;

    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public BookServiceImpl() {}

    @Override
    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public void addBook(Book book) throws ServiceException {
        try {
            book.setBookId(book.hashCode());
            bookDAO.add(book);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Book findBookById(Integer id) throws ServiceException {
        Book book = null;
        try {
            book = bookDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return book;
    }

    @Override
    public Book findBookByAuthor(String bookAuthor) throws ServiceException {
        Book book = null;
        try {
            book = bookDAO.findByAuthor(bookAuthor);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return book;
    }

    @Override
    public List<Book> findAllBooks() throws ServiceException {
        List<Book> books = null;
        try {
            books = bookDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return books;
    }

    @Override
    public void updateBook(Book book) throws ServiceException {
        try {
            bookDAO.update(book);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteBook(Integer id) throws ServiceException {
        try {
            bookDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
