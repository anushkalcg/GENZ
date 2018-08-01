package com.genz.server.controller;

import com.genz.server.model.Answer;
import com.genz.server.model.Group;
import com.genz.server.model.Question;
import com.genz.server.model.User;
import com.genz.server.service.question.QuestionServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
@Api(value = "questionManagement", description = "This API is for question management actions")
public class QuestionController {

    @Autowired
    private QuestionServiceImpl questionService;

    @ApiOperation(value = "View the question's information from the associated id.", response = Question.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the question info"),
            @ApiResponse(code = 400, message = "Valdation with request."),
            @ApiResponse(code = 404, message = "The question didnt found.")
    })
    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public Question getQuestion(@PathVariable(value = "id") Long id){
        return questionService.get(id);
    }

    @ApiOperation(value = "View all question's information.", response = Question.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the question info"),
            @ApiResponse(code = 400, message = "Valdation with request."),
    })
    @GetMapping(produces = "application/json;charset=UTF-8")
    public List<Question> listAll(){
        return questionService.listAll();
    }

    @ApiOperation(value = "Add a new Question", response = Question.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the group info"),
            @ApiResponse(code = 400, message = "Valdation with request.")
    })
    @PostMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public Question addQuestion(@RequestBody Question question){
        return questionService.add(question);
    }

    @ApiOperation(value = "Update question's information", response = Question.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the question info"),
            @ApiResponse(code = 400, message = "Valdation with request."),
            @ApiResponse(code = 404, message = "The question didnt found.")
    })
    @PutMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public Question updateQuestion(@RequestBody Question question){
        return questionService.update(question);
    }

    @ApiOperation(value = "Delete a question")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the question info"),
            @ApiResponse(code = 400, message = "Valdation with request."),
            @ApiResponse(code = 404, message = "The question didnt found.")
    })
    @DeleteMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public void deleteQuestion(@PathVariable(value = "id") Long id){
        questionService.delete(id);
    }

    @ApiOperation(value = "Add a new Answer into the question")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the question info"),
            @ApiResponse(code = 400, message = "Valdation with request."),
            @ApiResponse(code = 404, message = "The question didnt found.")
    })
    @PutMapping(value = "/{id}/answer", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public void addAnswer(@PathVariable(value = "id") Long id,
                               @RequestBody Answer answer){
        questionService.addNewAnswer(id, answer);
    }

    @ApiOperation(value = "Delete an answer")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the question info"),
            @ApiResponse(code = 400, message = "Valdation with request."),
            @ApiResponse(code = 404, message = "The question didnt found.")
    })
    @DeleteMapping(value = "/{id}/answers/{answer_id}", produces = "application/json;charset=UTF-8")
    public void deleteQuestion(@PathVariable(value = "id") Long id,
                               @PathVariable(value = "answer_id") Long answerId){
        questionService.removeAnswer(id, answerId);
    }
}
