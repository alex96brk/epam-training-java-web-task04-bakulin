package by.epamtc.bakulin.controller.command.impl.book.validator;

import by.epamtc.bakulin.service.exception.ServiceException;

public class BookValidator {

    public static void validateBookProperties(Integer id, String bookName, String bookAuthor, String bookGenre) throws ServiceException {
        validateId(id);
        validateBookProperties(bookName, bookAuthor, bookGenre);
    }

    public static void validateBookProperties(String bookName, String bookAuthor, String bookGenre) throws ServiceException {
        validateBookName(bookName);
        validateAuthor(bookAuthor);
        validateBookGenre(bookGenre);
    }

    public static void validateId(Integer id) throws ServiceException {
        if (id == null || id < 0) {
            throw new ServiceException("Id can not be null or less then 0");
        }
    }

    public static void validateBookName(String bookName) throws ServiceException {
        if (bookName == null) {
            throw new ServiceException("Book Name can not be null");
        }
    }

    public static void validateBookGenre(String bookGenre) throws ServiceException {
        if (bookGenre == null) {
            throw new ServiceException("Book Genre can not be null");
        }
    }

    public static void validateAuthor(String bookAuthor) throws ServiceException {
        if (bookAuthor == null) {
            throw new ServiceException("Author Name can not be null");
        }
    }

}
