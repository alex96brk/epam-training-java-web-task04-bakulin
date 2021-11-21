package by.epamtc.bakulin.test;

import by.epamtc.bakulin.controller.CommandSequence;
import by.epamtc.bakulin.dao.factory.TXTDAOFactory;
import org.junit.Test;

public class CommandSequenceTest {

    private CommandSequence commandSequence = new CommandSequence(TXTDAOFactory.getInstance().getUserDAO(), TXTDAOFactory.getInstance().getBookDAO());

    @Test
    public void testBookCommand1() {
        String cmdRequest = "book_find_all";
        System.out.println(commandSequence.getCommand(cmdRequest).execute());
    }

    @Test
    public void testBookCommand2() {
        String cmdRequest = "book_find_by_id $-1116902328";
        System.out.println(commandSequence.getCommand(cmdRequest).execute());
    }

    @Test
    public void testBookCommand3() {
        String cmdRequest = "book_add $Dune $Frank Herbert $Fantastic Novel";
        System.out.println(commandSequence.getCommand(cmdRequest).execute());
    }

    @Test
    public void testBookCommand4() {
        String cmdRequest = "book_delete $-1835594412";
        System.out.println(commandSequence.getCommand(cmdRequest).execute());
    }

    @Test
    public void testBookCommand5() {
        String cmdRequest = "book_find_by_author $Frank Herbert";
        System.out.println(commandSequence.getCommand(cmdRequest).execute());
    }

    @Test
    public void testBookCommand6() {
        String cmdRequest = "book_update $1279673643 $Quiet Don $Michael Sholokchov $Historical Novel";
        System.out.println(commandSequence.getCommand(cmdRequest).execute());
    }

    @Test
    public void testUserCommand1() {
        String cmdRequest = "user_find_all";
        System.out.println(commandSequence.getCommand(cmdRequest).execute());
    }

    @Test
    public void testUserCommand2() {
        String cmdRequest = "user_find_by_id $-845424629";
        System.out.println(commandSequence.getCommand(cmdRequest).execute());
    }
    @Test
    public void testUserCommand3() {
        String cmdRequest = "user_add $hromovSanches $Alexander $Hromov $sogha123354";
        System.out.println(commandSequence.getCommand(cmdRequest).execute());
    }
    @Test
    public void testUserCommand4() {
        String cmdRequest = "user_delete $-8569301";
        System.out.println(commandSequence.getCommand(cmdRequest).execute());
    }
    @Test
    public void testUserCommand5() {
        String cmdRequest = "user_find_by_uname $tamaVoron";
        System.out.println(commandSequence.getCommand(cmdRequest).execute());
    }
    @Test
    public void testUserCommand6() {
        String cmdRequest = "user_update $764748527 $tamaraVoron $Tamara $Voron $sglhs09 $admin";
        System.out.println(commandSequence.getCommand(cmdRequest).execute());
    }

}
