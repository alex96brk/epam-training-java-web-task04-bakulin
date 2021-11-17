package by.epamtc.bakulin.controller.command.impl.user;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.controller.command.impl.user.validator.UserValidator;
import by.epamtc.bakulin.entity.User;
import by.epamtc.bakulin.service.UserService;
import by.epamtc.bakulin.service.exception.ServiceException;
import by.epamtc.bakulin.service.factory.TXTServiceFactory;

public class UserFindByNameCommand implements Command {

    private TXTServiceFactory txtServiceFactory = TXTServiceFactory.getInstance();
    private UserService userService = txtServiceFactory.getUserService();
    private String[] requestParameters;

    @Override
    public String execute() {
        String userName = requestParameters[1];
        String cmdResponse = null;
        try {
            UserValidator.validateUserName(userName);
            User user = userService.findUserByName(userName);
            cmdResponse = user.toString();
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
