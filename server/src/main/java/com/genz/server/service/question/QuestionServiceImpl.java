package com.genz.server.service.question;

import com.genz.server.exception.ResourceNotFoundException;
import com.genz.server.exception.ResourceValidationException;
import com.genz.server.model.Answer;
import com.genz.server.model.Group;
import com.genz.server.model.Question;
import com.genz.server.repository.AnswerRepository;
import com.genz.server.repository.QuestionRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Nikos.Toulios
 */
@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

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
    public Question setCorrectAnswer(Long qouestionId, Long answerId){
        Question question = questionRepository.findOne(qouestionId);
        if(question == null){
            throw new ResourceNotFoundException("NOT FOUND Question with ID: " +qouestionId);
        }

        if((question.getAnswers() == null) || (question.getAnswers().isEmpty())){
            throw new ResourceValidationException("Question does not have potential answers");
        }

        Answer answer = answerRepository.findOne(answerId);
        if(answer == null){
            throw new ResourceNotFoundException("NOT FOUND Answer with ID: " +answer);
        }


        boolean isAnswerExistedInQuestion = question.getAnswers().stream().filter(answerVal -> answerVal.getId() == answerId).count() > 0;
        if(!isAnswerExistedInQuestion){
            throw new ResourceValidationException("Answer with ID: " + answerId + " is not associated with Question with ID: " + qouestionId);
        }

        question.setCorrectAswer(answerId);
        return questionRepository.save(question);
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
        return Optional.ofNullable(questionRepository.findOne(id))
                .map(question -> {
                    return question;
                })
                .orElseThrow(() -> new ResourceNotFoundException("NOT FOUND question with ID:" + id));
    }

    @Override
    public void validationAdd(Question entry) throws ResourceValidationException {
        if(entry == null){
            throw new ResourceValidationException("The value of question should not be NULL");
        }

        if(entry.getId() != null){
            throw new ResourceValidationException("The value of question ID should be NULL");
        }

        validationEntryProperties(entry);
    }

    @Override
    public void validationUpdate(Question entry) throws ResourceValidationException, ResourceNotFoundException {
        if(entry == null){
            throw new ResourceValidationException("The value of question should not be NULL");
        }

        if(entry.getId() == null){
            throw new ResourceValidationException("The value of question ID should not be NULL");
        }

        if(!questionRepository.exists(entry.getId())){
            throw new ResourceNotFoundException("Question didnt found for ID: "+ entry.getId());
        }
    }

    @Override
    public void validationEntryProperties(Question entry) {
        if(StringUtils.isBlank(entry.getText())){
            throw new ResourceValidationException("The value of group name should not be blank");
        }

        List<Answer> answers = entry.getAnswers();
        if(entry.getAnswers() != null){
            answers.forEach(answer -> checkValidAnswer(answer));
        }
    }

    private void checkValidAnswer(Answer answer){
        Long answerId = answer.getId();
        if(answerId != null){
            if(!answerRepository.exists(answerId)){
                throw new ResourceNotFoundException("The answer with ID " + answerId + " does not exist");
            }
        }

        if(StringUtils.isBlank(answer.getText())){
            throw new ResourceNotFoundException("The answer text should not be empty");
        }
    }
}
