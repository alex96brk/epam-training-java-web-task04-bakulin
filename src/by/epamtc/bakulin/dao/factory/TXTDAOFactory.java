package by.epamtc.bakulin.dao.factory;

import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.dao.impl.TXTBookDAO;
import by.epamtc.bakulin.dao.impl.TXTUserDAO;
import by.epamtc.bakulin.io.IOConnector;
import by.epamtc.bakulin.io.impl.IOConnectorTXT;

public final class TXTDAOFactory {

    private static final TXTDAOFactory INSTANCE = new TXTDAOFactory();
    private static IOConnector IO_CONNECTOR = new IOConnectorTXT();

    private TXTDAOFactory() {}

    public static TXTDAOFactory getInstance() {
        return INSTANCE;
    }

    public UserDAO getUserDAO() {
        return new TXTUserDAO(IO_CONNECTOR);
    }

    public BookDAO getBookDAO() {
        return new TXTBookDAO(IO_CONNECTOR);
    }
}
