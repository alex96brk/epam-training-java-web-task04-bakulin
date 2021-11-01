package by.epamtc.bakulin.test;

import by.epamtc.bakulin.controller.CommandSequence;
import org.junit.Test;

import java.util.Scanner;

public class CommandSequenceTest {

    private CommandSequence commandSequence = new CommandSequence();

    @Test
    public void test1() {
        String cmdRequest = "book_find_all";
        System.out.println(commandSequence.getCommand(cmdRequest).execute(cmdRequest));
    }

    @Test
    public void test2() {
        String cmdRequest = "book_find_by_id $1083762518538963968";
        System.out.println(commandSequence.getCommand(cmdRequest).execute(cmdRequest));
    }

    @Test
    public void test3() {
        String cmdRequest = "book_add $Dune $Frank Herbert $Fantastic Novel";
        System.out.println(commandSequence.getCommand(cmdRequest).execute(cmdRequest));
    }

    @Test
    public void test4() {
        String cmdRequest = "book_delete $3765047529279459328";
        System.out.println(commandSequence.getCommand(cmdRequest).execute(cmdRequest));
    }

    @Test
    public void test5() {
        String cmdRequest = "book_find_by_author $Frank Herbert";
        System.out.println(commandSequence.getCommand(cmdRequest).execute(cmdRequest));
    }

    @Test
    public void test6() {
        String cmdRequest = "book_update $8944158836706102272 $Code Complete $Steve McConnel $Computer Science";
        System.out.println(commandSequence.getCommand(cmdRequest).execute(cmdRequest));
    }

}
