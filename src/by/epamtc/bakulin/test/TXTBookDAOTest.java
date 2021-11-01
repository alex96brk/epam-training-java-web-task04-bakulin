package by.epamtc.bakulin.test;

import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.dao.exception.DAOException;
import by.epamtc.bakulin.dao.impl.TXTBookDAO;
import by.epamtc.bakulin.io.impl.IOConnectorTXT;
import by.epamtc.bakulin.entity.Book;
import org.junit.Test;

import java.util.List;

public class TXTBookDAOTest {

    BookDAO dao = new TXTBookDAO(new IOConnectorTXT());

    @Test
    public void test1() throws DAOException {
        List<Book> books = dao.findAll();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void test2() throws DAOException {
        Book book = new Book("Mist", "Steven King", "Horror Story");
        dao.add(book);
    }

    @Test
    public void test3() throws DAOException {
        Book book = dao.findById(76343991);
        book.setBookName("Quiet Don");
        dao.update(book);
    }

    @Test
    public void test4() throws DAOException {
        dao.delete(326965430);
    }

}
