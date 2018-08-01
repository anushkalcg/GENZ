package com.genz.server.controller;

import com.genz.server.model.User;
import com.genz.server.service.user.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nikos.Toulios
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    UserServiceImpl userService;

    @ApiOperation(value = "Finds the user with the associated ( username, password )", response = User.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the user info"),
            @ApiResponse(code = 404, message = "The user didnt found.")
    })
    @GetMapping(produces = "application/json;charset=UTF-8")
    public User login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password){
        return userService.login(username, password);
    }
}
