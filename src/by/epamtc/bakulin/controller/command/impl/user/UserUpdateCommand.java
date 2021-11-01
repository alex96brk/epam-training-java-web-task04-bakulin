package by.epamtc.bakulin.controller.command.impl.user;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.entity.Role;
import by.epamtc.bakulin.entity.User;
import by.epamtc.bakulin.service.UserService;
import by.epamtc.bakulin.service.factory.TXTServiceFactory;

public class UserUpdateCommand implements Command {

    private TXTServiceFactory txtServiceFactory = TXTServiceFactory.getInstance();
    private UserService userService = txtServiceFactory.getUserService();

    @Override
    public String execute(String cmdRequest) {
        String[] target = cmdRequest.split(" \\$");
        Long userId = Long.parseLong(target[1]);
        String userName = target[2];
        String firstName = target[3];
        String lastName = target[4];
        String password = target[5];
        String roleStr = target[6];
        String cmdResponse = null;
        try {
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
}
