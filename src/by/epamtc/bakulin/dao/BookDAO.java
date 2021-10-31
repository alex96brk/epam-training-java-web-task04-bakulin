package by.epamtc.bakulin.dao;

import by.epamtc.bakulin.dao.exception.DAOException;
import by.epamtc.bakulin.entity.Book;

public interface BookDAO extends DAO<Book> {

    Book findByAuthor(String author) throws DAOException;
}
