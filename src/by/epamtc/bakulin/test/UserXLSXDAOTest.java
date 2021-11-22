package by.epamtc.bakulin.test;

import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.dao.exception.DAOException;
import by.epamtc.bakulin.dao.exception.general.FileAccessException;
import by.epamtc.bakulin.dao.exception.general.IncorrectStateException;
import by.epamtc.bakulin.dao.impl.XLSXUserDAO;
import by.epamtc.bakulin.entity.User;
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
    public void test2() throws IncorrectStateException, FileAccessException {
        User user = userDAO.findByName("tamaraVoron");
        System.out.println(user);
    }
}
