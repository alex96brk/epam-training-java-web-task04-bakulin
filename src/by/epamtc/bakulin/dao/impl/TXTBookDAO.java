package by.epamtc.bakulin.dao.impl;

import by.epamtc.bakulin.dao.BookDAO;
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
        return null;
    }

    @Override
    public Book findBookByBookAuthor(String bookAuthor) {
        return null;
    }

    @Override
    public Book findBookByBookName(String bookName) {
        return null;
    }

    @Override
    public List<Book> findAllBooks() {
        return convertBookDataToList(ioManager.readDocumentData(BOOKS_SOURCE_PATH));
    }

    @Override
    public void updateBook(String bookName, String newBookName, String newBookAuthor, String newBookGenre) {

    }

    @Override
    public void deleteBook(String bookName) {

    }

    private List<Book> convertBookDataToList(List<String> fileData) {
        List<Book> result = new ArrayList<>();
        for (int i = 0; i < fileData.size(); i++) {
            Book book = buildBook(parseStringBook(fileData.get(i)));
            result.add(book);
        }
        return result;
    }

    private String[] parseStringBook(String line) {
        String[] strings = line.replaceAll("Book", "")
                .replaceAll("=", "")
                .replaceAll("'", "")
                .replaceAll("\\{", "")
                .replaceAll(" ", "")
                .replaceAll("\\}", "")
                .replaceAll("bookId", "")
                .replaceAll("bookName", "")
                .replaceAll("bookAuthor", "")
                .replaceAll("bookGenre", "")
                .split(",");
        return strings;
    }

    private Book buildBook(String[] userProps) {
        Book book = new Book();
        book.setBookId(Long.parseLong(userProps[0]));
        book.setBookName(userProps[1]);
        book.setBookAuthor(userProps[2]);
        book.setBookGenre(userProps[3]);
        return book;
    }
}
