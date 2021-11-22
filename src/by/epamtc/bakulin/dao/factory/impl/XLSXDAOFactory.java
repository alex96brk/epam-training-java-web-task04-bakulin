package by.epamtc.bakulin.dao.factory.impl;

import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.dao.factory.LibraryDAOFactory;
import by.epamtc.bakulin.dao.impl.XLSXBookDAO;
import by.epamtc.bakulin.dao.impl.XLSXUserDAO;

public class XLSXDAOFactory implements LibraryDAOFactory {

    private static XLSXDAOFactory INSTANCE = new XLSXDAOFactory();

    public static XLSXDAOFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public UserDAO getUserDAO() {
        return new XLSXUserDAO();
    }

    @Override
    public BookDAO getBookDAO() {
        return new XLSXBookDAO();
    }
}
