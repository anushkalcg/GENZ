package com.genz.server.service.question;

import com.genz.server.model.Answer;
import com.genz.server.model.Group;
import com.genz.server.model.Question;
import com.genz.server.service.CommonService;

import java.util.Set;

public interface QuestionService extends CommonService<Question> {

    Question addNewAnswer(Long questionId, Answer answer);
    Set<Answer> getAnswers(Long questionId);
    Group removeAnswer(Long questionId, Long answerId);
}
