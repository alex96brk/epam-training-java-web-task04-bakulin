package by.epamtc.bakulin.controller;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.controller.command.impl.book.BookFindAllCommand;
import by.epamtc.bakulin.controller.command.impl.book.BookFindByAuthorCommand;
import by.epamtc.bakulin.controller.command.impl.book.BookFindByIdCommand;
import by.epamtc.bakulin.controller.command.impl.user.UserFindAllCommand;
import by.epamtc.bakulin.controller.command.impl.user.UserFindByIdCommand;
import by.epamtc.bakulin.controller.command.impl.user.UserFindByNameCommand;
import by.epamtc.bakulin.entity.Role;

public class Controller {

    private static final CommandSequence commandSequence = new CommandSequence();

    public static CommandSequence getCommandSequence() {
        return commandSequence;
    }

    public String runCommand(String cmdRequest, String userAuthRole) {
        String result = null;
        Command command = commandSequence.getCommand(cmdRequest);
        if (userAuthRole.equalsIgnoreCase(Role.ADMIN.name())) {
            result = command.execute();
        }
        if (userAuthRole.equalsIgnoreCase(Role.USER.name())) {
            if (command instanceof UserFindAllCommand ||
                    command instanceof UserFindByIdCommand ||
                    command instanceof UserFindByNameCommand ||
                    command instanceof BookFindAllCommand ||
                    command instanceof BookFindByIdCommand ||
                    command instanceof BookFindByAuthorCommand) {
                result = command.execute();
            } else {
                result = "You do not enough authorities to change data in this repository";
            }
        }
        return result;
    }

}
