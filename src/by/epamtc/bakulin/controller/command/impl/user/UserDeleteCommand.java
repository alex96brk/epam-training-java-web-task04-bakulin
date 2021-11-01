package by.epamtc.bakulin.controller.command.impl.user;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.service.UserService;
import by.epamtc.bakulin.service.factory.TXTServiceFactory;

public class UserDeleteCommand implements Command {

    private TXTServiceFactory txtServiceFactory = TXTServiceFactory.getInstance();
    private UserService userService = txtServiceFactory.getUserService();

    @Override
    public String execute(String cmdRequest) {
        String[] target = cmdRequest.split(" \\$");
        Long id = Long.parseLong(target[1]);
        String cmdResponse = null;
        try {
            userService.deleteUser(id);
            cmdResponse = String.format("Deleted successfully; userId = %d", id);
        } catch (Exception e) {
            e.printStackTrace();
            cmdResponse = "Bad request";
        }
        return cmdResponse;
    }
}
