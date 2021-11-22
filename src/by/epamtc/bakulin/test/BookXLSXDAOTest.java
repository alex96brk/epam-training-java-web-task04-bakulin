package by.epamtc.bakulin.test;

import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.dao.exception.DAOException;
import by.epamtc.bakulin.dao.impl.XLSXBookDAO;
import by.epamtc.bakulin.entity.Book;
import org.junit.Test;

import java.util.List;

public class BookXLSXDAOTest {

    private BookDAO bookDAO = new XLSXBookDAO();

    @Test
    public void test1() throws DAOException {
        List<Book> books = bookDAO.findAll();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void test2() throws DAOException {
        Book book = bookDAO.findByAuthor("Frank Herbert");
        System.out.println(book);
        Book book1 = bookDAO.findById(book.getBookId());
        System.out.println(book1);
    }

    @Test
    public void test3() throws DAOException {
        Book book = new Book("The Hobbit, or There and Back Again", "John Tolkien", "Fantasy");
        book.setBookId(12345);
        bookDAO.add(book);
    }

    @Test
    public void test4() throws DAOException {
        Book updateBook = bookDAO.findById(12345);
        updateBook.setBookAuthor("John Ronald Reuel Tolkien");
        bookDAO.update(updateBook);
    }

    @Test
    public void test5() throws DAOException {
        bookDAO.delete(12345);
    }
}
