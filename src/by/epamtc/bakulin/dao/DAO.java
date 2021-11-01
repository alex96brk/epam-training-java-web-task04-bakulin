package by.epamtc.bakulin.dao;

import by.epamtc.bakulin.dao.exception.DAOException;

import java.util.List;

public interface DAO<T> {
    void add(T entity) throws DAOException;

    T findById(Integer id) throws DAOException;

    List<T> findAll() throws DAOException;

    void update(T entity) throws DAOException;

    void delete(Integer id) throws DAOException;
}
