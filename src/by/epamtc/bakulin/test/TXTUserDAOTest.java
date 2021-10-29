package by.epamtc.bakulin.test;

import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.dao.impl.TXTUserDAO;
import by.epamtc.bakulin.io.impl.IOManagerTXT;
import by.epamtc.bakulin.model.Role;
import by.epamtc.bakulin.model.User;
import org.junit.Test;

import java.util.List;

public class TXTUserDAOTest {
    UserDAO dao = new TXTUserDAO(new IOManagerTXT());

    @Test
    public void test1() {
        List<User> users = dao.findAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test2() {
        User newUser =  dao.createUser("janeFrosty", "Jane", "Frost", "124josidjo");
        System.out.println("New User: " + newUser);
        System.out.println("All users: ");
        test1();
    }

    @Test
    public void test3() {
        String searchName = "janeFrosty";
        User searchUser = dao.findUserByUserName(searchName);
        System.out.println("Result: " + searchUser);
    }

    @Test
    public void test4() {
        dao.updateUser(4045569050689157120L, "andrey456holod", "Andrey", "Holodnov", "sjhkjsg2345", Role.USER);
        List<User> users = dao.findAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test5() {
        dao.deleteUser(4045569050689157120L);
    }



}
