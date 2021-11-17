package by.epamtc.bakulin.service.exception.general;

import by.epamtc.bakulin.service.exception.ServiceException;

public class NotFoundException extends ServiceException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Exception e) {
        super(e);
    }
}
