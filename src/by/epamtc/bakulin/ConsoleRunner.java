package by.epamtc.bakulin;

import by.epamtc.bakulin.controller.CommandSequence;
import by.epamtc.bakulin.controller.Controller;
import by.epamtc.bakulin.controller.UserSession;
import by.epamtc.bakulin.controller.command.CmdId;
import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.dao.factory.impl.TXTDAOFactory;
import by.epamtc.bakulin.service.exception.ServiceException;
import by.epamtc.bakulin.service.factory.ServiceFactory;

import java.util.Scanner;

public class ConsoleRunner {

    public static void main(String[] args) {
        ConsoleRunner runner = ConsoleRunner.getInstance();
        runner.doAuth(runner.getUserSession());
        runner.commandHandler();
    }

    private static Scanner sc = new Scanner(System.in);

    private UserDAO userDAO;

    private BookDAO bookDAO;

    private UserSession userSession;

    private Controller controller;

    private CommandSequence commandSequence;

    private static final String DATA_SOURCE_TYPE_TXT = "TXT";
    private static final String DATA_SOURCE_TYPE_EXCEL = "EXCEL";

    public ConsoleRunner(UserDAO userDAO, BookDAO bookDAO) {
        this.userDAO = userDAO;
        this.bookDAO = bookDAO;
        this.commandSequence = new CommandSequence(userDAO, bookDAO);
        this.controller = new Controller(commandSequence);
        this.userSession = new UserSession(ServiceFactory.getInstance().getUserService(userDAO));
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public BookDAO getBookDAO() {
        return bookDAO;
    }

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public CommandSequence getCommandSequence() {
        return commandSequence;
    }

    public void setCommandSequence(CommandSequence commandSequence) {
        this.commandSequence = commandSequence;
    }

    private static ConsoleRunner getInstance() {
        String source = null;
        ConsoleRunner runner = null;
        printConsoleMessageNextLine("Choose data source.\n\tEnter 'TXT' - to get data from .txt data source\n\tEnter 'EXCEL' - to get data from .xlsx data source");
        do {
            printConsoleMessageCurrentLine("DATA SOURCE TYPE: ");
            source = sc.nextLine();
            if (source.equalsIgnoreCase(DATA_SOURCE_TYPE_TXT)) {
                runner = new ConsoleRunner(TXTDAOFactory.getInstance().getUserDAO(), TXTDAOFactory.getInstance().getBookDAO());
                printConsoleMessageNextLine("Current Data Source: " + DATA_SOURCE_TYPE_TXT + "\n");
            }
            if (source.equalsIgnoreCase(DATA_SOURCE_TYPE_EXCEL)) {
                throw new UnsupportedOperationException("Excel Data Source is not ready");
            }
        } while (runner == null);
        return runner;
    }

    private void doAuth(UserSession userSession) {
        printConsoleMessageNextLine("Library APP started.");
        do {
            try {
                printConsoleMessageNextLine("Please enter your Username:");
                String userName = sc.nextLine();
                printConsoleMessageNextLine("Please enter your Password:");
                String password = sc.nextLine();

                userSession.authenticate(userName, password);
                if (userSession.getAuthority() == null) {
                    throw new ServiceException("Incorrect credentials. Try again");
                }
            } catch (ServiceException e) {
                printConsoleMessageNextLine(e.getMessage());
            }
        } while (userSession.getAuthority() == null);
    }

    private void commandHandler() {
        if (userSession.getAuthority().equalsIgnoreCase("ADMIN")) {
            printConsoleMessageNextLine(adminCmd);
        }
        if (userSession.getAuthority().equalsIgnoreCase("USER")) {
            printConsoleMessageNextLine(userCmd);
        }
        if(userSession.isSessionActive()) {
            String request = null;
            do {
                try {
                    request = sc.nextLine();
                    String response = controller.runCommand(request, userSession.getAuthority());
                    printConsoleMessageNextLine(response);
                }
                catch (NullPointerException e) {
                    printConsoleMessageNextLine("Not Found.");
                }
                catch (NumberFormatException e) {
                    printConsoleMessageNextLine("Incorrect id");
                }
                catch (IndexOutOfBoundsException e) {
                    printConsoleMessageNextLine("Incorrect params sequence");
                }
            } while (!request.equalsIgnoreCase(CmdId.EXIT));
        }
    }

    private static void printConsoleMessageNextLine(String message) {
        System.out.println(message);
    }

    private static void printConsoleMessageCurrentLine(String message) {
        System.out.print(message);
    }

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
}
