package by.epamtc.bakulin.controller.command.impl.book;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.controller.command.impl.book.validator.BookValidator;
import by.epamtc.bakulin.entity.Book;
import by.epamtc.bakulin.service.BookService;
import by.epamtc.bakulin.service.exception.ServiceException;
import by.epamtc.bakulin.service.factory.TXTServiceFactory;

public class BookUpdateCommand implements Command {

    private TXTServiceFactory txtServiceFactory = TXTServiceFactory.getInstance();
    private BookService bookService = txtServiceFactory.getBookService();
    private String[] requestParameters;

    @Override
    public String execute() {
        Integer bookId = Integer.parseInt(requestParameters[1]);
        String bookName = requestParameters[2];
        String bookAuthor = requestParameters[3];
        String bookGenre = requestParameters[4];
        String cmdResponse = null;
        try {
            BookValidator.validateBookProperties(bookId, bookName, bookAuthor, bookGenre);
            Book book = bookService.findBookById(bookId);
            book.setBookName(bookName);
            book.setBookAuthor(bookAuthor);
            book.setBookGenre(bookGenre);
            bookService.updateBook(book);
            cmdResponse = book.toString();
        } catch (ServiceException e) {
            cmdResponse = "Bad request";
        }
        return cmdResponse;
    }

    @Override
    public void setRequestParameters(String[] requestParameters) {
        this.requestParameters = requestParameters;
    }
}
