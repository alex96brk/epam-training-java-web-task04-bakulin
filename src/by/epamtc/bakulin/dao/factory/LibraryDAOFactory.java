package by.epamtc.bakulin.dao.factory;

import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.dao.UserDAO;

public interface LibraryDAOFactory {

    static final LibraryDAOFactory INSTANCE = null;

    static LibraryDAOFactory getInstance() {
        return INSTANCE;
    }

    UserDAO getUserDAO();

    BookDAO getBookDAO();
}
