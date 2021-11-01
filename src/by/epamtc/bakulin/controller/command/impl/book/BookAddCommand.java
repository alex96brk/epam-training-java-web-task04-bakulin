package by.epamtc.bakulin.controller.command.impl.book;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.entity.Book;
import by.epamtc.bakulin.service.BookService;
import by.epamtc.bakulin.service.factory.TXTServiceFactory;

public class BookAddCommand implements Command {

    private TXTServiceFactory txtServiceFactory = TXTServiceFactory.getInstance();
    private BookService bookService = txtServiceFactory.getBookService();

    @Override
    public String execute(String cmdRequest) {
        String[] target = cmdRequest.split(" \\$");
        String bookName = target[1];
        String bookAuthor = target[2];
        String bookGenre = target[3];
        String cmdResponse = null;
        try {
            Book book = new Book(bookName, bookAuthor, bookGenre);
            bookService.addBook(book);
            cmdResponse = book.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cmdResponse;
    }
}
