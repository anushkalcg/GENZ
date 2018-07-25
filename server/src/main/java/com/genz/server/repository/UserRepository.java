package com.genz.server.repository;

import com.genz.server.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    User findOneByUsernameAndPassword(String username, String password);
}
