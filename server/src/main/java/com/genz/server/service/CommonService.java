package com.genz.server.service;

import com.genz.server.model.AbstractEntry;

import java.util.List;

public interface CommonService<T extends AbstractEntry> {

    T add(T entry);
    List<T> listAll();
    T update(T entry);
    void delete(Long id);
    T get(Long id);
}
