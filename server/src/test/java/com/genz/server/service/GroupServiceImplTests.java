package com.genz.server.service;

import com.genz.server.ServerApplicationTests;
import com.genz.server.exception.ResourceNotFoundException;
import com.genz.server.exception.ResourceValidationException;
import com.genz.server.model.*;
import com.genz.server.repository.GroupRepository;
import com.genz.server.repository.QuestionRepository;
import com.genz.server.repository.UserRepository;
import com.genz.server.service.group.GroupServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Nikos.Toulios
 */
public class GroupServiceImplTests extends ServerApplicationTests {

    @Autowired
    private GroupServiceImpl groupService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    GroupRepository groupRepository;

    @Before
    @Override
    public void setUp(){
        userRepository.deleteAll();
        groupRepository.deleteAll();
        questionRepository.deleteAll();
    }

    @Test
    @Transactional
    public void addUser_success(){
        //given

        User user = createUser();
        user = userRepository.save(user);

        Group group = new Group();
        group.setName("group");
        group = groupService.add(group);

        //when

        Group resultedGroup = groupService.addNewUser(group.getId(), user.getId());


        //then
        assertNotNull(resultedGroup);
        assertFalse(resultedGroup.getUsers().isEmpty());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void addUser_user_not_found(){
        //given

        //when
       groupService.addNewUser(10L, 100L);


        //then
    }

    @Test(expected = ResourceNotFoundException.class)
    public void addUser_group_not_found(){
        //given

        User user = createUser();
        user = userRepository.save(user);

        //when
        groupService.addNewUser(10L, user.getId());


        //then
    }

    @Test
    public void addGroup_success_with_user(){
        //given

        User user = createUser();

        Group group = new Group();
        group.setName("group");
        group.addUser(user);

        //when
        Group resultedGroup = groupService.add(group);


        //then
        assertNotNull(resultedGroup);
        assertFalse(resultedGroup.getUsers().isEmpty());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void removeUser_user_not_found(){
        //given

        //when
        groupService.removeUser(10L, 100L);


        //then
    }

    @Test(expected = ResourceNotFoundException.class)
    public void removeUser_group_not_found(){
        //given

        User user = createUser();
        user = userRepository.save(user);

        //when
        groupService.removeUser(10L, user.getId());

        //then
    }

    @Test
    public void removeUser_success_with_user(){
        //given

        User user = createUser();

        Group group = new Group();
        group.setName("group");
        group.addUser(user);

        Group resultedGroup = groupService.add(group);

        assertNotNull(resultedGroup);
        assertFalse(resultedGroup.getUsers().isEmpty());

        //when

        resultedGroup = groupService.removeUser(resultedGroup.getId(), resultedGroup.getUsers().get(0).getId());

        //then
        assertNotNull(resultedGroup);
        assertTrue(resultedGroup.getUsers().isEmpty());
    }

    @Test
    public void addNewQuestion_success(){
        //given

        Question question = createDefaultQuestion();
        question = questionRepository.save(question);

        Group group = new Group();
        group.setName("groupTest");
        group = groupService.add(group);

        //when

        Group resultGroup = groupService.addNewQuestion(group.getId(), question.getId());

        //then
        assertNotNull(resultGroup);
        assertFalse(resultGroup.getQuestions().isEmpty());
        assertEquals(question.getText(), resultGroup.getQuestions().get(0).getText());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void addNewQuestion_question_not_found(){
        //given


        //when
        groupService.addNewQuestion(1L, 10L);


        //then
    }

    @Test(expected = ResourceNotFoundException.class)
    public void addNewQuestion_group_not_found(){
        //given

        Question question = createDefaultQuestion();
        Answer answer = createDefaultAnswer();
        question = questionRepository.save(question);

        //when
        groupService.addNewQuestion(1L, question.getId());


        //then
    }

    @Test(expected = ResourceNotFoundException.class)
    public void removeQuestion_question_not_found(){
        //given



        //when
        groupService.removeQuestion(1L, 10L);


        //then
    }

    @Test(expected = ResourceNotFoundException.class)
    public void removeQuestion_group_not_found(){
        //given

        Question question = createDefaultQuestion();
        Answer answer = createDefaultAnswer();
        question = questionRepository.save(question);

        //when
        groupService.removeQuestion(1L, question.getId());


        //then
    }

    @Test
    public void removeQuestion_success(){
        //given

        Question question = createDefaultQuestion();
        question = questionRepository.save(question);

        Group group = new Group();
        group.setName("groupTest");
        group = groupService.add(group);

        Group resultGroup = groupService.addNewQuestion(group.getId(), question.getId());

        assertNotNull(resultGroup);
        assertFalse(resultGroup.getQuestions().isEmpty());
        assertEquals(question.getText(), resultGroup.getQuestions().get(0).getText());

        //when

        resultGroup = groupService.removeQuestion(group.getId(), question.getId());

        //then

        assertNotNull(resultGroup);
        assertTrue(resultGroup.getQuestions().isEmpty());
    }

    @Test
    public void getQuestions_success(){
        //given

        Question question = createDefaultQuestion();
        question = questionRepository.save(question);

        Question question2 = createDefaultQuestion();
        question2.setText("question 2");
        question2.setPriority(2);

        question = questionRepository.save(question);
        question2 = questionRepository.save(question2);

        Group group = new Group();
        group.setName("groupTest");
        group = groupService.add(group);

        Group resultGroup = groupService.addNewQuestion(group.getId(), question.getId());
        resultGroup = groupService.addNewQuestion(resultGroup.getId(), question2.getId());

        //when

        List<Question> questions = groupService.getQuestions(resultGroup.getId());

        //then

        assertNotNull(questions);
        assertFalse(questions.isEmpty());
        assertEquals(2, questions.size());
        assertEquals(question.getText(), questions.get(0).getText());
        assertEquals(question2.getText(), questions.get(1).getText());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getQuestions_not_found_group(){
        //given


        //when

        groupService.getQuestions(10l);

        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void addGroup_null_entry(){
        //given



        //when
        Group resultGroup = groupService.add(null);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void addGroup_not_null_entry_id(){
        //given

        Group group = new Group();
        group.setId(1L);

        //when
        Group resultGroup = groupService.add(group);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void addGroup_blank(){
        //given

        Group group = new Group();

        //when
        Group resultGroup = groupService.add(group);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void addGroup_double_entries(){
        //given

        Group group = new Group();
        group.setName("group");

        groupService.add(group);

        Group group2 = new Group();
        group2.setName("group");

        //when

        groupService.add(group2);

        //then
    }

    @Test
    public void addGroup_success(){
        //given

        Group group = new Group();
        group.setName("group");

        //when
        Group resultGroup = groupService.add(group);


        //then
        assertNotNull(resultGroup);
        assertEquals("group", resultGroup.getName());
    }

    @Test(expected = ResourceValidationException.class)
    public void updateGroup_null_entry(){
        //given



        //when
        Group resultGroup = groupService.update(null);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void updateGroup_null_entry_id(){
        //given

        Group group = new Group();

        //when
        Group resultGroup = groupService.update(group);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void updateGroup_blank(){
        //given

        Group group = new Group();

        //when
        Group resultGroup = groupService.update(group);


        //then
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateGroup_not_found_entry(){
        //given

        Group group = new Group();
        group.setId(10L);
        //when
        Group resultGroup = groupService.update(group);


        //then
    }

    @Test
    public void updateGroup_success(){
        //given

        Group group = new Group();
        group.setName("group");

        Group resultGroup = groupService.add(group);
        assertNotNull(resultGroup);
        assertEquals("group", resultGroup.getName());
        resultGroup.setName("new group");

        //when

        resultGroup = groupService.update(resultGroup);

        //then

        assertNotNull(resultGroup);
        assertEquals("new group", resultGroup.getName());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void delete_not_found(){
        //given



        //when
        groupService.delete(10L);


        //then
    }

    @Test
    public void delete_success(){
        //given

        Group group = new Group();
        group.setName("group");

        Group resultGroup = groupService.add(group);
        assertNotNull(resultGroup);
        assertEquals("group", resultGroup.getName());
        resultGroup.setName("new group");

        //when

        groupService.delete(resultGroup.getId());

        //then

        assertFalse(groupRepository.exists(resultGroup.getId()));
    }
}
