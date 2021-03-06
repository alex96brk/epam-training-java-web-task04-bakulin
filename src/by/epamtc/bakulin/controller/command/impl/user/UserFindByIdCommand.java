package by.epamtc.bakulin.controller.command.impl.user;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.controller.command.impl.book.validator.BookValidator;
import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.entity.User;
import by.epamtc.bakulin.service.UserService;
import by.epamtc.bakulin.service.exception.ServiceException;
import by.epamtc.bakulin.service.factory.ServiceFactory;

public class UserFindByIdCommand implements Command {

    private UserDAO userDAO;

    private UserService userService;

    private String[] requestParameters;

    public UserFindByIdCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
        this.userService = ServiceFactory.getInstance().getUserService(userDAO);
    }

    @Override
    public String execute() {
        Integer id = Integer.parseInt(requestParameters[1]);
        String cmdResponse = null;

        try {
            BookValidator.validateId(id);
            User user = userService.findUserById(id);
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
