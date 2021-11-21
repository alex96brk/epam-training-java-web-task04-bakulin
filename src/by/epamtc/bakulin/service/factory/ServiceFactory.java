package by.epamtc.bakulin.service.factory;

import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.service.BookService;
import by.epamtc.bakulin.service.UserService;
import by.epamtc.bakulin.service.impl.BookServiceImpl;
import by.epamtc.bakulin.service.impl.UserServiceImpl;

public final class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public UserService getUserService(UserDAO userDAO) {
        return new UserServiceImpl(userDAO);
    }

    public BookService getBookService(BookDAO bookDAO) {
        return new BookServiceImpl(bookDAO);
    }
}
