package by.epamtc.bakulin.test;

import by.epamtc.bakulin.entity.Book;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BookTest {

    @Test
    public void test1() {
        Book book1 = new Book("Count of Monte Cristo", "Alexandr Duma", "Historical Novel");
        Book book2 = new Book("Dune", "Frank Herbert", "Fantastic Novel");
        Book book8 = book1;

        Assert.assertTrue(book1.equals(book8));
        Assert.assertTrue(book8.equals(book1));
        Assert.assertFalse(book1.equals(book2));
        Assert.assertFalse(book2.equals(book1));
        Assert.assertTrue(book2.equals(book2));

    }

    @Test
    public void test2() {
        Book book1 = new Book("Count of Monte Cristo", "Alexandr Duma", "Historical Novel");
        Book book2 = new Book("Dune", "Frank Herbert", "Fantastic Novel");
        Book book3 = new Book("Altered Carbon", "Richard Morgan", "Fantastic Novel");
        Book book4 = new Book("Quiet Done", "Michael Sholokhov", "Historical Novel");
        Book book5 = new Book("Thinking in Java", "Bruce Eckel", "Computer Science");
        Book book6 = new Book("Code Complete", "Steve McConnell", "Computer Science");
        Book book7 = new Book("Code Complete", "Steve McConnell", "Computer Science");

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);
        books.add(book7);
        System.out.println("Books List: ");
        for (Book book : books) {
            System.out.println(book);
        }
        Assert.assertFalse(books.isEmpty());

    }
}
