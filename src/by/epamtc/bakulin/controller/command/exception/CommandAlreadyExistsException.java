package by.epamtc.bakulin.controller.command.exception;

public class CommandAlreadyExistsException extends Exception {
    public CommandAlreadyExistsException(String message) {
        super(message);
    }
    public CommandAlreadyExistsException(Exception exception) {
        super(exception);
    }
}
