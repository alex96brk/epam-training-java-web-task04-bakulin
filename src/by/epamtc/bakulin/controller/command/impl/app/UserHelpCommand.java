package by.epamtc.bakulin.controller.command.impl.app;

import by.epamtc.bakulin.controller.command.Command;

import static by.epamtc.bakulin.controller.command.impl.CommandConstant.*;

public class UserHelpCommand implements Command {

    @Override
    public void setRequestParameters(String[] requestParameters) {}

    @Override
    public String execute() {
        return USER_CMD;
    }
}
