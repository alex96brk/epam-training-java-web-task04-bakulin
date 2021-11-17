package by.epamtc.bakulin.test;

import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.dao.exception.DAOException;
import by.epamtc.bakulin.dao.impl.TXTUserDAO;
import by.epamtc.bakulin.entity.Role;
import by.epamtc.bakulin.entity.User;
import org.junit.Test;

import java.util.List;

public class TXTUserDAOTest {

    UserDAO dao = new TXTUserDAO();

    @Test
    public void test1() throws DAOException {
        List<User> users = dao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test2() throws DAOException {
        User user = new User("hover14", "Alex", "Hover", "ahahfoa14");
        dao.add(user);
    }

    @Test
    public void test3() throws DAOException {
        User user = dao.findById(179857332);
        user.setUserName("Alexander");
        user.setUserRole(Role.ADMIN);
        dao.update(user);
    }

    @Test
    public void test4() throws DAOException {
        dao.delete(528008672);
    }

}
