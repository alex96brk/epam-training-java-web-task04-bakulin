package by.epamtc.bakulin.dao.factory.impl;

import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.dao.factory.LibraryDAOFactory;
import by.epamtc.bakulin.dao.impl.TXTBookDAO;
import by.epamtc.bakulin.dao.impl.TXTUserDAO;

public final class TXTDAOFactory implements LibraryDAOFactory {

    private static final TXTDAOFactory INSTANCE = new TXTDAOFactory();

    private TXTDAOFactory() {}

    public static TXTDAOFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public UserDAO getUserDAO() {
        return new TXTUserDAO();
    }

    @Override
    public BookDAO getBookDAO() {
        return new TXTBookDAO();
    }
}
