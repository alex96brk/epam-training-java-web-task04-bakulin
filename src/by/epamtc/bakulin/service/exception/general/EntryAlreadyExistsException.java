package by.epamtc.bakulin.service.exception.general;

public class EntryAlreadyExistsException extends Exception {
    public EntryAlreadyExistsException(String message) {
        super(message);
    }

    public EntryAlreadyExistsException(Exception e) {
        super(e);
    }
}
