package com.genz.server.service.question;

import com.genz.server.model.Answer;
import com.genz.server.model.Question;
import com.genz.server.service.CommonService;

import java.util.List;

/**
 * @author Nikos.Toulios
 */
public interface QuestionService extends CommonService<Question> {

    Question addNewAnswer(Long questionId, Answer answer);
    List<Answer> getAnswers(Long questionId);
    Question removeAnswer(Long questionId, Long answerId);
    Question setCorrectAnswer(Long qouestionId, Long answerId);
}
