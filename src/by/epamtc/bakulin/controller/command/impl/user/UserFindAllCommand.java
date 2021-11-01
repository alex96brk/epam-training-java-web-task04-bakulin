package by.epamtc.bakulin.controller.command.impl.user;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.entity.User;
import by.epamtc.bakulin.service.UserService;
import by.epamtc.bakulin.service.exception.ServiceException;
import by.epamtc.bakulin.service.factory.TXTServiceFactory;

import java.util.List;

public class UserFindAllCommand implements Command {

    private TXTServiceFactory txtServiceFactory = TXTServiceFactory.getInstance();
    private UserService userService = txtServiceFactory.getUserService();

    @Override
    public String execute(String cmdRequest) {
        String cmdResponse = null;
        try {
            List<User> users = userService.findAllUsers();
            cmdResponse = users.toString();
        } catch (ServiceException e) {
            cmdResponse = "Bad request";
        }
        return cmdResponse;
    }
}
