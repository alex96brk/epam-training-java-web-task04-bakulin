package by.epamtc.bakulin.dao.exception.general;

import by.epamtc.bakulin.dao.exception.DAOException;

public class IncorrectStateException extends DAOException {

    public IncorrectStateException(String message) {
        super(message);
    }

    public IncorrectStateException(Exception e) {
        super(e);
    }
}
