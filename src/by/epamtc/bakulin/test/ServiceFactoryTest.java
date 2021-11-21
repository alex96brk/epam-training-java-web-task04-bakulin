package by.epamtc.bakulin.test;

import by.epamtc.bakulin.dao.factory.TXTDAOFactory;
import by.epamtc.bakulin.entity.Book;
import by.epamtc.bakulin.entity.User;
import by.epamtc.bakulin.service.BookService;
import by.epamtc.bakulin.service.UserService;
import by.epamtc.bakulin.service.exception.ServiceException;
import by.epamtc.bakulin.service.factory.ServiceFactory;
import org.junit.Test;

import java.util.List;

public class ServiceFactoryTest {
    UserService userService = ServiceFactory.getInstance().getUserService(TXTDAOFactory.getInstance().getUserDAO());
    BookService bookService = ServiceFactory.getInstance().getBookService(TXTDAOFactory.getInstance().getBookDAO());

    @Test
    public void test1() throws ServiceException {
       List<User> users = userService.findAllUsers();
       for (User user : users) {
           System.out.println(user);
       }
    }

    @Test
    public void test2() throws ServiceException {
        List<Book> books = bookService.findAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void test3() throws ServiceException {
        Book book = bookService.findBookById(-1);
        System.out.println(book);
    }
}
