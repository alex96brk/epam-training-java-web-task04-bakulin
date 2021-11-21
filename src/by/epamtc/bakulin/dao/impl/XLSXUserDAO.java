package by.epamtc.bakulin.dao.impl;

import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.dao.exception.DAOException;
import by.epamtc.bakulin.dao.exception.general.FileAccessException;
import by.epamtc.bakulin.dao.exception.general.IncorrectStateException;
import by.epamtc.bakulin.dao.io.xlsx.UserXLSXConnector;
import by.epamtc.bakulin.entity.User;

import java.util.List;

public class XLSXUserDAO extends UserXLSXConnector implements UserDAO {

    private static final String USERS_SOURCE_PATH = "users.xlsx.source.path";
    private static final String USERS_CACHE_PATH = "users.xlsx.source.cache.path";

    @Override
    public void add(User entity) throws DAOException {

    }

    @Override
    public User findById(Integer id) throws DAOException {
        return null;
    }

    @Override
    public List<User> findAll() throws DAOException {
        return readDocumentData(USERS_SOURCE_PATH);
    }

    @Override
    public void update(User entity) throws DAOException {

    }

    @Override
    public void delete(Integer id) throws DAOException {

    }

    @Override
    public User findByName(String userName) throws IncorrectStateException, FileAccessException {
        return null;
    }
}
