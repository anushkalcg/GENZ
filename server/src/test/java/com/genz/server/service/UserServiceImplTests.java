package com.genz.server.service;

import com.genz.server.ServerApplicationTests;
import com.genz.server.exception.ResourceNotFoundException;
import com.genz.server.exception.ResourceValidationException;
import com.genz.server.model.User;
import com.genz.server.model.UserStatus;
import com.genz.server.repository.UserRepository;
import com.genz.server.service.user.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static org.junit.Assert.*;
/**
 * @author Nikos.Toulios
 */
public class UserServiceImplTests extends ServerApplicationTests {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Before
    @Override
    public void setUp(){
        userRepository.deleteAll();
    }

    @Test(expected = ResourceValidationException.class)
    public void add_violation_of_uniqueness(){
        //given
        User user1 = createUser();
        User user2 = createUser();
        userService.add(user1);

        //when
        userService.add(user2);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void add_null_entry(){
        //given



        //when
        userService.add(null);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void add_not_null_id(){
        //given
        User user = createUser();
        user.setId(10L);


        //when
        userService.add(user);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void add_less_Zero_initial_score(){
        //given
        User user = createUser();
        user.setScore(-10);


        //when
        userService.add(user);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void add_blank_email(){
        //given
        User user = createUser();
        user.setEmail("");


        //when
        userService.add(user);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void add_blank_name(){
        //given
        User user = createUser();
        user.setName("");


        //when
        userService.add(user);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void add_blank_password(){
        //given
        User user = createUser();
        user.setPassword("");


        //when
        userService.add(user);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void add_blank_phonenumber(){
        //given
        User user = createUser();
        user.setPhoneNumber("");


        //when
        userService.add(user);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void add_blank_surname(){
        //given
        User user = createUser();
        user.setSurname("");


        //when
        userService.add(user);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void add_blank_username(){
        //given
        User user = createUser();
        user.setUsername("");


        //when
        userService.add(user);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void add_not_valid_age(){
        //given
        User user = createUser();
        user.setAge(200);


        //when
        userService.add(user);


        //then
    }

    @Test
    public void add_success(){
        //given
        User user = createUser();


        //when
        User resultUser = userService.add(user);


        //then
        assertNotNull(resultUser);
        assertNotNull(resultUser.getId());
        assertEquals(user.getEmail(), resultUser.getEmail());
    }

    @Test(expected = ResourceValidationException.class)
    public void update_null_entry(){
        //given



        //when
        userService.update(null);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void update_null_id(){
        //given
        User user = createUser();


        //when
        userService.update(user);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void update_less_Zero_initial_score(){
        //given
        User user = createUser();
        user.setScore(-10);


        //when
        userService.update(user);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void update_blank_email(){
        //given
        User user = createUser();
        user.setEmail("");


        //when
        userService.update(user);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void update_blank_name(){
        //given
        User user = createUser();
        user.setName("");


        //when
        userService.update(user);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void update_blank_password(){
        //given
        User user = createUser();
        user.setPassword("");


        //when
        userService.update(user);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void update_blank_phonenumber(){
        //given
        User user = createUser();
        user.setPhoneNumber("");


        //when
        userService.update(user);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void update_blank_surname(){
        //given
        User user = createUser();
        user.setSurname("");


        //when
        userService.update(user);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void update_blank_username(){
        //given
        User user = createUser();
        user.setUsername("");


        //when
        userService.update(user);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void update_not_valid_age(){
        //given
        User user = createUser();
        user.setAge(200);


        //when
        userService.update(user);


        //then
    }

    @Test
    public void update_success(){
        //given
        User user = createUser();
        User updateUser = userService.add(user);
        updateUser.setName("name2");

        //when
        User resultUser = userService.update(updateUser);


        //then
        assertNotNull(resultUser);
        assertNotNull(resultUser.getId());
        assertEquals(updateUser.getId(), resultUser.getId());
        assertEquals("name2", resultUser.getName());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void delete_not_existed_id(){
        //given



        //when
        userService.delete(100L);


        //then
    }

    @Test
    public void delete_success(){
        //given
        User user = createUser();
        user = userService.add(user);
        assertTrue(userRepository.exists(user.getId()));

        //when
        userService.delete(user.getId());


        //then
        assertFalse(userRepository.exists(user.getId()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void get_not_existed_id(){
        //given



        //when
        userService.get(100L);


        //then
    }

    @Test
    public void get_success() {
        //given
        User user = createUser();
        user = userService.add(user);
        assertTrue(userRepository.exists(user.getId()));

        //when
        User resultUser = userService.get(user.getId());


        //then
        assertEquals(user.getId(), resultUser.getId());
    }

    @Test(expected = ResourceValidationException.class)
    public void login_empty_username(){
        //given



        //when
        userService.login("", "password");


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void login_empty_passowrd(){
        //given



        //when
        userService.login("", "password");


        //then
    }

    @Test(expected = ResourceNotFoundException.class)
    public void login_not_existed_user(){
        //given



        //when
        userService.login("user", "password");


        //then
    }

    @Test
    public void login_success(){
        //given
        User user = createUser();
        user = userService.add(user);
        assertTrue(userRepository.exists(user.getId()));

        //when
        User resultUser = userService.login("username","password");


        //then
        assertEquals(user.getId(), resultUser.getId());
    }

}
