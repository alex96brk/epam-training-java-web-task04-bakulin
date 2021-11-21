package by.epamtc.bakulin.controller.command.impl.user;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.controller.command.impl.user.validator.UserValidator;
import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.dao.factory.TXTDAOFactory;
import by.epamtc.bakulin.entity.Role;
import by.epamtc.bakulin.entity.User;
import by.epamtc.bakulin.service.UserService;
import by.epamtc.bakulin.service.exception.ServiceException;
import by.epamtc.bakulin.service.exception.general.EntryAlreadyExistsException;
import by.epamtc.bakulin.service.factory.ServiceFactory;

public class UserUpdateCommand implements Command {

    private UserDAO userDAO = TXTDAOFactory.getInstance().getUserDAO();

    private UserService userService = ServiceFactory.getInstance().getUserService(userDAO);

    private String[] requestParameters;

    @Override
    public String execute() {
        Integer userId = Integer.parseInt(requestParameters[1]);
        String currentUserName = requestParameters[2];
        String newUserName = requestParameters[3];
        String firstName = requestParameters[4];
        String lastName = requestParameters[5];
        String password = requestParameters[6];
        String roleStr = requestParameters[7];
        String cmdResponse = null;
        try {
            UserValidator.validateUserProperties(userId, currentUserName, firstName, lastName, password, roleStr);
            User user = userService.findUserById(userId);
            if (!newUserName.equals(currentUserName)) {
                UserValidator.validateUniqueUserName(newUserName, userService.findAllUsers());
                user.setUserName(newUserName);
            }
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
        } catch (EntryAlreadyExistsException e) {
            cmdResponse = e.getMessage();
        }
        catch (ServiceException e) {
            cmdResponse = e.getMessage();
        }
        return cmdResponse;
    }

    @Override
    public void setRequestParameters(String[] requestParameters) {
        this.requestParameters = requestParameters;
    }
}
