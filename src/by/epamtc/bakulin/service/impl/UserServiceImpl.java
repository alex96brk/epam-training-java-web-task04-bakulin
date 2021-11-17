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
            if (user == null) {
                throw new IncorrectStateException("User value can not be null.");
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
            if (id == null || id < 0) {
                throw new IncorrectStateException("Parameter - id, can not be null or less then 0; id = " + id);
            }
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
            if (user == null) {
                throw new NotFoundException("User Not found. userName = " + userName);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
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
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteUser(Integer id) throws ServiceException {
        try {
            userDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
