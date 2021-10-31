package by.epamtc.bakulin.service;

import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.entity.Book;
import by.epamtc.bakulin.service.exception.ServiceException;

import java.util.List;

public interface BookService {

    void addBook(Book book) throws ServiceException;

    Book findBookById(Long id) throws ServiceException;

    Book findBookByAuthor(String bookAuthor) throws ServiceException;

    List<Book> findAllBooks() throws ServiceException;

    void updateBook(Book book) throws ServiceException;

    void deleteBook(Long id) throws ServiceException;

    void setBookDAO(BookDAO bookDAO);

}
