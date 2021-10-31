package by.epamtc.bakulin.service;

import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.dao.exception.DAOException;
import by.epamtc.bakulin.entity.User;
import by.epamtc.bakulin.service.exception.ServiceException;

import java.util.List;

public interface UserService {

    void addUser(User user) throws ServiceException;

    User findUserById(Long id) throws ServiceException;

    User findUserByName(String userName) throws ServiceException;

    List<User> findAllUsers() throws ServiceException;

    void updateUser(User user) throws ServiceException;

    void deleteUser(Long id) throws ServiceException;

    void setUserDAO(UserDAO userDAO);


}
