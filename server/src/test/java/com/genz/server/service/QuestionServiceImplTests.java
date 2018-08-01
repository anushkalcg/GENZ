package com.genz.server.service;

import com.genz.server.ServerApplicationTests;
import com.genz.server.exception.ResourceNotFoundException;
import com.genz.server.exception.ResourceValidationException;
import com.genz.server.model.Answer;
import com.genz.server.model.Question;
import com.genz.server.repository.QuestionRepository;
import com.genz.server.service.question.QuestionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.Assert.*;

public class QuestionServiceImplTests extends ServerApplicationTests {

    @Autowired
    QuestionServiceImpl questionService;

    @Autowired
    QuestionRepository questionRepository;

    @Before
    @Override
    public void setUp(){
        questionRepository.deleteAll();
    }

    @Test
    public void addQuestion_without_answer_success(){
        //given

        Question question = createDefaultQuestion();

        //when

        Question resultQuestion = questionService.add(question);

        //then
        assertNotNull(resultQuestion);
        assertNotNull(resultQuestion.getId());
    }

    @Test
    public void addQuestion_with_answer_success(){
        //given

        Answer answer = createDefaultAnswer();
        List<Answer> answers =  new ArrayList<Answer>();
        answers.add(answer);

        Question question = createDefaultQuestion();
        question.setAnswers(answers);

        //when

        Question resultQuestion = questionService.add(question);

        //then
        assertNotNull(resultQuestion);
        assertNotNull(resultQuestion.getId());
        assertEquals(1, resultQuestion.getAnswers().size());
    }

    @Test
    public void updateQuestion_without_answer_success(){
        //given

        Question question = createDefaultQuestion();

        question = questionService.add(question);
        question.setText("question2");

        //when

        Question resultQuestion = questionService.update(question);

        //then
        assertNotNull(resultQuestion);
        assertNotNull(resultQuestion.getId());
        assertEquals("question2", resultQuestion.getText());
    }

    @Test
    public void updateQuestion_with_answer_success(){
        //given

        Answer answer = createDefaultAnswer();
        List<Answer> answers =  new ArrayList<>();
        answers.add(answer);

        Question question = createDefaultQuestion();
        question.setAnswers(answers);

        question = questionService.add(question);

        question.getAnswers().get(0).setText("new Answer");
        //when

        Question resultQuestion = questionService.update(question);

        //then
        assertNotNull(resultQuestion);
        assertNotNull(resultQuestion.getId());
        assertEquals(1, resultQuestion.getAnswers().size());
        assertEquals(1, resultQuestion.getAnswers().stream().filter(answerVal -> answerVal.getText().equals("new Answer")).count());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void addNewAnswer_Question_not_found(){
        //given



        //when
        questionService.addNewAnswer(10L, null);


        //then
    }

    @Test(expected = ResourceValidationException.class)
    public void addNewAnswer_answer_should_not_be_null(){
        //given

        Question question = createDefaultQuestion();

        Question resultQuestion = questionService.add(question);

        //when
        questionService.addNewAnswer(resultQuestion.getId(), null);


        //then
    }

    @Test
    public void addNewAnswer_success(){
        //given

        Question question = createDefaultQuestion();

        Question resultQuestion = questionService.add(question);

        Answer answer = createDefaultAnswer();

        //when
        resultQuestion = questionService.addNewAnswer(resultQuestion.getId(), answer);


        //then
        assertNotNull(resultQuestion);
        assertNotNull(resultQuestion.getId());
        assertEquals(1, resultQuestion.getAnswers().size());
        assertEquals(1, resultQuestion.getAnswers().stream().filter(answerVal -> answerVal.getText().equals("answer")).count());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getAnswers_without_know_questionId(){
        //given



        //when
        questionService.getAnswers(11L);


        //then
    }

    @Test
    public void getAnswers_success(){
        //given

        Question question = createDefaultQuestion();
        Answer answer = createDefaultAnswer();
        question.addAnswer(answer);

        question = questionService.add(question);

        //when
        List<Answer> answers = questionService.getAnswers(question.getId());


        //then
        assertNotNull(answers);
        assertFalse(answers.isEmpty());
        assertEquals(1, answers.size());
        assertEquals(1, answers.stream().filter(answerVal -> answerVal.getText().equals("answer")).count());
    }

    @Test
    public void removeAnswer_success(){
        //given

        Question question = createDefaultQuestion();
        Answer answer = createDefaultAnswer();
        question.addAnswer(answer);

        question = questionService.add(question);
        List<Answer> answers = questionService.getAnswers(question.getId());

        //when

        Question resultQuestion = questionService.removeAnswer(question.getId(), answers.get(0).getId());

        //then
        assertNotNull(resultQuestion);
        assertTrue(resultQuestion.getAnswers().isEmpty());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void removeAnswer_not_found_questionId(){
        //given

        //when

        questionService.removeAnswer(11L, 1l);

        //then

    }

    @Test(expected = ResourceNotFoundException.class)
    public void removeAnswer_not_found_answerId(){
        //given
        Question question = createDefaultQuestion();
        Answer answer = createDefaultAnswer();
        question.addAnswer(answer);

        question = questionService.add(question);

        //when

        questionService.removeAnswer(question.getId(), 100L);

        //then

    }

    private Question createDefaultQuestion() {
        Question question = new Question();
        question.setPriority(1);
        question.setText("question");
        return question;
    }

    private Answer createDefaultAnswer() {
        Answer answer = new Answer();
        answer.setOdds(0.01);
        answer.setPriority(1);
        answer.setPoints(100);
        answer.setText("answer");
        return answer;
    }
}
