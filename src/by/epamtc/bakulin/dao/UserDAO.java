package by.epamtc.bakulin.dao;

import by.epamtc.bakulin.dao.exception.general.IncorrectStateException;
import by.epamtc.bakulin.entity.User;

public interface UserDAO extends DAO<User> {

    User findByName(String userName) throws IncorrectStateException;
}
