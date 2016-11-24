package com.bottle.team.service;

/**
 * Created by Viki on 10/15/2016.
 */
public interface Service<T> {
    Iterable<T> findAll();

    T save(T object);

    T add (T object);

    void delete(Long id);

    T findById(Long id);
}
