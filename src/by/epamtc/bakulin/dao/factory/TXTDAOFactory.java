package by.epamtc.bakulin.dao.factory;

import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.dao.impl.TXTBookDAO;
import by.epamtc.bakulin.dao.impl.TXTUserDAO;

public final class TXTDAOFactory {

    private static final TXTDAOFactory INSTANCE = new TXTDAOFactory();

    private TXTDAOFactory() {}

    public static TXTDAOFactory getInstance() {
        return INSTANCE;
    }

    public UserDAO getUserDAO() {
        return new TXTUserDAO();
    }

    public BookDAO getBookDAO() {
        return new TXTBookDAO();
    }
}
