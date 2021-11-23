package by.epamtc.bakulin.controller;


import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.controller.command.impl.CommandSequence;
import by.epamtc.bakulin.entity.Role;

import static by.epamtc.bakulin.controller.command.impl.CmdId.*;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private static CommandSequence commandSequence;

    private List<Command> userAuthorityCommandList;

    public Controller(CommandSequence commandSequence) {
        this.commandSequence = commandSequence;
        {
            userAuthorityCommandList = new ArrayList<>();
            userAuthorityCommandList.add(commandSequence.getCommand(USER_FIND_ALL));
            userAuthorityCommandList.add(commandSequence.getCommand(USER_FIND_BY_ID));
            userAuthorityCommandList.add(commandSequence.getCommand(USER_FIND_BY_UNAME));
            userAuthorityCommandList.add(commandSequence.getCommand(BOOK_FIND_ALL));
            userAuthorityCommandList.add(commandSequence.getCommand(BOOK_FIND_BY_ID));
            userAuthorityCommandList.add(commandSequence.getCommand(BOOK_FIND_BY_AUTHOR));
            userAuthorityCommandList.add(commandSequence.getCommand(USER_HELP));
            userAuthorityCommandList.add(commandSequence.getCommand(EXIT));
        }
    }

    public Controller() {}

    public CommandSequence getCommandSequence() {
        return commandSequence;
    }


    public String runCommand(String cmdRequest, String userAuthRole) {
        String result = null;
        Command command = commandSequence.getCommand(cmdRequest);

        if (userAuthRole.equalsIgnoreCase(Role.ADMIN.name())) {
            result = command.execute();
        }
        if (userAuthRole.equalsIgnoreCase(Role.USER.name())) {
            if (userAuthorityCommandList.contains(command)) {
                result = command.execute();
            } else {
                result = "You do not enough authorities to change data in this repository";
            }
        }
        return result;
    }

    public void setCommandSequence(CommandSequence commandSequence) {
        this.commandSequence = commandSequence;
    }
}
