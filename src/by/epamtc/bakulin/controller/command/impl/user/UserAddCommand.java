package by.epamtc.bakulin.controller.command.impl.user;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.controller.command.impl.user.validator.UserValidator;
import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.entity.User;
import by.epamtc.bakulin.service.UserService;
import by.epamtc.bakulin.service.exception.ServiceException;
import by.epamtc.bakulin.service.exception.general.EntryAlreadyExistsException;
import by.epamtc.bakulin.service.factory.ServiceFactory;

public class UserAddCommand implements Command {

    private UserDAO userDAO;

    private UserService userService;

    private String[] requestParameters;

    public UserAddCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
        this.userService = ServiceFactory.getInstance().getUserService(userDAO);
    }

    @Override
    public String execute() {
        String userName = requestParameters[1];
        String firstName = requestParameters[2];
        String lastName = requestParameters[3];
        String password = requestParameters[4];
        String cmdResponse = null;

        try {
            UserValidator.validateUserProperties(userName, firstName, lastName, password);
            UserValidator.validateUniqueUserName(userName, userService.findAllUsers());
            User user = new User(userName,firstName,lastName,password);
            userService.addUser(user);
            cmdResponse = user.toString();
        } catch (EntryAlreadyExistsException e) {
            cmdResponse = e.getMessage();
        }
        catch (ServiceException e) {
            cmdResponse = "Bad request";
        }

        return cmdResponse;
    }

    @Override
    public void setRequestParameters(String[] requestParameters) {
        this.requestParameters = requestParameters;
    }
}
