package by.epamtc.bakulin.controller.command.impl.book;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.controller.command.impl.book.validator.BookValidator;
import by.epamtc.bakulin.entity.Book;
import by.epamtc.bakulin.service.BookService;
import by.epamtc.bakulin.service.exception.ServiceException;
import by.epamtc.bakulin.service.factory.TXTServiceFactory;

public class BookFindByIdCommand implements Command {

    private TXTServiceFactory txtServiceFactory = TXTServiceFactory.getInstance();
    private BookService bookService = txtServiceFactory.getBookService();
    private String[] requestParameters;

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
