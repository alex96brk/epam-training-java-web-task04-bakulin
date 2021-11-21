package by.epamtc.bakulin.controller.command.impl.book;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.controller.command.impl.book.validator.BookValidator;
import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.dao.factory.TXTDAOFactory;
import by.epamtc.bakulin.entity.Book;
import by.epamtc.bakulin.service.BookService;
import by.epamtc.bakulin.service.exception.ServiceException;
import by.epamtc.bakulin.service.exception.general.EntryAlreadyExistsException;
import by.epamtc.bakulin.service.factory.ServiceFactory;

public class BookAddCommand implements Command {

    private BookDAO bookDAO = TXTDAOFactory.getInstance().getBookDAO();

    private BookService bookService = ServiceFactory.getInstance().getBookService(bookDAO);

    private String[] requestParameters;

    @Override
    public String execute() {
        String bookName = requestParameters[1];
        String bookAuthor = requestParameters[2];
        String bookGenre = requestParameters[3];
        String cmdResponse = null;
        try {
            BookValidator.validateBookProperties(bookName, bookAuthor, bookGenre);
            BookValidator.validateUniqueBookName(bookName, bookService.findAllBooks());
            Book book = new Book(bookName, bookAuthor, bookGenre);
            bookService.addBook(book);
            cmdResponse = book.toString();
        } catch (EntryAlreadyExistsException e) {
            cmdResponse = e.getMessage();
        }
        catch (ServiceException e) {
            cmdResponse = "Bad request";
        }

        return cmdResponse;
    }

    @Override
    public void setRequestParameters(String[] requestParameters) {
        this.requestParameters = requestParameters;
    }
}
