package com.bottle.team.service;

import com.bottle.team.model.Solution;

/**
 * Created by Viki on 10/15/2016.
 */
public interface Service<T> {
    Iterable<T> findAll();

    T save(T object);

    void delete(Long id);

    T findById(Long id);
}
