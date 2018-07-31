package com.genz.server.controller;

import com.genz.server.model.User;
import com.genz.server.service.user.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Api(value = "userManagement", description = "This API is for user management actions")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @ApiOperation(value = "View the user's information from the associated id.", response = User.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the user info"),
            @ApiResponse(code = 400, message = "Valdation with request."),
            @ApiResponse(code = 404, message = "The user didnt found.")
    })
    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public User getUser(@PathVariable(value = "id") Long id){
        return userService.get(id);
    }

    @ApiOperation(value = "View all users's information.", response = User.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the user info"),
            @ApiResponse(code = 400, message = "Valdation with request."),
    })
    @GetMapping(produces = "application/json;charset=UTF-8")
    public List<User> listAll(){
        return userService.listAll();
    }

    @ApiOperation(value = "Add a new User", response = User.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the user info"),
            @ApiResponse(code = 400, message = "Valdation with request.")
    })
    @PostMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public User addUser(@RequestBody User user){
        return userService.add(user);
    }

    @ApiOperation(value = "Update user's information", response = User.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the user info"),
            @ApiResponse(code = 400, message = "Valdation with request."),
            @ApiResponse(code = 404, message = "The user didnt found.")
    })
    @PutMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public User updateUser(@RequestBody User user){
        return userService.update(user);
    }

    @ApiOperation(value = "Delete a user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the user info"),
            @ApiResponse(code = 400, message = "Valdation with request."),
            @ApiResponse(code = 404, message = "The user didnt found.")
    })
    @PutMapping(value = "/{id}",  produces = "application/json;charset=UTF-8")
    public void deleteUser(@PathVariable(value = "id") Long id){
        userService.delete(id);
    }
}
