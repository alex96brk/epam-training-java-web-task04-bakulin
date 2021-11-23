package by.epamtc.bakulin.controller.command.impl.app;

import by.epamtc.bakulin.controller.command.Command;

import static by.epamtc.bakulin.controller.command.impl.CommandConstant.ADMIN_CMD;


public class AdminHelpCommand implements Command {
    @Override
    public void setRequestParameters(String[] requestParameters) {}

    @Override
    public String execute() {
        return ADMIN_CMD;
    }
}
