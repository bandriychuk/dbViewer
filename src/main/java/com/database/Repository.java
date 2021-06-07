package com.database;

import java.util.List;

public interface Repository<S, T> {

    T findOne(S id);

    List<T> findAll();
}
