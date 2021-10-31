package by.epamtc.bakulin.service;

import by.epamtc.bakulin.entity.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    User findUserById(Long id);

    User findUserByName(String userName);

    List<User> findAllUsers();

    void updateUser(User user);

    void deleteUser(Long id);


}
