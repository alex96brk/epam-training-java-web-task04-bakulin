package by.epamtc.bakulin.dao.impl;

import by.epamtc.bakulin.dao.DAO;
import by.epamtc.bakulin.io.IOEntityCollector;
import by.epamtc.bakulin.io.IOConnector;
import by.epamtc.bakulin.entity.Role;
import by.epamtc.bakulin.entity.User;

import java.util.ArrayList;
import java.util.List;

public class TXTUserDAO implements DAO<User>, IOEntityCollector<User> {

    private static final String USERS_SOURCE_PATH = "users.txt.source.path";
    private static final String USERS_CACHE_PATH = "users.txt.source.cache.path";

    private IOConnector ioConnector;

    public TXTUserDAO() {
    }

    public TXTUserDAO(IOConnector ioConnector) {
        this.ioConnector = ioConnector;
    }

    public void setIoConnector(IOConnector ioConnector) {
        this.ioConnector = ioConnector;
    }

    @Override
    public void add(User entity) {
        ioConnector.writeDataLine(USERS_SOURCE_PATH, entity.toString() + "\n");
    }

    @Override
    public User findById(Long id) {
        List<User> users = findAll();
        User result = null;
        for (User user : users) {
            if (user.getUserId().equals(id)) {
                result = user;
            }
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        return collectFileData(ioConnector.readDocumentData(USERS_SOURCE_PATH));
    }

    @Override
    public void update(User entity) {
        ioConnector.updateDataLine(USERS_SOURCE_PATH, USERS_CACHE_PATH, findById(entity.getUserId()).toString(), entity.toString());
    }

    @Override
    public void delete(Long id) {
        ioConnector.deleteDataLine(USERS_SOURCE_PATH, USERS_CACHE_PATH, findById(id).toString());
    }

    @Override
    public List<User> collectFileData(List<String> fileData) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < fileData.size(); i++) {
            User user = buildEntity(parseStringLine(fileData.get(i)));
            users.add(user);
        }
        return users;
    }

    @Override
    public String[] parseStringLine(String line) {
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

    @Override
    public User buildEntity(String[] entityProps) {
        User user = new User();
        user.setUserId(Long.parseLong(entityProps[0]));
        user.setUserName(entityProps[1]);
        user.setLastName(entityProps[2]);
        user.setFirstName(entityProps[3]);
        if (entityProps[4].equalsIgnoreCase("USER")) {
            user.setUserRole(Role.USER);
        }
        if (entityProps[4].equalsIgnoreCase("ADMIN")) {
            user.setUserRole(Role.ADMIN);
        }
        user.setPassword(entityProps[5]);
        return user;
    }
}
