package com.genz.server.service.question;

import com.genz.server.exception.ResourceNotFoundException;
import com.genz.server.exception.ResourceValidationException;
import com.genz.server.model.Answer;
import com.genz.server.model.Group;
import com.genz.server.model.Question;
import com.genz.server.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Question addNewAnswer(Long questionId, Answer answer) {
        return Optional.ofNullable(questionRepository.findOne(questionId))
                .map(question -> {

                    //TODO check if there is actual a addition
                    question.addAnswer(answer);
                    return questionRepository.save(question);
                })
                .orElseThrow(() -> new ResourceNotFoundException("NOT FOUND question with ID:" + questionId));

    }

    @Override
    public Set<Answer> getAnswers(Long questionId) {
        return Optional.ofNullable(questionRepository.findOne(questionId))
                .map(question -> {
                    return question.getAnswers();
                })
                .orElseThrow(() -> new ResourceNotFoundException("NOT FOUND Question with ID:" + questionId));
    }

    @Override
    public Group removeAnswer(Long questionId, Long answerId) {
        return null;
    }

    @Override
    public Question add(Question entry) {
        return questionRepository.save(entry);
    }

    @Override
    public List<Question> listAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question update(Question entry) {
        return Optional.ofNullable(questionRepository.findOne(entry.getId()))
                .map(question -> {
                    return questionRepository.save(question);
                })
                .orElseThrow(() -> new ResourceNotFoundException("NOT FOUND question with ID:" + entry.getId()));
    }

    @Override
    public void delete(Long id) {
        if(!questionRepository.exists(id)){
            throw new ResourceNotFoundException("NOT FOUND question with ID:" + id);
        }
        questionRepository.delete(id);
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
