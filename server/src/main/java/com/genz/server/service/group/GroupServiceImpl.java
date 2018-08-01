package com.genz.server.service.group;

import com.genz.server.exception.ResourceNotFoundException;
import com.genz.server.exception.ResourceValidationException;
import com.genz.server.model.Group;
import com.genz.server.model.Question;
import com.genz.server.model.User;
import com.genz.server.repository.GroupRepository;
import com.genz.server.repository.QuestionRepository;
import com.genz.server.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Nikos.Toulios
 */
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
        User user = userRepository.findOne(userId);
        if(user == null){
            throw new ResourceNotFoundException("NOT FOUND User with ID:" + user);
        }

        return Optional.ofNullable(groupRepository.findOne(groupId))
                .map(group -> {
                    group.addUser(user);
                    return groupRepository.save(group);
                })
                .orElseThrow(() -> new ResourceNotFoundException("NOT FOUND Group with ID:" + groupId));
    }

    @Override
    public Group removeUser(Long groupId, Long userId) {
        User user = userRepository.findOne(userId);
        if(user == null){
            throw new ResourceNotFoundException("NOT FOUND User with ID:" + user);
        }

        return Optional.ofNullable(groupRepository.findOne(groupId))
                .map(group -> {
                    group.removeUser(user);
                    return groupRepository.save(group);
                })
                .orElseThrow(() -> new ResourceNotFoundException("NOT FOUND Group with ID:" + groupId));
    }

    @Override
    public List<User> listUsers(Long groupId) {
        return Optional.ofNullable(groupRepository.findOne(groupId))
                .map(group -> {
                    return group.getUsers();
                })
                .orElseThrow(() -> new ResourceNotFoundException("NOT FOUND Group with ID:" + groupId));
    }

    @Override
    public Group addNewQuestion(Long groupId, Long questionId) {
        Question question = questionRepository.findOne(questionId);
        if(question == null){
            throw new ResourceNotFoundException("NOT FOUND Question with ID:" + questionId);
        }

        return Optional.ofNullable(groupRepository.findOne(groupId))
                .map(group -> {
                    group.addQuestion(question);
                    return groupRepository.save(group);
                })
                .orElseThrow(() -> new ResourceNotFoundException("NOT FOUND Group with ID:" + groupId));
    }

    @Override
    public List<Question> getQuestions(Long groupId) {
        return Optional.ofNullable(groupRepository.findOne(groupId))
                .map(group -> {
                    return group.getQuestions();
                })
                .orElseThrow(() -> new ResourceNotFoundException("NOT FOUND Group with ID:" + groupId));
    }

    @Override
    public Group removeQuestion(Long groupId, Long questionId) {
        Question question = questionRepository.findOne(questionId);
        if(question == null){
            throw new ResourceNotFoundException("NOT FOUND Question with ID:" + questionId);
        }

        return Optional.ofNullable(groupRepository.findOne(groupId))
                .map(group -> {
                    group.removeQuestion(question);
                    return groupRepository.save(group);
                })
                .orElseThrow(() -> new ResourceNotFoundException("NOT FOUND Group with ID:" + groupId));
    }

    @Override
    public Group add(Group entry) {
        try {
            validationAdd(entry);
            if (entry.getUsers() != null) {
                entry.getUsers().forEach(user -> user.addGroup(entry));
            }
            return groupRepository.save(entry);
        }catch(DataIntegrityViolationException e){
            throw new ResourceValidationException("Duplicate entries - Constraint violation");
        }
    }

    @Override
    public List<Group> listAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group update(Group entry) {
        validationUpdate(entry);
        return Optional.ofNullable(groupRepository.findOne(entry.getId()))
                .map(group -> {
                    return groupRepository.save(entry);
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
        if(entry == null){
            throw new ResourceValidationException("The value of group should not be NULL");
        }

        if(entry.getId() != null){
            throw new ResourceValidationException("The value of group ID should be NULL");
        }

        validationEntryProperties(entry);
    }

    @Override
    public void validationUpdate(Group entry) throws ResourceValidationException, ResourceNotFoundException {
        if(entry == null){
            throw new ResourceValidationException("The value of group should not be NULL");
        }

        if(entry.getId() == null){
            throw new ResourceValidationException("The value of group ID should not be NULL");
        }

        if(!groupRepository.exists(entry.getId())){
            throw new ResourceNotFoundException("Group didnt found for ID: "+ entry.getId());
        }
    }

    @Override
    public void validationEntryProperties(Group entry) {
        if(StringUtils.isBlank(entry.getName())){
            throw new ResourceValidationException("The value of group name should not be blank");
        }
    }
}
