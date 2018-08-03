package com.genz.server.controller;

import com.genz.server.model.Group;
import com.genz.server.model.User;
import com.genz.server.service.group.GroupServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Nikos.Toulios
 */
@RestController
@RequestMapping("/api/group")
@Api(value = "groupManagement", description = "This API is for group management actions")
public class GroupController {

    @Autowired
    GroupServiceImpl groupService;

    @ApiOperation(value = "View the group's information from the associated id.", response = Group.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the group info"),
            @ApiResponse(code = 400, message = "Valdation with request."),
            @ApiResponse(code = 404, message = "The group didnt found.")
    })
    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public Group getGroup(@PathVariable(value = "id") Long id){
        return groupService.get(id);
    }

    @ApiOperation(value = "View all groups's information.", response = Group.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the group info"),
            @ApiResponse(code = 400, message = "Valdation with request."),
    })
    @GetMapping(produces = "application/json;charset=UTF-8")
    public List<Group> listAll(@RequestParam(value = "group_name", required = false) String groupName){
        return groupService.listAll(groupName);
    }

    @ApiOperation(value = "Add a new Group", response = Group.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the group info"),
            @ApiResponse(code = 400, message = "Valdation with request.")
    })
    @PostMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public Group addGroup(@RequestBody Group group){
        return groupService.add(group);
    }

    @ApiOperation(value = "Update group's information", response = Group.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the group info"),
            @ApiResponse(code = 400, message = "Valdation with request."),
            @ApiResponse(code = 404, message = "The group didnt found.")
    })
    @PutMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public Group updateGroup(@RequestBody Group group){
        return groupService.update(group);
    }

    @ApiOperation(value = "Delete a group")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the group info"),
            @ApiResponse(code = 400, message = "Valdation with request."),
            @ApiResponse(code = 404, message = "The group didnt found.")
    })
    @DeleteMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public void deleteGroup(@PathVariable(value = "id") Long id){
        groupService.delete(id);
    }

    @ApiOperation(value = "Add a new User into the group")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the group info"),
            @ApiResponse(code = 400, message = "Valdation with request."),
            @ApiResponse(code = 404, message = "The group or the user didnt found.")
    })
    @PutMapping(value = "/{id}/users/{user_id}", produces = "application/json;charset=UTF-8")
    public void addNewUser(@PathVariable(value = "id") Long id,
                           @PathVariable(value = "user_id") Long userId){
        groupService.addNewUser(id, userId);
    }

    @ApiOperation(value = "Remove a User from the group")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the group info"),
            @ApiResponse(code = 400, message = "Valdation with request."),
            @ApiResponse(code = 404, message = "The group or the user didnt found.")
    })
    @DeleteMapping(value = "/{id}/users/{user_id}", produces = "application/json;charset=UTF-8")
    public void removeUser(@PathVariable(value = "id") Long id,
                           @PathVariable(value = "user_id") Long userId){
        groupService.removeUser(id, userId);
    }

    @ApiOperation(value = "List all the User's from the group")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the group info"),
            @ApiResponse(code = 404, message = "The group didnt found.")
    })
    @GetMapping(value = "/{id}/users", produces = "application/json;charset=UTF-8")
    public void getUsers(@PathVariable(value = "id") Long id){
        groupService.listUsers(id);
    }

    @ApiOperation(value = "Add a Question into the group")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the group info"),
            @ApiResponse(code = 400, message = "Validation with request."),
            @ApiResponse(code = 404, message = "The group or the question didnt found.")
    })
    @PutMapping(value = "/{id}/questions/{question_id}", produces = "application/json;charset=UTF-8")
    public void addQuestion(@PathVariable(value = "id") Long id,
                               @PathVariable(value = "question_id") Long questionId){
        groupService.addNewQuestion(id, questionId);
    }

    @ApiOperation(value = "Add a Question into every group")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the group info"),
            @ApiResponse(code = 400, message = "Validation with request."),
            @ApiResponse(code = 404, message = "The group or the question didnt found.")
    })
    @PutMapping(value = "/questions/all/{question_id}", produces = "application/json;charset=UTF-8")
    public void addQuestions(@PathVariable(value = "question_id") Long questionId){
        groupService.addNewQuestionToEveryGroup(questionId);
    }

    @ApiOperation(value = "Remove a Question from the group")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the group info"),
            @ApiResponse(code = 400, message = "Valdation with request."),
            @ApiResponse(code = 404, message = "The group or the question didnt found.")
    })
    @DeleteMapping(value = "/{id}/questions/{question_id}", produces = "application/json;charset=UTF-8")
    public void removeQuestion(@PathVariable(value = "id") Long id,
                           @PathVariable(value = "question_id") Long questionId){
        groupService.removeQuestion(id, questionId);
    }

    @ApiOperation(value = "List all the Question's from the group")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the group info"),
            @ApiResponse(code = 404, message = "The group didnt found.")
    })
    @GetMapping(value = "/{id}/questions", produces = "application/json;charset=UTF-8")
    public void getQuestions(@PathVariable(value = "id") Long id){
        groupService.getQuestions(id);
    }

}
