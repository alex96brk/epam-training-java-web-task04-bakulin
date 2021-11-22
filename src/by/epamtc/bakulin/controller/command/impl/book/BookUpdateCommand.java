package by.epamtc.bakulin.controller.command.impl.book;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.controller.command.impl.book.validator.BookValidator;
import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.entity.Book;
import by.epamtc.bakulin.service.BookService;
import by.epamtc.bakulin.service.exception.ServiceException;
import by.epamtc.bakulin.service.exception.general.EntryAlreadyExistsException;
import by.epamtc.bakulin.service.factory.ServiceFactory;

public class BookUpdateCommand implements Command {

    private BookDAO bookDAO;

    private BookService bookService;

    private String[] requestParameters;

    public BookUpdateCommand(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
        this.bookService = ServiceFactory.getInstance().getBookService(bookDAO);
    }

    @Override
    public String execute() {
        Integer bookId = Integer.parseInt(requestParameters[1]);
        String currentBookName = requestParameters[2];
        String newBookName = requestParameters[3];
        String bookAuthor = requestParameters[4];
        String bookGenre = requestParameters[5];
        String cmdResponse = null;
        try {
            BookValidator.validateBookProperties(bookId, currentBookName, bookAuthor, bookGenre);
            Book book = bookService.findBookById(bookId);
            if (!newBookName.equals(currentBookName)) {
                BookValidator.validateUniqueBookName(newBookName, bookService.findAllBooks());
                book.setBookName(newBookName);
            }
            book.setBookAuthor(bookAuthor);
            book.setBookGenre(bookGenre);
            bookService.updateBook(book);
            cmdResponse = book.toString();
        }  catch (EntryAlreadyExistsException e) {
            cmdResponse = e.getMessage();
        }
        catch (ServiceException e) {
            cmdResponse = e.getMessage();
        }
        return cmdResponse;
    }

    @Override
    public void setRequestParameters(String[] requestParameters) {
        this.requestParameters = requestParameters;
    }
}
