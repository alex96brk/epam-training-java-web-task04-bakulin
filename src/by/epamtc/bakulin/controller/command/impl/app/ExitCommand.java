package by.epamtc.bakulin.controller.command.impl.app;

import by.epamtc.bakulin.controller.command.CmdId;
import by.epamtc.bakulin.controller.command.Command;

public class ExitCommand implements Command {
    @Override
    public String execute() {
        return CmdId.EXIT.name();
    }

    @Override
    public void setRequestParameters(String[] requestParameters) {

    }
}
