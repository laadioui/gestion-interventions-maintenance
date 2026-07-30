package com.maintenx.dao;
import java.util.List;
import java.util.Optional;
public interface GenericDAO<T, ID> {
    T save(T entity);
    void update(T entity);
    void deleteLogical(ID id);
    Optional<T> findById(ID id);
    List<T> findAll();
}
