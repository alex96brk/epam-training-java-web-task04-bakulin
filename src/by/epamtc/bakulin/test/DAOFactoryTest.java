package by.epamtc.bakulin.test;

import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.dao.exception.DAOException;
import by.epamtc.bakulin.dao.factory.TXTDAOFactory;
import by.epamtc.bakulin.entity.Book;
import by.epamtc.bakulin.entity.User;
import org.junit.Test;

import java.util.List;

public class DAOFactoryTest {
    static TXTDAOFactory TXT_DAO_FACTORY = TXTDAOFactory.getInstance();

    @Test
    public void test1() throws DAOException {

        UserDAO userDAO = TXT_DAO_FACTORY.getUserDAO();
        BookDAO bookDAO = TXT_DAO_FACTORY.getBookDAO();

        List<User> users = userDAO.findAll();
        System.out.println("Users:");
        for (User user : users) {
            System.out.println(user);
        }

        List<Book> books = bookDAO.findAll();
        System.out.println("Books:");
        for (Book book : books) {
            System.out.println(book);
        }

    }
}
