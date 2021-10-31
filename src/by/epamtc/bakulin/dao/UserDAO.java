package by.epamtc.bakulin.dao;

import by.epamtc.bakulin.dao.exception.DAOException;
import by.epamtc.bakulin.entity.User;

public interface UserDAO extends DAO<User> {

    User findByName(String userName) throws DAOException;
}
