package by.epamtc.bakulin.controller.command.impl.book;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.controller.command.impl.book.validator.BookValidator;
import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.entity.Book;
import by.epamtc.bakulin.service.BookService;
import by.epamtc.bakulin.service.exception.ServiceException;
import by.epamtc.bakulin.service.factory.ServiceFactory;

public class BookFindByIdCommand implements Command {

    private BookDAO bookDAO;

    private BookService bookService;

    private String[] requestParameters;

    public BookFindByIdCommand(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
        this.bookService = ServiceFactory.getInstance().getBookService(bookDAO);
    }

    @Override
    public String execute() {
        Integer id = Integer.parseInt(requestParameters[1]);
        String cmdResponse = null;
        try {
            BookValidator.validateId(id);
            Book book = bookService.findBookById(id);
            cmdResponse = book.toString();
        } catch (ServiceException e) {
            cmdResponse = e.getMessage();
        }
        return cmdResponse;
    }

    @Override
    public void setRequestParameters(String[] requestParameters) {
        this.requestParameters = requestParameters;
    }
}
