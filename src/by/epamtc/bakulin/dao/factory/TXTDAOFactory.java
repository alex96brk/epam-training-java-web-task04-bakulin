package by.epamtc.bakulin.dao.factory;

import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.dao.impl.TXTUserDAO;

public class UserDAOFactory implements DAOFactory<UserDAO> {
    @Override
    public UserDAO getDAO() {
        return new TXTUserDAO();
    }
}
