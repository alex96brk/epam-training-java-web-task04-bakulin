package by.epamtc.bakulin.controller.command.impl;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.controller.command.exception.CommandAlreadyExistsException;
import by.epamtc.bakulin.controller.command.impl.app.AdminHelpCommand;
import by.epamtc.bakulin.controller.command.impl.app.BadRequestCommand;
import by.epamtc.bakulin.controller.command.impl.app.ExitCommand;
import by.epamtc.bakulin.controller.command.impl.app.UserHelpCommand;
import by.epamtc.bakulin.controller.command.impl.book.*;
import by.epamtc.bakulin.controller.command.impl.user.*;
import by.epamtc.bakulin.dao.BookDAO;
import by.epamtc.bakulin.dao.UserDAO;
import by.epamtc.bakulin.dao.factory.LibraryDAOFactory;

import java.util.HashMap;
import java.util.Map;

import static by.epamtc.bakulin.controller.command.impl.CmdId.*;

public class CommandSequence {

    private UserDAO userDAO;

    private BookDAO bookDAO;

    private Map<String, Command> sequence;

    public CommandSequence(LibraryDAOFactory libraryDAOFactory) {
        this.userDAO = libraryDAOFactory.getUserDAO();
        this.bookDAO = libraryDAOFactory.getBookDAO();
        this.sequence = new HashMap<>();
        {
            sequence.put(USER_ADD, new UserAddCommand(userDAO));
            sequence.put(USER_FIND_BY_ID, new UserFindByIdCommand(userDAO));
            sequence.put(USER_FIND_BY_UNAME, new UserFindByNameCommand(userDAO));
            sequence.put(USER_FIND_ALL, new UserFindAllCommand(userDAO));
            sequence.put(USER_UPDATE, new UserUpdateCommand(userDAO));
            sequence.put(USER_DELETE, new UserDeleteCommand(userDAO));
            sequence.put(BOOK_ADD, new BookAddCommand(bookDAO));
            sequence.put(BOOK_FIND_BY_ID, new BookFindByIdCommand(bookDAO));
            sequence.put(BOOK_FIND_BY_AUTHOR, new BookFindByAuthorCommand(bookDAO));
            sequence.put(BOOK_FIND_ALL, new BookFindAllCommand(bookDAO));
            sequence.put(BOOK_UPDATE, new BookUpdateCommand(bookDAO));
            sequence.put(BOOK_DELETE, new BookDeleteCommand(bookDAO));
            sequence.put(BAD_REQUEST, new BadRequestCommand());
            sequence.put(EXIT, new ExitCommand());
            sequence.put(USER_HELP, new UserHelpCommand());
            sequence.put(ADMIN_HELP, new AdminHelpCommand());
        }
    }

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

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public BookDAO getBookDAO() {
        return bookDAO;
    }

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public Map<String, Command> getSequence() {
        return sequence;
    }

    public void setSequence(Map<String, Command> sequence) {
        this.sequence = sequence;
    }

    public void addCommand(String cmdId, Command command) throws CommandAlreadyExistsException {
        if (sequence.containsValue(command)) {
            throw new CommandAlreadyExistsException("Command already exists. Check command sequence.");
        }
        sequence.put(cmdId, command);
    }
}
