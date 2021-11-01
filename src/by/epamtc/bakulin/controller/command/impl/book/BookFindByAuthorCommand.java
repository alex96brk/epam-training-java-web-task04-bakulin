package by.epamtc.bakulin.controller.command.impl.book;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.entity.Book;
import by.epamtc.bakulin.service.BookService;
import by.epamtc.bakulin.service.factory.TXTServiceFactory;

public class BookFindByAuthorCommand implements Command {

    private TXTServiceFactory txtServiceFactory = TXTServiceFactory.getInstance();
    private BookService bookService = txtServiceFactory.getBookService();

    @Override
    public String execute(String cmdRequest) {
        String[] target = cmdRequest.split(" \\$");
        String bookAuthor = target[1];
        String cmdResponse = null;
        try {
            Book book = bookService.findBookByAuthor(bookAuthor);
            cmdResponse = book.toString();
        } catch (Exception e) {
            e.printStackTrace();
            cmdResponse = "Bad request";
        }
        return cmdResponse;
    }
}
