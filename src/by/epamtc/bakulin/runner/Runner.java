package by.epamtc.bakulin.runner;

import by.epamtc.bakulin.controller.Controller;
import by.epamtc.bakulin.controller.UserSession;
import by.epamtc.bakulin.controller.command.CmdId;
import by.epamtc.bakulin.service.exception.ServiceException;

import java.util.Scanner;

public class Runner {

    private static Controller controller = new Controller();

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Library APP started.\nPlease enter your Username and Password:");

        UserSession userSession = new UserSession();
        String userName = sc.nextLine();
        String password = sc.nextLine();

        try {
            userSession.authenticate(userName, password);
        } catch (ServiceException e) {
            System.out.println("Incorrect credentials");
        }

        String instructure = "Enter next cmd request:\n" +

                "User requests:\n" +
                "\tuser_find_all - find all users in the library\n" +
                "\tuser_find_by_id $id - to find user by id\n" +
                "\tuser_find_by_uname $userName - to find user by username\n" +

                "Book requests:\n" +
                "\tbook_find_all - find all books in the library\n" +
                "\tbook_find_by_id $id - to find book by id\n" +
                "\tbook_find_by_author $bookAuthor - to find book by author\n" +

                "ADMIN requests:\n" +
                "\tbook_add $bookName $bookAuthor $bookGenre - to add book into the library(ADMIN)\n" +
                "\tbook_update $id $bookName $bookAuthor $bookGenre - to update book into the library(ADMIN)\n" +
                "\tbook_delete $id - to delete book from the library(ADMIN)\n" +
                "\tuser_add $userName $firstName $lastName $password - to add book into the library(ADMIN))\n" +
                "\tuser_update $id $userName $firstName $lastName $password $role - to add book into the library(ADMIN)\n" +
                "\tuser_delete $id - to delete user from the library(ADMIN)\n" +
                "";
        System.out.println(instructure);

        if(userSession.isSessionActive()) {
            String request = null;
            do {
                try {
                    request = sc.nextLine();
                    String response = controller.runCommand(request, userSession.getAuthority());
                    System.out.println(response);
                } catch (NullPointerException e) {
                    System.out.println("Exit application");
                }
            } while (!request.equalsIgnoreCase(CmdId.EXIT.name()));
        }

    }
}
