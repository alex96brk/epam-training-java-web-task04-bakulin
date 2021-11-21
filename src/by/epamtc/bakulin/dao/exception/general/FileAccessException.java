package by.epamtc.bakulin.dao.exception.general;

import by.epamtc.bakulin.dao.exception.DAOException;

public class FileAccessException extends DAOException {

    public FileAccessException(String message) {
        super(message);
    }

    public FileAccessException(Exception e) {
        super(e);
    }
}
