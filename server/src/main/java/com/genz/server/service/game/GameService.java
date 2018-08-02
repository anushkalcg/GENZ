package com.genz.server.service.game;

import com.genz.server.model.User;

import java.util.List;

/**
 * @author Nikos.Toulios
 */
public interface GameService {
    User play(Integer weekNum, Long groupId, Long userId, List<Long> questionIds, List<Long> answerIds);
    double calculatePotentialWinnings(List<Double> odds, Double money);
    List<User> calculateUserPointsPerCorrectAnswer(List<Long> userIds, List<Long> questionIds, Integer weekNum);
}
