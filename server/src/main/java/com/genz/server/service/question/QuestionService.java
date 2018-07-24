package com.genz.server.service.question;

import com.genz.server.model.Answer;
import com.genz.server.model.Group;
import com.genz.server.model.Question;
import com.genz.server.service.CommonService;

import java.util.List;

public interface QuestionService extends CommonService<Question> {

    Group addNewAnswer(Long questionId, Answer answer);
    List<Answer> getAnswers(Long questionId);
    Group removeAnswer(Long questionId, Long answerId);
}
