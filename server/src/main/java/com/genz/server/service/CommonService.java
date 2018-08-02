package com.genz.server.service;

import com.genz.server.exception.ResourceNotFoundException;
import com.genz.server.exception.ResourceValidationException;
import com.genz.server.model.AbstractEntry;

import java.util.List;

/**
 * @author Nikos.Toulios
 */
public interface CommonService<T extends AbstractEntry> {

    T add(T entry) throws Exception;
    List<T> listAll();
    T update(T entry);
    void delete(Long id);
    T get(Long id);
    void validationAdd(T entry) throws ResourceValidationException;
    void validationUpdate(T entry) throws ResourceValidationException, ResourceNotFoundException;
    void validationEntryProperties(T entry);
}
