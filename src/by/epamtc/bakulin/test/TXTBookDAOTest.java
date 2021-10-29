package by.epamtc.bakulin.test;

import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.dao.impl.TXTBookDAO;
import by.epamtc.bakulin.io.impl.IOManagerTXT;
import by.epamtc.bakulin.model.Book;
import org.junit.Test;

import java.util.List;

public class TXTBookDAOTest {

    BookDAO dao = new TXTBookDAO(new IOManagerTXT());

    @Test
    public void test1() {
        List<Book> books = dao.findAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void test2() {
        Book book = dao.createBook("Mist", "Steven King", "Psychological Horror");
        System.out.println("New book: " + book);
    }

    @Test
    public void test21() {
        Long searchBookByBookId = 5281449453290762240L;
        Book book = dao.findBookByBookId(searchBookByBookId);
        System.out.println("Search result: " + book);
    }

    @Test
    public void test3() {
        String searchBookByBookName = "Altered Carbon";
        Book book = dao.findBookByBookName(searchBookByBookName);
        System.out.println("Search result: " + book);
    }

    @Test
    public void test4() {
        String searchBookByAuthor = "Frank Herbert";
        Book book = dao.findBookByBookName(searchBookByAuthor);
        System.out.println("Search result: " + book);
    }

    @Test
    public void test5() {
        dao.updateBook(8944158836706102272L, "Code Complete", "Steve McConnell", "Computer Science");
        test1();
    }

    @Test
    public void test6() {
        String id = "1783575304007059456";
        dao.deleteBook(Long.parseLong(id));
    }
}
