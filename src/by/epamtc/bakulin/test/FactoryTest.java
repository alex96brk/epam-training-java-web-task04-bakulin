package by.epamtc.bakulin.test;

import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.dao.exception.DAOException;
import by.epamtc.bakulin.dao.factory.LibraryDAOFactory;
import by.epamtc.bakulin.dao.factory.impl.TXTDAOFactory;
import by.epamtc.bakulin.entity.Book;
import by.epamtc.bakulin.entity.User;
import org.junit.Test;

import java.util.List;

public class FactoryTest {

    @Test
    public void test1() throws DAOException {
        LibraryDAOFactory libraryDAOFactory = TXTDAOFactory.getInstance();
        BookDAO bookDAO = libraryDAOFactory.getBookDAO();
        List<Book> books = bookDAO.findAll();
        for (Book book : books) {
            System.out.println(book);
        }
        UserDAO userDAO = libraryDAOFactory.getUserDAO();
        List<User> users = userDAO.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
