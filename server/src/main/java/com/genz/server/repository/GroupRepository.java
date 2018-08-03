package com.genz.server.repository;

import com.genz.server.model.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nikos.Toulios
 */
@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {
    List<Group> findAll();
    Group findOneByName(String name);
}
