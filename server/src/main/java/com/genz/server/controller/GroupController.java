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
    public List<Group> listAll(){
        return groupService.listAll();
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
    @PutMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public void deleteGroup(@PathVariable(value = "id") Long id){
        groupService.delete(id);
    }

}
