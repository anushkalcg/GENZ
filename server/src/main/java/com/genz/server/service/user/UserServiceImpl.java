package com.genz.server.service.user;

import com.genz.server.exception.ResourceNotFoundException;
import com.genz.server.exception.ResourceValidationException;
import com.genz.server.model.User;
import com.genz.server.model.UserStatus;
import com.genz.server.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Nikos.Toulios
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    private static final int MAX_AGE = 100;
    private static final int MIN_AGE = 18;
    private static final int DEFAULT_USER_SCORE = 0;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User add(User entry) {
        validationAdd(entry);
        entry.setUserStatus(UserStatus.NOT_STARTED);
        try {
            return userRepository.save(entry);
        }catch(DataIntegrityViolationException e){
            throw new ResourceValidationException("Duplicate entries - Constraint violation");
        }
    }

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User entry) {
        validationUpdate(entry);
        return userRepository.save(entry);
    }

    @Override
    public void delete(Long id) {
        if(userRepository.findOne(id) == null){
            throw new ResourceNotFoundException("The user with ID: " + id + " does not exist.");
        }
        userRepository.delete(id);
    }

    @Override
    public User get(Long id) {
        User result = userRepository.findOne(id);
        if(result == null){
            throw new ResourceNotFoundException("User not found");
        }
        return result;
    }

    @Override
    public User login(String username, String password) {
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            throw new ResourceValidationException("Username and password values should not be empty");
        }

        User user = userRepository.findOneByUsernameAndPassword(username, password);

        if(user == null){
            throw new ResourceNotFoundException("The user not found");
        }
        return user;
    }


    @Override
    public void play(Long UserId) {

    }

    @Override
    public void validationAdd(User entry) throws ResourceValidationException {
        if(entry == null){
            throw new ResourceValidationException("The user entry should not be null.");
        }

        if(entry.getId() != null){
            throw new ResourceValidationException("The user id should be null.");
        }

        if(entry.getScore() != DEFAULT_USER_SCORE){
            throw new ResourceValidationException("The user's property age should have values equals to: " + DEFAULT_USER_SCORE);
        }

        validationEntryProperties(entry);
    }

    @Override
    public void validationUpdate(User entry) throws ResourceValidationException, ResourceNotFoundException {
        if(entry == null){
            throw new ResourceValidationException("The user entry should not be null for addition.");
        }

        if(entry.getId() == null){
            throw new ResourceValidationException("The user id should not be null.");
        }

        if(userRepository.findOne(entry.getId()) == null){
            throw new ResourceNotFoundException("The user with ID: " + entry.getId() + " does not exist.");
        }

        if(entry.getScore() < DEFAULT_USER_SCORE){
            throw new ResourceValidationException("The user's property age should have values equals to: " + DEFAULT_USER_SCORE);
        }

        validationEntryProperties(entry);
    }

    @Override
    public void validationEntryProperties(User entry) {
        if(StringUtils.isBlank(entry.getEmail())){
            throw new ResourceValidationException("The user's property email should not be null or empty.");
        }

        if(StringUtils.isBlank(entry.getName())){
            throw new ResourceValidationException("The user's property name should not be null or empty.");
        }

        if(StringUtils.isBlank(entry.getPassword())){
            throw new ResourceValidationException("The user's property password should not be null or empty.");
        }

        if(StringUtils.isBlank(entry.getPhoneNumber())){
            throw new ResourceValidationException("The user's property phone number should not be null or empty.");
        }
        if(StringUtils.isBlank(entry.getSurname())){
            throw new ResourceValidationException("The user's property surname should not be null or empty for addition.");
        }

        int userAge = entry.getAge();
        if(userAge > MAX_AGE || userAge < MIN_AGE){
            throw new ResourceValidationException("The user's property age should have values between [ " + MIN_AGE + " , " + MAX_AGE + " ]");
        }

        if(StringUtils.isBlank(entry.getUsername())){
            throw new ResourceValidationException("The user's property username should not be null or empty for addition.");
        }
    }
}
