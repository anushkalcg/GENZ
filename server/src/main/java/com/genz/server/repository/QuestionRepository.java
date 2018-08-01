package com.genz.server.repository;

import com.genz.server.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nikos.Toulios
 */
@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
    List<Question> findAllByGroup(Long groupId);
    List<Question> findAll();
}
