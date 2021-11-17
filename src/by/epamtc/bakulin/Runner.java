package by.epamtc.bakulin;

import by.epamtc.bakulin.controller.Controller;
import by.epamtc.bakulin.controller.UserSession;
import by.epamtc.bakulin.controller.command.CmdId;
import by.epamtc.bakulin.entity.User;
import by.epamtc.bakulin.service.exception.ServiceException;

import java.util.Scanner;

public class Runner {

    private static Controller controller = new Controller();
    private static Scanner sc = new Scanner(System.in);
    private static UserSession userSession = new UserSession();

    public static void main(String[] args) {

        doAuth(userSession);

        String userCmd = "Enter next cmd request:\n" +
                "User requests:\n" +
                "\tuser_find_all - find all users in the library\n" +
                "\tuser_find_by_id $id - to find user by id\n" +
                "\tuser_find_by_uname $userName - to find user by username\n" +

                "Book requests:\n" +
                "\tbook_find_all - find all books in the library\n" +
                "\tbook_find_by_id $id - to find book by id\n" +
                "\tbook_find_by_author $bookAuthor - to find book by author\n";

        String adminCmd = "Enter next cmd request:\n" +
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
        if (userSession.getAuthority().equalsIgnoreCase("ADMIN")) {
            System.out.println(adminCmd);
        }
        if (userSession.getAuthority().equalsIgnoreCase("USER")) {
            System.out.println(userCmd);
        }

        if(userSession.isSessionActive()) {
            String request = null;
            do {
                try {
                    request = sc.nextLine();
                    String response = controller.runCommand(request, userSession.getAuthority());
                    System.out.println(response);
                } catch (NullPointerException e) {
                    System.out.println("Not Found");
                }
            } while (!request.equalsIgnoreCase(CmdId.EXIT.name()));
        }

    }

    public static void doAuth(UserSession userSession) {
        System.out.println("Library APP started.\n");
        do {
            try {
                System.out.println("Please enter your Username:");
                String userName = sc.nextLine();
                System.out.println("Please enter your Password:");
                String password = sc.nextLine();

                userSession.authenticate(userName, password);
                if (userSession.getAuthority() == null) {
                    throw new ServiceException("Incorrect credentials. Try again");
                }
            } catch (ServiceException e) {
                System.out.println(e.getMessage());
            }
        } while (userSession.getAuthority() == null);
    }
}
