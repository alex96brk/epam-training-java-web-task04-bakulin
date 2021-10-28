package by.epamtc.bakulin.test;

import by.epamtc.bakulin.dao.impl.TXTUserDAO;
import by.epamtc.bakulin.io.IOManager;
import by.epamtc.bakulin.io.impl.IOManagerTXT;
import by.epamtc.bakulin.model.Role;
import by.epamtc.bakulin.model.User;
import org.junit.Test;

import java.util.List;

public class TXTConnectorTest {
    IOManager ioManager = new IOManagerTXT();
    @Test
    public void test1() {
        List<String> list = ioManager.readDocumentData("users.source.path");
        for(String line : list) {
            System.out.println(line);
        }
    }

    @Test
    public void test2() {
        User user1 = new User("fredMuller", "Frederick", "Muller", "afihaosfh123");
        ioManager.appendDataLine("users.source.path", user1.toString() + "\n", true);
    }

    @Test
    public void test3() {
        TXTUserDAO dao = new TXTUserDAO();
        User user = dao.findUserByUserName("user4ik");
        String oldStr = user.toString();
        System.out.println(oldStr);

        user.setUserName("freddie");
        user.setFirstName("Fred");
        user.setLastName("Mercury");
        user.setUserRole(Role.ADMIN);
        String newStr = user.toString();
        System.out.println(newStr);

        ioManager.replaceDataLine("users.source.path", "users.source.cache.path", oldStr, newStr);
    }


}
