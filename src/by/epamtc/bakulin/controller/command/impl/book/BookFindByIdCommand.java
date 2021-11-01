package by.epamtc.bakulin.controller.command.impl.book;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.entity.Book;
import by.epamtc.bakulin.service.BookService;
import by.epamtc.bakulin.service.exception.ServiceException;
import by.epamtc.bakulin.service.factory.TXTServiceFactory;

public class BookFindByIdCommand implements Command {

    private TXTServiceFactory txtServiceFactory = TXTServiceFactory.getInstance();
    private BookService bookService = txtServiceFactory.getBookService();


    @Override
    public String execute(String cmdRequest) {
        String[] target = cmdRequest.split(" \\$");
        Long id = Long.parseLong(target[1]);
        String cmdResponse = null;
        try {
            Book book = bookService.findBookById(id);
            cmdResponse = book.toString();
        } catch (ServiceException e) {
            e.printStackTrace();
            cmdResponse = "Bad request";
        }
        return cmdResponse;
    }

}
