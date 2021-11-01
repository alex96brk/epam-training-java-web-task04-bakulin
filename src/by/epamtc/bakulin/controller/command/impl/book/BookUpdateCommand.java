package by.epamtc.bakulin.controller.command.impl.book;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.entity.Book;
import by.epamtc.bakulin.service.BookService;
import by.epamtc.bakulin.service.factory.TXTServiceFactory;

import java.util.logging.Logger;

public class BookUpdateCommand implements Command {

    private TXTServiceFactory txtServiceFactory = TXTServiceFactory.getInstance();
    private BookService bookService = txtServiceFactory.getBookService();

    @Override
    public String execute(String cmdRequest) {
        String[] target = cmdRequest.split(" \\$");
        Long bookId = Long.parseLong(target[1]);
        String bookName = target[2];
        String bookAuthor = target[3];
        String bookGenre = target[4];
        String cmdResponse = null;
        try {
            Book book = bookService.findBookById(bookId);
            book.setBookName(bookName);
            book.setBookAuthor(bookAuthor);
            book.setBookGenre(bookGenre);
            bookService.updateBook(book);
            cmdResponse = book.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cmdResponse;
    }
}
