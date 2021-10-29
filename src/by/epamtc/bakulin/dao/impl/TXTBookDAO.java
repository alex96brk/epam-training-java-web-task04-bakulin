package by.epamtc.bakulin.dao.impl;

import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.exception.BookNotFoundException;
import by.epamtc.bakulin.io.IOManager;
import by.epamtc.bakulin.model.Book;

import java.util.ArrayList;
import java.util.List;

public class TXTBookDAO implements BookDAO {
    private static final String BOOKS_SOURCE_PATH = "books.source.path";
    private static final String BOOKS_CACHE_PATH = "books.source.cache.path";
    private IOManager ioManager;

    public TXTBookDAO(IOManager ioManager) {
        this.ioManager = ioManager;
    }

    public TXTBookDAO() {
    }

    public void setIoManager(IOManager ioManager) {
        this.ioManager = ioManager;
    }

    @Override
    public Book createBook(String bookName, String bookAuthor, String bookGenre) {
        Book book = new Book(bookName, bookAuthor, bookGenre);
        ioManager.appendDataLine(BOOKS_SOURCE_PATH, book + "\n", true);
        return book;
    }

    @Override
    public Book findBookByBookId(Long bookId) {
        List<Book> books = findAllBooks();
        Book searchBook = null;
        try {
            for (Book book : books) {
                if (book.getBookId().equals(bookId)) {
                    searchBook = book;
                }
            }
            if (searchBook == null) {
                throw new BookNotFoundException("Book not found: " + bookId);
            }
        } catch (BookNotFoundException exception) {
            exception.printStackTrace();
        }
        return searchBook;
    }

    @Override
    public Book findBookByBookAuthor(String bookAuthor) {
        List<Book> books = findAllBooks();
        Book searchBook = null;
        try {
            for (Book book : books) {
                if (book.getBookAuthor().equalsIgnoreCase(bookAuthor)) {
                    searchBook = book;
                }
            }
            if (searchBook == null) {
                throw new BookNotFoundException("Book not found: " + bookAuthor);
            }
        } catch (BookNotFoundException exception) {
            exception.printStackTrace();
        }
        return searchBook;
    }

    @Override
    public Book findBookByBookName(String bookName) {
        List<Book> books = findAllBooks();
        Book searchBook = null;
        try {
            for (Book book : books) {
                if (book.getBookName().equalsIgnoreCase(bookName)) {
                    searchBook = book;
                }
            }
            if (searchBook == null) {
                throw new BookNotFoundException("Book not found: " + bookName);
            }
        } catch (BookNotFoundException exception) {
            exception.printStackTrace();
        }
        return searchBook;
    }

    @Override
    public List<Book> findAllBooks() {
        return convertBookDataToList(ioManager.readDocumentData(BOOKS_SOURCE_PATH));
    }

    @Override
    public void updateBook(Long bookId, String newBookName, String newBookAuthor, String newBookGenre) {
        Book book = findBookByBookId(bookId);
        String oldBookStr = book.toString();
        populateBookData(book, newBookName, newBookAuthor, newBookGenre);
        String updatedBook = book.toString();
        ioManager.replaceDataLine(BOOKS_SOURCE_PATH, BOOKS_CACHE_PATH, oldBookStr, updatedBook);
    }

    @Override
    public void deleteBook(Long bookId) {
        Book book = findBookByBookId(bookId);
        ioManager.replaceDataLine(BOOKS_SOURCE_PATH, BOOKS_CACHE_PATH, book.toString(), "");
    }

    private List<Book> convertBookDataToList(List<String> fileData) {
        List<Book> result = new ArrayList<>();
        for (int i = 0; i < fileData.size(); i++) {
            Book book = buildBook(parseStringBook(fileData.get(i)));
            result.add(book);
        }
        return result;
    }

    private void populateBookData(Book targetBook, String newBookName, String newBookAuthor, String newBookGenre) {
        if (newBookName != null) {
            targetBook.setBookName(newBookName);
        }
        if (newBookAuthor != null) {
            targetBook.setBookAuthor(newBookAuthor);
        }
        if (newBookGenre != null) {
            targetBook.setBookGenre(newBookGenre);
        }
    }

    private String[] parseStringBook(String line) {
        String[] strings = line.replace("Book{", "")
                .replaceAll("bookId=", "")
                .replaceAll("bookName='", "")
                .replaceAll("bookAuthor='", "")
                .replaceAll("bookGenre='", "")
                .replaceAll("'", "")
                .replaceAll("\\}", "")
                .split(", ");
        return strings;
    }

    private Book buildBook(String[] bookProps) {
        Book book = new Book();
        book.setBookId(Long.parseLong(bookProps[0]));
        book.setBookName(bookProps[1]);
        book.setBookAuthor(bookProps[2]);
        book.setBookGenre(bookProps[3]);
        return book;
    }
}
