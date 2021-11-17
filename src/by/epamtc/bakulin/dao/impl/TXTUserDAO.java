package by.epamtc.bakulin.dao.impl;

import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.dao.exception.general.IncorrectStateException;
import by.epamtc.bakulin.io.IOEntityBuilder;
import by.epamtc.bakulin.entity.User;
import by.epamtc.bakulin.io.IOConnectorTXT;

import java.util.ArrayList;
import java.util.List;

import static by.epamtc.bakulin.entity.Role.*;

public class TXTUserDAO extends IOConnectorTXT implements UserDAO, IOEntityBuilder<User> {

    private static final String USERS_SOURCE_PATH = "users.txt.source.path";
    private static final String USERS_CACHE_PATH = "users.txt.source.cache.path";

    public TXTUserDAO() {
    }

    @Override
    public void add(User entity) throws IncorrectStateException {
        entityNullCheck(entity);
        writeDataLine(USERS_SOURCE_PATH, entity + "\n");
    }

    @Override
    public User findById(Integer id) throws IncorrectStateException {
        idNullCheck(id);
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
        return parseFileData(readDocumentData(USERS_SOURCE_PATH));
    }

    @Override
    public void update(User entity) throws IncorrectStateException {
        entityNullCheck(entity);
        updateDataLine(USERS_SOURCE_PATH, USERS_CACHE_PATH, findById(entity.getUserId()).toString(), entity.toString());
    }

    @Override
    public void delete(Integer id) throws IncorrectStateException {
        idNullCheck(id);
       deleteDataLine(USERS_SOURCE_PATH, USERS_CACHE_PATH, findById(id).toString());
    }

    @Override
    public User findByName(String userName) throws IncorrectStateException {
        stringNullCheck(userName);
        List<User> users = findAll();
        User result = null;
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                result = user;
            }
        }
        return result;
    }

    @Override
    public List<User> parseFileData(List<String> fileData) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < fileData.size(); i++) {
            User user = buildEntity(parseStringLine(fileData.get(i)));
            users.add(user);
        }
        return users;
    }

    @Override
    public String[] parseStringLine(String line) {
        String[] strings = line.replace("User{", "")
                .replaceAll("userId=", "")
                .replaceAll("userName=", "")
                .replaceAll("lastName=", "")
                .replaceAll("firstName=", "")
                .replaceAll("userRole=", "")
                .replaceAll("password=", "")
                .replaceAll("'", "")
                .replaceAll("\\}", "")
                .split(", ");

        return strings;
    }

    @Override
    public User buildEntity(String[] entityProps) {
        User user = new User();
        user.setUserId(Integer.parseInt(entityProps[0]));
        user.setUserName(entityProps[1]);
        user.setLastName(entityProps[2]);
        user.setFirstName(entityProps[3]);
        if (entityProps[4].equalsIgnoreCase("USER")) {
            user.setUserRole(USER);
        }
        if (entityProps[4].equalsIgnoreCase("ADMIN")) {
            user.setUserRole(ADMIN);
        }
        user.setPassword(entityProps[5]);
        return user;
    }

    private void idNullCheck(Integer id) throws IncorrectStateException {
        if (id == null) {
            throw new IncorrectStateException("Parameter - id, can not be null or less then 0; id = " + id);
        }
    }

    private void entityNullCheck(User entity) throws IncorrectStateException {
        if (entity == null) {
            throw new IncorrectStateException("Parameter entity can not be null");
        }
    }

    private void stringNullCheck(String userName) throws IncorrectStateException {
        if (userName == null) {
            throw new IncorrectStateException("Method string parameter can not be null");
        }
    }
}
