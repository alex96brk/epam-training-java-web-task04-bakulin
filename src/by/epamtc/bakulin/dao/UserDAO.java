package by.epamtc.bakulin.dao;

import by.epamtc.bakulin.model.Role;
import by.epamtc.bakulin.model.User;

import java.util.List;

public interface UserDAO {

    User createUser(String userName, String firstName, String lastName, String password);

    User findUserByUserId(Long userId);

    User findUserByUserName(String userName);

    List<User> findAllUsers();

    void updateUser(Long userId, String newUserName, String newFirstName, String newLastName, String newPassword, Role newUserRole);

    void deleteUser(Long userId);
}
