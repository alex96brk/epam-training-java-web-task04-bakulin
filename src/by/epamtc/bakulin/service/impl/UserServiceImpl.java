package by.epamtc.bakulin.service.impl;

import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.dao.exception.DAOException;
import by.epamtc.bakulin.entity.User;
import by.epamtc.bakulin.service.UserService;
import by.epamtc.bakulin.service.exception.ServiceException;
import by.epamtc.bakulin.dao.exception.general.IncorrectStateException;
import by.epamtc.bakulin.service.exception.general.NotFoundException;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserServiceImpl() {
    }

    @Override
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void addUser(User user) throws ServiceException {
        try {
            int hash = user.hashCode();
            if (hash < 0) {
                hash = hash * (-1);
            }
            user.setUserId(user.hashCode());
            userDAO.add(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findUserById(Integer id) throws ServiceException {
        User user = null;
        try {
            user = userDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public User findUserByName(String userName) throws ServiceException {
        User user = null;
        try {
            user = userDAO.findByName(userName);
        } catch (DAOException e) {
            throw new NotFoundException(e);
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        return userDAO.findAll();
    }

    @Override
    public void updateUser(User user) throws ServiceException {
        try {
            userDAO.update(user);
        } catch (DAOException e) {
            throw new NotFoundException(e);
        }
    }

    @Override
    public void deleteUser(Integer id) throws ServiceException {
        try {
            userDAO.delete(id);
        } catch (DAOException e) {
            throw new NotFoundException(e);
        }
    }
}
