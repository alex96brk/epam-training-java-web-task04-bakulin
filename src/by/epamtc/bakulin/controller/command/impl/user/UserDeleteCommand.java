package by.epamtc.bakulin.controller.command.impl.user;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.controller.command.impl.book.validator.BookValidator;
import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.service.UserService;
import by.epamtc.bakulin.service.factory.ServiceFactory;

public class UserDeleteCommand implements Command {

    private UserDAO userDAO;

    private UserService userService;

    private String[] requestParameters;

    public UserDeleteCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
        this.userService = ServiceFactory.getInstance().getUserService(userDAO);
    }

    @Override
    public String execute() {
        Integer id = Integer.parseInt(requestParameters[1]);
        String cmdResponse = null;
        try {
            BookValidator.validateId(id);
            userService.deleteUser(id);
            cmdResponse = String.format("Deleted successfully; userId = %d", id);
        } catch (Exception e) {
            cmdResponse = e.getMessage();
        }
        return cmdResponse;
    }

    @Override
    public void setRequestParameters(String[] requestParameters) {
        this.requestParameters = requestParameters;
    }
}
