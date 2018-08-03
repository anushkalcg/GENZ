package com.genz.server.service.group;

import com.genz.server.model.Group;
import com.genz.server.model.Question;
import com.genz.server.model.User;
import com.genz.server.service.CommonService;

import java.util.List;

/**
 * @author Nikos.Toulios
 */
public interface GroupService extends CommonService<Group> {

    Group getGroupByName(String name);
    Group addNewUser(Long groupId, Long userId);
    Group removeUser(Long groupId, Long userId);
    public List<User> listUsers(Long groupId);

    Group addNewQuestion(Long groupId, Long questionId);
    List<Question> getQuestions(Long groupId);
    Group removeQuestion(Long groupId, Long QuestionId);
    void addNewQuestionToEveryGroup(Long questionId);
}
