package by.epamtc.bakulin.service.exception;

public class ServiceException extends Exception{

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Exception e) {
        super(e);
    }
}
