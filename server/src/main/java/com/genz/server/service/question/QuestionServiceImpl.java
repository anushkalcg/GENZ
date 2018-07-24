package com.genz.server.service.question;

import com.genz.server.exception.ResourceNotFoundException;
import com.genz.server.exception.ResourceValidationException;
import com.genz.server.model.Answer;
import com.genz.server.model.Group;
import com.genz.server.model.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    @Override
    public Group addNewAnswer(Long questionId, Answer answer) {
        return null;
    }

    @Override
    public List<Answer> getAnswers(Long questionId) {
        return null;
    }

    @Override
    public Group removeAnswer(Long questionId, Long answerId) {
        return null;
    }

    @Override
    public Question add(Question entry) {
        return null;
    }

    @Override
    public List<Question> listAll() {
        return null;
    }

    @Override
    public Question update(Question entry) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Question get(Long id) {
        return null;
    }

    @Override
    public void validationAdd(Question entry) throws ResourceValidationException {

    }

    @Override
    public void validationUpdate(Question entry) throws ResourceValidationException, ResourceNotFoundException {

    }

    @Override
    public void validationEntryProperties(Question entry) {

    }
}
