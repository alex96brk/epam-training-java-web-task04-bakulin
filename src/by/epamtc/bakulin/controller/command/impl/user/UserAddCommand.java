package by.epamtc.bakulin.controller.command.impl.user;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.entity.User;
import by.epamtc.bakulin.service.UserService;
import by.epamtc.bakulin.service.factory.TXTServiceFactory;

public class UserAddCommand implements Command {

    private TXTServiceFactory txtServiceFactory = TXTServiceFactory.getInstance();
    private UserService userService = txtServiceFactory.getUserService();

    @Override
    public String execute(String cmdRequest) {
        String[] target = cmdRequest.split(" \\$");
        String userName = target[1];
        String firstName = target[2];
        String lastName = target[3];
        String password = target[4];
        String cmdResponse = null;

        try {
            User user = new User(userName,firstName,lastName,password);
            userService.addUser(user);
            cmdResponse = user.toString();
        } catch (Exception e) {
            e.printStackTrace();
            cmdResponse = "Bad request";
        }

        return cmdResponse;
    }
}
