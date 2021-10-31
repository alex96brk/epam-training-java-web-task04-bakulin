package by.epamtc.bakulin.test;

import by.epamtc.bakulin.dao.factory.TXTDAOFactory;
import by.epamtc.bakulin.entity.Book;
import by.epamtc.bakulin.service.BookService;
import by.epamtc.bakulin.service.exception.ServiceException;
import by.epamtc.bakulin.service.impl.BookServiceImpl;
import org.junit.Test;

import java.util.List;

public class BookServiceTest {
    BookService bookService = new BookServiceImpl(TXTDAOFactory.getInstance().getBookDAO());

    @Test
    public void test1() throws ServiceException {
        List<Book> books = bookService.findAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }

    }
}
