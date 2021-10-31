package by.epamtc.bakulin.service.factory;

import by.epamtc.bakulin.dao.factory.TXTDAOFactory;
import by.epamtc.bakulin.service.BookService;
import by.epamtc.bakulin.service.UserService;
import by.epamtc.bakulin.service.impl.BookServiceImpl;
import by.epamtc.bakulin.service.impl.UserServiceImpl;

public class TXTServiceFactory {

    private static final TXTServiceFactory INSTANCE = new TXTServiceFactory();
    private UserService userService;
    private BookService bookService;

    private TXTServiceFactory() {}

    public static TXTServiceFactory getInstance() {
        return INSTANCE;
    }

    public UserService getUserService() {
        return new UserServiceImpl(TXTDAOFactory.getInstance().getUserDAO());
    }

    public BookService getBookService() {
        return new BookServiceImpl(TXTDAOFactory.getInstance().getBookDAO());
    }
}
