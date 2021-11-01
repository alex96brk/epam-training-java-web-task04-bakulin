package by.epamtc.bakulin.test;

import by.epamtc.bakulin.controller.Controller;
import org.junit.Test;

public class ControllerTest {

    private Controller controller = new Controller();

    @Test
    public void test1() {
        String cmdRequest = "book_find_all";
        String result = controller.runCommand(cmdRequest);
        System.out.println(result);
    }
}
