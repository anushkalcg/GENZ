package com.genz.server.service.group;

import com.genz.server.model.Group;
import com.genz.server.model.Question;
import com.genz.server.model.User;
import com.genz.server.service.CommonService;

import java.util.List;
import java.util.Set;

public interface GroupService extends CommonService<Group> {

    Group addNewQuestion(Long groupId, Long questionId);
    List<Question> getQuestions(Long groupId);
    Group removeQuestion(Long groupId, Long QuestionId);
}
