package by.epamtc.bakulin.controller.command.impl.user.validator;

import by.epamtc.bakulin.entity.User;
import by.epamtc.bakulin.service.exception.ServiceException;
import by.epamtc.bakulin.service.exception.general.EntryAlreadyExistsException;

import java.util.List;

public class UserValidator {

    public static void validateUserProperties(Integer id, String userName, String firstName, String lastName, String password, String userRole) throws ServiceException {
        validateId(id);
        validateUserProperties(userName, firstName, lastName, password);
        validateUserRole(userRole);
    }

    public static void validateUserProperties(String userName, String firstName, String lastName, String password) throws ServiceException {
        validateUserName(userName);
        validateFirstName(firstName);
        validateLastName(lastName);
        validateUserPassword(password);
    }

    public static void validateId(Integer id) throws ServiceException {
        if (id == null || id < 0) {
            throw new ServiceException("Id can not be null or less then 0");
        }
    }

    public static void validateUserName(String userName) throws ServiceException {
        if (userName == null) {
            throw new ServiceException("User Name can not be null");
        }
    }

    public static void validateUniqueUserName(String userName, List<User> users) throws EntryAlreadyExistsException {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                throw new EntryAlreadyExistsException("UserName already exists. UserName: " + userName);
            }
        }
    }

    public static void validateFirstName(String firstName) throws ServiceException {
        if (firstName == null) {
            throw new ServiceException("First Name can not be null");
        }
    }

    public static void validateLastName(String lastName) throws ServiceException {
        if (lastName == null) {
            throw new ServiceException("Last Name can not be null");
        }
    }

    public static void validateUserRole(String userRole) throws ServiceException {
        if (userRole == null) {
            throw new ServiceException("User Role can not be null");
        }
    }

    public static void validateUserPassword(String password) throws ServiceException {
        if (password == null) {
            throw new ServiceException("Password can not be null");
        }
    }



}
