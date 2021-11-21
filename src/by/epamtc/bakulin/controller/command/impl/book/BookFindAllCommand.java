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

    private BookDAO bookDAO = TXTDAOFactory.getInstance().getBookDAO();

    private BookService bookService = ServiceFactory.getInstance().getBookService(bookDAO);

    private String[] requestParameters;

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
