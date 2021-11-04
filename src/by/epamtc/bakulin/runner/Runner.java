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

        UserSession userSession = new UserSession();
        String userName = sc.nextLine();
        String password = sc.nextLine();
        System.out.println("uName: " + userName + " password: " + password);

        try {
            userSession.authenticate(userName, password);
        } catch (ServiceException e) {
            System.out.println("Incorrect credentials");
        }

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
