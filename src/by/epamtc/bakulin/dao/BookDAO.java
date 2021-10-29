package by.epamtc.bakulin.dao;

import by.epamtc.bakulin.model.Book;

import java.util.List;

public interface BookDAO {
    
    Book createBook(String bookName, String bookAuthor, String bookGenre);

    Book findBookByBookId(Long bookId);

    Book findBookByBookAuthor(String bookAuthor);

    Book findBookByBookName(String bookName);

    List<Book> findAllBooks();

    void updateBook(Long bookId, String newBookName, String newBookAuthor, String newBookGenre);

    void deleteBook(Long bookId);
}
