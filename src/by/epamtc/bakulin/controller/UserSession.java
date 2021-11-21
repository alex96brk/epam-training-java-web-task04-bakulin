package by.epamtc.bakulin.controller;

import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.entity.User;
import by.epamtc.bakulin.service.UserService;
import by.epamtc.bakulin.service.exception.ServiceException;
import by.epamtc.bakulin.service.factory.ServiceFactory;

public class UserSession {

    private boolean isSessionActive;

    private String authority;

    private UserService userService;

    public UserSession(UserService userService) {
        this.userService = userService;
    }

    public boolean isSessionActive() {
        return isSessionActive;
    }

    public String getAuthority() {
        return this.authority;
    }

    public User authenticate(String userName, String password) throws ServiceException {
        User authUser = null;
        try {
            authUser = userService.findUserByName(userName);

            if (authUser != null) {
                boolean isValidPassword = password.equals(authUser.getPassword());

                if (isValidPassword) {
                    this.isSessionActive = true;
                    this.authority = authUser.getUserRole().name();
                }
            }
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
        return authUser;
    }
}
