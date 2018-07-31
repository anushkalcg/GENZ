package com.genz.server.service.question;

import com.genz.server.exception.ResourceNotFoundException;
import com.genz.server.exception.ResourceValidationException;
import com.genz.server.model.Answer;
import com.genz.server.model.Question;
import com.genz.server.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Question addNewAnswer(Long questionId, Answer answer) {
        return Optional.ofNullable(questionRepository.findOne(questionId))
                .map(question -> {
                    if(answer == null){
                        throw new ResourceValidationException("Answer object should not be null");
                    }
                    question.addAnswer(answer);
                    return questionRepository.save(question);
                })
                .orElseThrow(() -> new ResourceNotFoundException("NOT FOUND question with ID:" + questionId));

    }

    @Override
    public List<Answer> getAnswers(Long questionId) {
        return Optional.ofNullable(questionRepository.findOne(questionId))
                .map(Question::getAnswers)
                .orElseThrow(() -> new ResourceNotFoundException("NOT FOUND Question with ID:" + questionId));
    }

    @Override
    public Question removeAnswer(Long questionId, Long answerId) {
        return Optional.ofNullable(questionRepository.findOne(questionId))
                .map(question -> {
                    List<Answer> answers = question.getAnswers();
                    if((answers != null) && (!answers.isEmpty())){
                        if(answers.removeIf(answer -> answer.getId()==answerId)){
                            question.setAnswers(answers);
                            questionRepository.save(question);
                            return question;
                        }
                    }
                    throw new ResourceNotFoundException("NOT FOUND answer with ID:" + answerId +" associated with question with ID::" + questionId);
                })
                .orElseThrow(() -> new ResourceNotFoundException("NOT FOUND question with ID:" + questionId));
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
                    return questionRepository.save(entry);
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
