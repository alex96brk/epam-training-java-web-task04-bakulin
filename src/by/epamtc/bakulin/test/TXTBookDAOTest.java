package by.epamtc.bakulin.test;

import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.dao.impl.TXTBookDAO;
import by.epamtc.bakulin.io.impl.IOManagerTXT;
import by.epamtc.bakulin.model.Book;
import org.junit.Test;

import java.util.List;

public class TXTBookDAOTest {

    BookDAO bookDAO = new TXTBookDAO(new IOManagerTXT());

    @Test
    public void test1() {
        List<Book> books = bookDAO.findAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
