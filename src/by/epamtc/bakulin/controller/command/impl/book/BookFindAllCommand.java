package by.epamtc.bakulin.controller.command.impl.book;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.entity.Book;
import by.epamtc.bakulin.service.BookService;
import by.epamtc.bakulin.service.exception.ServiceException;
import by.epamtc.bakulin.service.factory.TXTServiceFactory;

import java.util.List;

public class BookFindAllCommand implements Command {

    private TXTServiceFactory txtServiceFactory = TXTServiceFactory.getInstance();
    private BookService bookService = txtServiceFactory.getBookService();
    private String[] requestParameters;

    @Override
    public String execute() {
        String cmdResponse = null;
        try {
            List<Book> books =  bookService.findAllBooks();
            cmdResponse = books.toString();
        } catch (ServiceException e) {
            e.printStackTrace();
            cmdResponse = "Bad request";
        }
        return cmdResponse;
    }

    @Override
    public void setRequestParameters(String[] requestParameters) {
        this.requestParameters = this.requestParameters;
    }
}
