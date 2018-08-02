package com.genz.server.controller;

import com.genz.server.model.PlayRequest;
import com.genz.server.model.User;
import com.genz.server.service.game.GameService;
import com.genz.server.service.game.GameServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Nikos.Toulios
 */
@RestController
@RequestMapping("/api/game")
@Api(value = "game actions", description = "This API is to execute commands for the game")
public class GameController {

    @Autowired
    private GameServiceImpl gameService;

    @ApiOperation(value = "View the user's information after he played.", response = User.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the user info"),
            @ApiResponse(code = 400, message = "Valdation with request."),
            @ApiResponse(code = 404, message = "The appropriate resource hasnt found.")
    })
    @PostMapping(value = "/play", produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public User play(@RequestBody PlayRequest playrequest){
        return gameService.play(
                playrequest.getWeekNumber(),
                playrequest.getGroupId(),
                playrequest.getUserId(),
                playrequest.getQuestionIds(),
                playrequest.getAnswerIds());
    }

    @ApiOperation(value = "Calculate potential winnings")
    @GetMapping(value = "/users/potentialWinnings", consumes = "application/json;charset=UTF-8")
    public double calculatePotentialWinnings(@RequestParam(value = "odds") List<Double> odds,@RequestParam(value = "money") Double money){
        return gameService.calculatePotentialWinnings(odds, money);
    }

    @ApiOperation(value = "Calculate user's points and correct answers.", response = User.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the user info"),
            @ApiResponse(code = 400, message = "Valdation with request."),
            @ApiResponse(code = 404, message = "The appropriate resource hasnt found.")
    })
    @PostMapping(value = "/users/calculatePointsAndCorrectAnswers", produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public List<User> calculateUserPointsPerCorrectAnswer(List<Long> userIds, List<Long> questionIds, Integer weekNum){
        return gameService.calculateUserPointsPerCorrectAnswer(userIds, questionIds, weekNum);
    }
}
