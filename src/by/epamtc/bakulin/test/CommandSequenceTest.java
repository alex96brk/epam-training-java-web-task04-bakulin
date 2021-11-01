package by.epamtc.bakulin.test;

import by.epamtc.bakulin.controller.CommandSequence;
import org.junit.Test;

public class CommandSequenceTest {

    private CommandSequence commandSequence = new CommandSequence();

    @Test
    public void testBookCommand1() {
        String cmdRequest = "book_find_all";
        System.out.println(commandSequence.getCommand(cmdRequest).execute(cmdRequest));
    }

    @Test
    public void testBookCommand2() {
        String cmdRequest = "book_find_by_id $1083762518538963968";
        System.out.println(commandSequence.getCommand(cmdRequest).execute(cmdRequest));
    }

    @Test
    public void testBookCommand3() {
        String cmdRequest = "book_add $Dune $Frank Herbert $Fantastic Novel";
        System.out.println(commandSequence.getCommand(cmdRequest).execute(cmdRequest));
    }

    @Test
    public void testBookCommand4() {
        String cmdRequest = "book_delete $3765047529279459328";
        System.out.println(commandSequence.getCommand(cmdRequest).execute(cmdRequest));
    }

    @Test
    public void testBookCommand5() {
        String cmdRequest = "book_find_by_author $Frank Herbert";
        System.out.println(commandSequence.getCommand(cmdRequest).execute(cmdRequest));
    }

    @Test
    public void testBookCommand6() {
        String cmdRequest = "book_update $8944158836706102272 $Code Complete $Steve McConnel $Computer Science";
        System.out.println(commandSequence.getCommand(cmdRequest).execute(cmdRequest));
    }

    @Test
    public void testUserCommand1() {
        String cmdRequest = "user_find_all";
        System.out.println(commandSequence.getCommand(cmdRequest).execute(cmdRequest));
    }

    @Test
    public void testUserCommand2() {
        String cmdRequest = "user_find_by_id $5333580729289848832";
        System.out.println(commandSequence.getCommand(cmdRequest).execute(cmdRequest));
    }
    @Test
    public void testUserCommand3() {
        String cmdRequest = "user_add $hromovSanches $Alexander $Hromov $sogha123354";
        System.out.println(commandSequence.getCommand(cmdRequest).execute(cmdRequest));
    }
    @Test
    public void testUserCommand4() {
        String cmdRequest = "user_delete $8629434380267018240";
        System.out.println(commandSequence.getCommand(cmdRequest).execute(cmdRequest));
    }
    @Test
    public void testUserCommand5() {
        String cmdRequest = "user_find_by_uname $tamaVoron";
        System.out.println(commandSequence.getCommand(cmdRequest).execute(cmdRequest));
    }
    @Test
    public void testUserCommand6() {
        String cmdRequest = "user_update $8960285287894992896 $anderHolod $Holodnov $Andrew $sglhs09 $admin";
        System.out.println(commandSequence.getCommand(cmdRequest).execute(cmdRequest));
    }

}
