package by.epamtc.bakulin.service;

import by.epamtc.bakulin.entity.Book;

import java.util.List;

public interface BookService {

    void addBook(Book book);

    Book findBookById(Long id);

    Book findBookByName(String bookName);

    List<Book> findAllBooks();

    void updateBook(Book book);

    void deleteBook(Book book);


}
