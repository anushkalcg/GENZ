package com.genz.server.service.group;

import com.genz.server.exception.ResourceNotFoundException;
import com.genz.server.exception.ResourceValidationException;
import com.genz.server.model.Group;
import com.genz.server.model.Question;
import com.genz.server.model.User;

import java.util.List;

public class GroupServiceImpl implements GroupService{

    @Override
    public Group addNewUser(Long groupId, Long userId) {
        return null;
    }

    @Override
    public List<User> getUsers(Long groupId) {
        return null;
    }

    @Override
    public Group removeUser(Long groupId, Long userId) {
        return null;
    }

    @Override
    public Group addNewQuestion(Long groupId, Long questionId) {
        return null;
    }

    @Override
    public List<Question> getQuestions(Long groupId) {
        return null;
    }

    @Override
    public Group removeQuestion(Long groupId, Long QuestionId) {
        return null;
    }

    @Override
    public Group add(Group entry) {
        return null;
    }

    @Override
    public List<Group> listAll() {
        return null;
    }

    @Override
    public Group update(Group entry) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Group get(Long id) {
        return null;
    }

    @Override
    public void validationAdd(Group entry) throws ResourceValidationException {

    }

    @Override
    public void validationUpdate(Group entry) throws ResourceValidationException, ResourceNotFoundException {

    }

    @Override
    public void validationEntryProperties(Group entry) {

    }
}
