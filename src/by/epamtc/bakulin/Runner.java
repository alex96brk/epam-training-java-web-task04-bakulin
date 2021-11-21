package by.epamtc.bakulin;

import by.epamtc.bakulin.controller.Controller;
import by.epamtc.bakulin.controller.UserSession;
import by.epamtc.bakulin.controller.command.CmdId;
import by.epamtc.bakulin.dao.factory.TXTDAOFactory;
import by.epamtc.bakulin.service.exception.ServiceException;

import java.util.Scanner;

public class Runner {

    private static Controller controller = new Controller();
    private static Scanner sc = new Scanner(System.in);
    private static UserSession userSession = new UserSession(TXTDAOFactory.getInstance().getUserDAO());

    private static String userCmd = "Enter next cmd request:\n" +
            "User requests:\n" +
            "\tuser_find_all - find all users in the library\n" +
            "\tuser_find_by_id $id - to find user by id\n" +
            "\tuser_find_by_uname $userName - to find user by username\n" +

            "Book requests:\n" +
            "\tbook_find_all - find all books in the library\n" +
            "\tbook_find_by_id $id - to find book by id\n" +
            "\tbook_find_by_author $bookAuthor - to find book by author\n";

    private static String adminCmd = "Enter next cmd request:\n" +
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
            "\tbook_update $id $currentBookName $newBookName $bookAuthor $bookGenre - to update book into the library(ADMIN)\n" +
            "\tbook_delete $id - to delete book from the library(ADMIN)\n" +
            "\tuser_add $userName $firstName $lastName $password - to add book into the library(ADMIN))\n" +
            "\tuser_update $id $currentUserName $newUserName $firstName $lastName $password $role - to add book into the library(ADMIN)\n" +
            "\tuser_delete $id - to delete user from the library(ADMIN)\n" +
            "";

    public static void main(String[] args) {
        doAuth(userSession);

        printInstructions();

        commandHandler();
    }

    private static void doAuth(UserSession userSession) {
        printConsoleMessage("Library APP started.\n");
        do {
            try {
                printConsoleMessage("Please enter your Username:");
                String userName = sc.nextLine();
                printConsoleMessage("Please enter your Password:");
                String password = sc.nextLine();

                userSession.authenticate(userName, password);
                if (userSession.getAuthority() == null) {
                    throw new ServiceException("Incorrect credentials. Try again");
                }
            } catch (ServiceException e) {
                printConsoleMessage(e.getMessage());
            }
        } while (userSession.getAuthority() == null);
    }

    private static void printInstructions() {
        if (userSession.getAuthority().equalsIgnoreCase("ADMIN")) {
            printConsoleMessage(adminCmd);
        }
        if (userSession.getAuthority().equalsIgnoreCase("USER")) {
            printConsoleMessage(userCmd);
        }
    }

    private static void commandHandler() {
        if(userSession.isSessionActive()) {
            String request = null;
            do {
                try {
                    request = sc.nextLine();
                    String response = controller.runCommand(request, userSession.getAuthority());
                    printConsoleMessage(response);
                }
                catch (NullPointerException e) {
                    printConsoleMessage("Not Found.");
                }
                catch (NumberFormatException e) {
                    printConsoleMessage("Incorrect id");
                }
                catch (IndexOutOfBoundsException e) {
                    printConsoleMessage("Incorrect params sequence");
                }
            } while (!request.equalsIgnoreCase(CmdId.EXIT));
        }
    }

    private static void printConsoleMessage(String message) {
        System.out.println(message);
    }
}
