package by.epamtc.bakulin.controller.command.impl.app;

import by.epamtc.bakulin.controller.command.Command;

public class BadRequestCommand implements Command {

    private static final String cmdResponse = "Bad Request";

    @Override
    public String execute() {
        return cmdResponse;
    }

    @Override
    public void setRequestParameters(String[] requestParameters) {
        throw new UnsupportedOperationException();
    }
}
