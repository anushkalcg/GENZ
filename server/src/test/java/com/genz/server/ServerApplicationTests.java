package com.genz.server;

import com.genz.server.model.Answer;
import com.genz.server.model.Question;
import com.genz.server.model.User;
import com.genz.server.model.UserStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

/**
 * @author Nikos.Toulios
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerApplicationTests {

	@Test
	public void test(){

	}

	public void setUp(){

	}

	protected User createUser(){
		return new User(
				0,
				"email",
				"password",
				"name",
				"surname",
				19,
				"0044787777",
				"username",
				UserStatus.NOT_STARTED,
				new ArrayList<>(),
				null

		);
	}

	protected Question createDefaultQuestion() {
		Question question = new Question();
		question.setPriority(1);
		question.setText("question");
		return question;
	}

	protected Question createCustomQuestion(Integer priority, String text, Integer points) {
		Question question = new Question();
		question.setPriority(priority);
		question.setText(text);
		question.setPoints(points);
		return question;
	}

	protected Answer createDefaultAnswer() {
		Answer answer = new Answer();
		answer.setOdds(0.01);
		answer.setPriority(1);
		answer.setText("answer");
		return answer;
	}

	protected Answer createCustomAnswer(double odds, Integer priority, String text) {
		Answer answer = new Answer();
		answer.setOdds(odds);
		answer.setPriority(priority);
		answer.setText(text);
		return answer;
	}
}
