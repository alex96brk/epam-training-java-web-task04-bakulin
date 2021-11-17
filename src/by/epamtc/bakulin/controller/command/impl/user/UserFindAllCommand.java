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
    private String[] requestParameters;

    @Override
    public String execute() {
        String cmdResponse = null;
        try {
            List<User> users = userService.findAllUsers();
            cmdResponse = users.toString();
        } catch (ServiceException e) {
            cmdResponse = e.getMessage();
        }
        return cmdResponse;
    }

    @Override
    public void setRequestParameters(String[] requestParameters) {
        this.requestParameters = requestParameters;
    }
}
