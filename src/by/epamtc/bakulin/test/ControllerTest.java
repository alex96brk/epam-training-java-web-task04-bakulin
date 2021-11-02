package by.epamtc.bakulin.test;

import by.epamtc.bakulin.controller.Controller;
import org.junit.Test;

import java.util.Scanner;

public class ControllerTest {

    private static Controller controller = new Controller();

    @Test
    public void test1() {
        String cmdRequest = "book_find_all";
        String result = controller.runCommand(cmdRequest);
        System.out.println(result);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String request = sc.nextLine();
        String result = controller.runCommand(request);
        System.out.println(result);
    }
}
