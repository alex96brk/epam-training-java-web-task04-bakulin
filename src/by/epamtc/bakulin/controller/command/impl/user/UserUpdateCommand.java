package by.epamtc.bakulin.controller.command.impl.user;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.controller.command.impl.user.validator.UserValidator;
import by.epamtc.bakulin.entity.Role;
import by.epamtc.bakulin.entity.User;
import by.epamtc.bakulin.service.UserService;
import by.epamtc.bakulin.service.factory.TXTServiceFactory;

public class UserUpdateCommand implements Command {

    private TXTServiceFactory txtServiceFactory = TXTServiceFactory.getInstance();
    private UserService userService = txtServiceFactory.getUserService();
    private String[] requestParameters;

    @Override
    public String execute() {
        Integer userId = Integer.parseInt(requestParameters[1]);
        String userName = requestParameters[2];
        String firstName = requestParameters[3];
        String lastName = requestParameters[4];
        String password = requestParameters[5];
        String roleStr = requestParameters[6];
        String cmdResponse = null;
        try {
            UserValidator.validateUserProperties(userId, userName, firstName, lastName, password, roleStr);
            User user = userService.findUserById(userId);
            user.setUserName(userName);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(password);
            if (roleStr.equalsIgnoreCase("user")) {
                user.setUserRole(Role.USER);
            }
            if (roleStr.equalsIgnoreCase("admin")) {
                user.setUserRole(Role.ADMIN);
            }
            userService.updateUser(user);
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
