package by.epamtc.bakulin.test;

import by.epamtc.bakulin.dao.factory.TXTDAOFactory;
import by.epamtc.bakulin.entity.User;
import by.epamtc.bakulin.service.UserService;
import by.epamtc.bakulin.service.exception.ServiceException;
import by.epamtc.bakulin.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.List;

public class UserServiceTest {
    UserService userService = new UserServiceImpl(TXTDAOFactory.getInstance().getUserDAO());

    @Test
    public void test1() throws ServiceException {
        List<User> users = userService.findAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
