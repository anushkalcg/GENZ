package com.genz.server.repository;

import com.genz.server.model.Answer;
import com.genz.server.model.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nikos.Toulios
 */
@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {
}
