package com.genz.server.service;

import com.genz.server.ServerApplicationTests;
import com.genz.server.exception.ResourceNotFoundException;
import com.genz.server.model.Group;
import com.genz.server.model.User;
import com.genz.server.repository.UserRepository;
import com.genz.server.service.group.GroupServiceImpl;
import com.genz.server.service.user.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GroupServiceImplTests extends ServerApplicationTests {

    @Autowired
    private GroupServiceImpl groupService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    UserRepository userRepository;

    @Before
    @Override
    public void setUp(){
        userRepository.deleteAll();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void addNewUser_user_does_not_exists(){
        //given



        //when
        groupService.addNewUser(11l, 100l);


        //then
    }

    @Test(expected = ResourceNotFoundException.class)
    public void addNewUser_group_does_not_exists(){
        //given

        User user = new User();
        user.setName("Nikos");
        user = userRepository.save(user);

        //when

        groupService.addNewUser(10L, user.getId());

        //then
    }

//    @Test
//    public void addNewUser_success(){
//        //given
//
//        User user = new User();
//        user.setName("Nikos");
//        user = userRepository.save(user);
//
//        Group group = new Group();
//        group.setName("Group 1");
//        group = groupService.add(group);
//
//        //when
//
//        Group resultGroup = groupService.addNewUser(group.getId(), user.getId());
//
//
//        //then
//
//        System.out.println("");
//    }



}
