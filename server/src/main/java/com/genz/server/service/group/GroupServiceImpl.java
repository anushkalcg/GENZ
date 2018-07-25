package com.genz.server.service.group;

import com.genz.server.exception.ResourceNotFoundException;
import com.genz.server.exception.ResourceValidationException;
import com.genz.server.model.Group;
import com.genz.server.model.Question;
import com.genz.server.model.User;
import com.genz.server.repository.GroupRepository;
import com.genz.server.repository.QuestionRepository;
import com.genz.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class GroupServiceImpl implements GroupService{

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Group addNewUser(Long groupId, Long userId) {

        if(!userRepository.exists(userId)){
            throw new ResourceNotFoundException("NOT FOUND User with ID:" + userId);
        }

        return Optional.ofNullable(groupRepository.findOne(groupId))
                .map(group -> {
                    User user = new User();
                    user.setId(userId);
                    //TODO check if there is actual a addition
                    group.addUser(user);
                    return groupRepository.save(group);
                })
                .orElseThrow(() -> new ResourceNotFoundException("NOT FOUND Group with ID:" + groupId));
    }

    @Override
    public Set<User> getUsers(Long groupId) {
        return Optional.ofNullable(groupRepository.findOne(groupId))
                .map(group -> {
                    return group.getUsers();
                })
                .orElseThrow(() -> new ResourceNotFoundException("NOT FOUND Group with ID:" + groupId));
    }

    @Override
    public Group removeUser(Long groupId, Long userId) {
        if(!userRepository.exists(userId)){
            throw new ResourceNotFoundException("NOT FOUND User with ID:" + userId);
        }

        return Optional.ofNullable(groupRepository.findOne(groupId))
                .map(group -> {
                    User user = new User();
                    user.setId(userId);
                    //TODO check if there is actual a deletion
                    group.removeUser(user);
                    return groupRepository.save(group);
                })
                .orElseThrow(() -> new ResourceNotFoundException("NOT FOUND Group with ID:" + groupId));
    }

    @Override
    public Group addNewQuestion(Long groupId, Long questionId) {
        if(!questionRepository.exists(questionId)){
            throw new ResourceNotFoundException("NOT FOUND Question with ID:" + questionId);
        }

        return Optional.ofNullable(groupRepository.findOne(groupId))
                .map(group -> {
                    Question question = new Question();
                    question.setId(questionId);
                    //TODO check if there is actual a addition
                    group.addQuestion(question);
                    return groupRepository.save(group);
                })
                .orElseThrow(() -> new ResourceNotFoundException("NOT FOUND Group with ID:" + groupId));
    }

    @Override
    public Set<Question> getQuestions(Long groupId) {
        return Optional.ofNullable(groupRepository.findOne(groupId))
                .map(group -> {
                    return group.getQuestions();
                })
                .orElseThrow(() -> new ResourceNotFoundException("NOT FOUND Group with ID:" + groupId));
    }

    @Override
    public Group removeQuestion(Long groupId, Long questionId) {
        if(!questionRepository.exists(questionId)){
            throw new ResourceNotFoundException("NOT FOUND Question with ID:" + questionId);
        }

        return Optional.ofNullable(groupRepository.findOne(groupId))
                .map(group -> {
                    Question question = new Question();
                    question.setId(questionId);
                    //TODO check if there is actual a deletion
                    group.removeQuestion(question);
                    return groupRepository.save(group);
                })
                .orElseThrow(() -> new ResourceNotFoundException("NOT FOUND Group with ID:" + groupId));
    }

    @Override
    public Group add(Group entry) {
        return groupRepository.save(entry);
    }

    @Override
    public List<Group> listAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group update(Group entry) {
        return Optional.ofNullable(groupRepository.findOne(entry.getId()))
                .map(group -> {
                    return groupRepository.save(group);
                })
                .orElseThrow(() -> new ResourceNotFoundException("NOT FOUND Group with ID:" + entry.getId()));
    }

    @Override
    public void delete(Long id) {
        if(!groupRepository.exists(id)){
            throw new ResourceNotFoundException("NOT FOUND Group with ID:" + id);
        }
        groupRepository.delete(id);
    }

    @Override
    public Group get(Long id) {
        return Optional.ofNullable(groupRepository.findOne(id))
                .orElseThrow(() -> new ResourceNotFoundException("NOT FOUND Group with ID:" + id));
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
