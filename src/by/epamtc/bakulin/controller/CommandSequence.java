package by.epamtc.bakulin.controller;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.controller.command.CmdId;
import by.epamtc.bakulin.controller.command.impl.app.BadRequestCommand;
import by.epamtc.bakulin.controller.command.impl.app.ExitCommand;
import by.epamtc.bakulin.controller.command.impl.book.*;
import by.epamtc.bakulin.controller.command.impl.user.*;

import java.util.HashMap;
import java.util.Map;

import static by.epamtc.bakulin.controller.command.CmdId.*;

public class CommandSequence {
    private Map<String, Command> sequence = new HashMap<>();
    {
        sequence.put(USER_ADD, new UserAddCommand());
        sequence.put(USER_FIND_BY_ID, new UserFindByIdCommand());
        sequence.put(USER_FIND_BY_UNAME, new UserFindByNameCommand());
        sequence.put(USER_FIND_ALL, new UserFindAllCommand());
        sequence.put(USER_UPDATE, new UserUpdateCommand());
        sequence.put(USER_DELETE, new UserDeleteCommand());

        sequence.put(BOOK_ADD, new BookAddCommand());
        sequence.put(BOOK_FIND_BY_ID, new BookFindByIdCommand());
        sequence.put(BOOK_FIND_BY_AUTHOR, new BookFindByAuthorCommand());
        sequence.put(BOOK_FIND_ALL, new BookFindAllCommand());
        sequence.put(BOOK_UPDATE, new BookUpdateCommand());
        sequence.put(BOOK_DELETE, new BookDeleteCommand());

        sequence.put(BAD_REQUEST, new BadRequestCommand());
        sequence.put(EXIT, new ExitCommand());
    }

    public CommandSequence() {}

    public Command getCommand(String cmdRequest) {
        String[] requestParameters = cmdRequest.split(" \\$");
        Command command = null;

        if (cmdRequest != null) {
            try {
                String cmdId = requestParameters[0].toUpperCase();
                command = sequence.get(cmdId);
                command.setRequestParameters(requestParameters);
            } catch (IllegalArgumentException exception) {
                command = sequence.get(BAD_REQUEST);
            }
        }

        return command;
    }


}
