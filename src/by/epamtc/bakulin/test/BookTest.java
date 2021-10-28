package by.epamtc.bakulin.test;

import by.epamtc.bakulin.model.Book;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BookTest {

    @Test
    public void test1() {
        Book book1 = new Book("Count of Monte Cristo", "Alexandr Duma", "Historical Novel");
        Book book2 = new Book("Dune", "Frank Herbert", "Fantastic Novel");
        Book book3 = new Book("Altered Carbon", "Richard Morgan", "Fantastic Novel");
        Book book4 = new Book("Quiet Done", "Michael Sholokhov", "Historical Novel");
        Book book5 = new Book("Thinking in Java", "Bruce Eckel", "Computer Science");
        Book book6 = new Book("Code Complete", "Steve McConnell", "Computer Science");
        Book book7 = new Book("Code Complete", "Steve McConnell", "Computer Science");
        Book book8 = book1;
        Book book9 = new Book("Count of Monte Cristo", "Alexandr Duma", "Historical Novel");

        boolean equals1 = book1.equals(book8);
        boolean equals2 = book8.equals(book1);
        boolean equals3 = book1.equals(book2);
        boolean equals4 = book2.equals(book1);
        boolean equals5 = book2.equals(book2);

        System.out.println("book1: " + book1);
        System.out.println("book2: " + book2);
        System.out.println("book8: " + book8);

        System.out.println("book1 equals book8? : " + equals1);
        System.out.println("book8 equals book1? : " + equals2);
        System.out.println("book1 equals book2? : " + equals3);
        System.out.println("book2 equals book1? : " + equals4);
        System.out.println("book2 equals book2? : " + equals5);

        System.out.println("All books:");
        System.out.println("book1: " + book1);
        System.out.println("book2: " + book2);
        System.out.println("book3: " + book3);
        System.out.println("book4: " + book4);
        System.out.println("book5: " + book5);
        System.out.println("book6: " + book6);
        System.out.println("book6: " + book7);
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

    }
}
