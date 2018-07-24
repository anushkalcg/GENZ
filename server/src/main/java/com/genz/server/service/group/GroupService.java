package com.genz.server.service.group;

import com.genz.server.model.Group;
import com.genz.server.model.Question;
import com.genz.server.model.User;
import com.genz.server.service.CommonService;

import java.util.List;

public interface GroupService extends CommonService<Group> {
    Group addNewUser(Long groupId, Long userId);
    List<User> getUsers(Long groupId);
    Group removeUser(Long groupId, Long userId);

    Group addNewQuestion(Long groupId, Long questionId);
    List<Question> getQuestions(Long groupId);
    Group removeQuestion(Long groupId, Long QuestionId);
}
