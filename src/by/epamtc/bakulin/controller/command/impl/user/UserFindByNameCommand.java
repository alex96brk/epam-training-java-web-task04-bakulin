package by.epamtc.bakulin.controller.command.impl.user;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.entity.User;
import by.epamtc.bakulin.service.UserService;
import by.epamtc.bakulin.service.factory.TXTServiceFactory;

public class UserFindByNameCommand implements Command {

    private TXTServiceFactory txtServiceFactory = TXTServiceFactory.getInstance();
    private UserService userService = txtServiceFactory.getUserService();

    @Override
    public String execute(String cmdRequest) {
        String[] target = cmdRequest.split(" \\$");
        String userName = target[1];
        String cmdResponse = null;
        try {
            User user = userService.findUserByName(userName);
            cmdResponse = user.toString();
        } catch (Exception e) {
            e.printStackTrace();
            cmdResponse = "Bad request";
        }
        return cmdResponse;
    }
}
