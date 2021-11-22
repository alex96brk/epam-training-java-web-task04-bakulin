package by.epamtc.bakulin.test;

import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.dao.exception.DAOException;
import by.epamtc.bakulin.dao.exception.general.FileAccessException;
import by.epamtc.bakulin.dao.exception.general.IncorrectStateException;
import by.epamtc.bakulin.dao.impl.XLSXUserDAO;
import by.epamtc.bakulin.entity.User;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.junit.Test;

import java.util.List;


public class UserXLSXDAOTest {
    UserDAO userDAO = new XLSXUserDAO();

    @Test
    public void test1() throws DAOException {
        List<User> users = userDAO.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test2() throws DAOException {
        User user = userDAO.findByName("tamaraVoron");
        System.out.println(user);
        User user1 = userDAO.findById(user.getUserId());
        System.out.println(user1);
    }

    @Test
    public void test3() throws DAOException {
        User user = new User("newUser", "Newer", "News", "123");
        user.setUserId(12345);
        userDAO.add(user);
    }

    @Test
    public void test4() throws DAOException {
        User updateUser = userDAO.findByName("newUser");
        updateUser.setFirstName("James");
        updateUser.setLastName("Stinger");
        userDAO.update(updateUser);
    }

    @Test
    public void test5() throws DAOException {
        userDAO.delete(12345);
    }
}
