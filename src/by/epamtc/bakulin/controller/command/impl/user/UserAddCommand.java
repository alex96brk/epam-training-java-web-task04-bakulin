package by.epamtc.bakulin.controller.command.impl.user;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.entity.User;
import by.epamtc.bakulin.service.UserService;
import by.epamtc.bakulin.service.factory.TXTServiceFactory;

public class UserAddCommand implements Command {

    private TXTServiceFactory txtServiceFactory = TXTServiceFactory.getInstance();
    private UserService userService = txtServiceFactory.getUserService();
    private String[] requestParameters;

    @Override
    public String execute() {
        String userName = requestParameters[1];
        String firstName = requestParameters[2];
        String lastName = requestParameters[3];
        String password = requestParameters[4];
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

    @Override
    public void setRequestParameters(String[] requestParameters) {
        this.requestParameters = requestParameters;
    }
}
