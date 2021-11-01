package by.epamtc.bakulin.controller.command.impl.book;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.service.BookService;
import by.epamtc.bakulin.service.factory.TXTServiceFactory;

public class BookDeleteCommand implements Command {

    private TXTServiceFactory txtServiceFactory = TXTServiceFactory.getInstance();
    private BookService bookService = txtServiceFactory.getBookService();
    private String[] requestParameters;

    @Override
    public String execute() {
        Integer id = Integer.parseInt(requestParameters[1]);
        String cmdResponse = null;
        try {
            bookService.deleteBook(id);
            cmdResponse = String.format("Deleted successfully; bookId = %d", id);
        } catch (Exception e) {
            e.printStackTrace();
            cmdResponse = "Bad request";
        }
        return cmdResponse;
    }

    @Override
    public void setRequestParameters(String[] requestParameters) {
        this.requestParameters = requestParameters;
    }
}
