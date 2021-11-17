package by.epamtc.bakulin.dao;

import by.epamtc.bakulin.dao.exception.general.IncorrectStateException;

import java.util.List;

public interface DAO<T> {
    void add(T entity) throws IncorrectStateException;

    T findById(Integer id) throws IncorrectStateException;

    List<T> findAll();

    void update(T entity) throws IncorrectStateException;

    void delete(Integer id) throws IncorrectStateException;
}
