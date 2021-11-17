package by.epamtc.bakulin.controller;

import by.epamtc.bakulin.entity.User;
import by.epamtc.bakulin.service.UserService;
import by.epamtc.bakulin.service.exception.ServiceException;
import by.epamtc.bakulin.service.factory.TXTServiceFactory;

public class UserSession {

    private boolean isSessionActive;

    private String authority;

    private TXTServiceFactory txtServiceFactory = TXTServiceFactory.getInstance();

    private UserService userService;

    public UserSession() {
        this.userService = txtServiceFactory.getUserService();
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
