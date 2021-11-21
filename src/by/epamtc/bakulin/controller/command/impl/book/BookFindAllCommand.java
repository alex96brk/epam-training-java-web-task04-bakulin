package by.epamtc.bakulin.controller.command.impl.book;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.dao.factory.TXTDAOFactory;
import by.epamtc.bakulin.entity.Book;
import by.epamtc.bakulin.service.BookService;
import by.epamtc.bakulin.service.exception.ServiceException;
import by.epamtc.bakulin.service.factory.ServiceFactory;

import java.util.List;

public class BookFindAllCommand implements Command {

    private BookDAO bookDAO;

    private BookService bookService;

    private String[] requestParameters;

    public BookFindAllCommand(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
        this.bookService = ServiceFactory.getInstance().getBookService(bookDAO);
    }

    @Override
    public String execute() {
        String cmdResponse = null;
        try {
            List<Book> books =  bookService.findAllBooks();
            cmdResponse = books.toString();
        } catch (ServiceException e) {
            cmdResponse = e.getMessage();
        }
        return cmdResponse;
    }

    @Override
    public void setRequestParameters(String[] requestParameters) {
        this.requestParameters = this.requestParameters;
    }
}
