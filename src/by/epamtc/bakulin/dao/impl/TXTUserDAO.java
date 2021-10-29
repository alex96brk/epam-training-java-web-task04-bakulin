package by.epamtc.bakulin.dao.impl;

import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.exception.UserNotFoundException;
import by.epamtc.bakulin.io.IOManager;
import by.epamtc.bakulin.model.Role;
import by.epamtc.bakulin.model.User;

import java.util.ArrayList;
import java.util.List;

public class TXTUserDAO implements UserDAO {

    private static final String USERS_SOURCE_PATH = "users.source.path";
    private static final String USERS_CACHE_PATH = "users.source.cache.path";
    private IOManager ioManager;

    public TXTUserDAO(IOManager ioManager) {
        this.ioManager = ioManager;
    }

    public TXTUserDAO() {
    }

    public void setIoManager(IOManager ioManager) {
        this.ioManager = ioManager;
    }

    @Override
    public User createUser(String userName, String firstName, String lastName, String password) {
        User user = new User(userName, firstName, lastName, password);
        ioManager.appendDataLine(USERS_SOURCE_PATH, user + "\n", true);
        return user;
    }

    @Override
    public User findUserByUserId(Long userId) {
        List<User> users = findAllUsers();
        User searchUser = null;
        try {
            for (User user : users) {
                if (user.getUserId().equals(userId)) {
                    searchUser = user;
                }
            }
            if (searchUser == null) {
                throw new UserNotFoundException(String.format("User not found: userId = %s", userId));
            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return searchUser;
    }

    @Override
    public User findUserByUserName(String userName) {
        List<User> users = findAllUsers();
        User searchUser = null;
        try {
            for (User user : users) {
                if (user.getUserName().equalsIgnoreCase(userName)) {
                    searchUser = user;
                }
            }
            if (searchUser == null) {
                throw new UserNotFoundException(String.format("User not found: userName = %s", userName));
            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return searchUser;
    }

    @Override
    public List<User> findAllUsers() {
        return convertUserDataToList(ioManager.readDocumentData(USERS_SOURCE_PATH));
    }

    @Override
    public void updateUser(Long userId, String newUserName, String newFirstName, String newLastName, String newPassword, Role newUserRole) {
        User user = findUserByUserId(userId);
        String oldUserStr = user.toString();
        populateUserData(user, newUserName, newFirstName, newLastName, newPassword, newUserRole);
        String updatedUserStr = user.toString();
        ioManager.replaceDataLine(USERS_SOURCE_PATH, USERS_CACHE_PATH, oldUserStr, updatedUserStr);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = findUserByUserId(userId);
        String oldUserStr = user.toString();
        String updatedUserStr = "";
        ioManager.replaceDataLine(USERS_SOURCE_PATH, USERS_CACHE_PATH, oldUserStr, updatedUserStr);

    }

    private List<User> convertUserDataToList(List<String> fileData) {
        List<User> result = new ArrayList<>();
        for (int i = 0; i < fileData.size(); i++) {
            User user = buildUser(parseStringUser(fileData.get(i)));
            result.add(user);
        }
        return result;
    }

    private void populateUserData(User targetUser, String newUserName, String newFirstName, String newLastName, String newPassword, Role newUserRole) {
        if (newUserName != null) {
            targetUser.setUserName(newUserName);
        }
        if (newFirstName != null) {
            targetUser.setFirstName(newFirstName);
        }
        if (newLastName != null) {
            targetUser.setLastName(newLastName);
        }
        if (newPassword != null) {
            targetUser.setPassword(newPassword);
        }
        if (newUserRole != null) {
            targetUser.setUserRole(newUserRole);
        }
    }

    private String[] parseStringUser(String line) {
        String[] strings = line.replaceAll("User", "")
                .replaceAll("=", "")
                .replaceAll("'", "")
                .replaceAll("\\{", "")
                .replaceAll(" ", "")
                .replaceAll("\\}", "")
                .replaceAll("userId", "")
                .replaceAll("userName", "")
                .replaceAll("lastName", "")
                .replaceAll("firstName", "")
                .replaceAll("userRole", "")
                .replaceAll("password", "")
                .split(",");
        return strings;
    }

    private User buildUser(String[] userProps) {
        User user = new User();
        user.setUserId(Long.parseLong(userProps[0]));
        user.setUserName(userProps[1]);
        user.setLastName(userProps[2]);
        user.setFirstName(userProps[3]);
        if (userProps[4].equalsIgnoreCase("USER")) {
            user.setUserRole(Role.USER);
        }
        if (userProps[4].equalsIgnoreCase("ADMIN")) {
            user.setUserRole(Role.ADMIN);
        }
        user.setPassword(userProps[5]);
        return user;
    }

}
