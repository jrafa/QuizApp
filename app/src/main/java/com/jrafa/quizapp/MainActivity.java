package com.jrafa.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // Points earned by quiz solver
    private int score = 0;
    // All the points in the quiz
    private int scoreTotal = 5;
    // Map where messages are stored
    private Map<Integer, String> messages = new HashMap<Integer, String>();

    // Answers to Question 1
    private CheckBox answerCorrectFirstQuestionFirst;
    private CheckBox answerCorrectSecondQuestionFirst;
    private CheckBox answerWrongQuestionFirst;

    // Answer to Question 2
    private RadioButton answerCorrectQuestionSecond;

    // Answer to Question 3
    private EditText answerCorrectQuestionThird;

    // Answer to Question 4
    private EditText answerCorrectQuestionFourth;

    // Answer to Question 5
    private RadioButton answerCorrectQuestionFifth;

    // Radio Group Answers question 2 and 5
    private RadioGroup answersRadioGroupQuestionSecond;
    private RadioGroup answersRadioGroupQuestionFifth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createMessages();

        answerCorrectFirstQuestionFirst = (CheckBox) findViewById(R.id.question_first_answer_correct_first_check_box);
        answerCorrectSecondQuestionFirst = (CheckBox) findViewById(R.id.question_first_answer_correct_second_check_box);
        answerWrongQuestionFirst = (CheckBox) findViewById(R.id.question_first_answer_wrong_check_box);
        answerCorrectQuestionSecond = (RadioButton) findViewById(R.id.question_second_answer_correct_radio_button);
        answerCorrectQuestionThird = (EditText) findViewById(R.id.question_third_answer_correct_edit_text);
        answerCorrectQuestionFourth = (EditText) findViewById(R.id.question_fourth_answer_correct_edit_text);
        answerCorrectQuestionFifth = (RadioButton) findViewById(R.id.question_fifth_answer_correct_radio_button);
        answersRadioGroupQuestionSecond = (RadioGroup) findViewById(R.id.question_second_radio_group);
        answersRadioGroupQuestionFifth = (RadioGroup) findViewById(R.id.question_fifth_radio_group);
    }

    /**
     * This method creates messages.
     */
    private void createMessages() {
        messages.put(0, "Upps :(");
        messages.put(1, "You can do better!");
        messages.put(2, "Python is sad :(");
        messages.put(3, "Try again!");
        messages.put(4, "Well done!");
        messages.put(5, "Great job!");
    }

    /**
     * This method check answer on the question no 1.
     */
    private void checkFirstQuestionAnswer() {
        boolean answer = answerCorrectFirstQuestionFirst.isChecked()
                && answerCorrectSecondQuestionFirst.isChecked()
                && !answerWrongQuestionFirst.isChecked();

        addPoint(answer);
    }

    /**
     * This method check answer on the question no 2.
     */
    private void checkSecondQuestionAnswer() {
        addPoint(answerCorrectQuestionSecond.isChecked());
    }

    /**
     * This method check answer on the question no 3.
     */
    private void checkThirdQuestionAnswer() {
        boolean answer = answerCorrectQuestionThird.getText().toString().trim().equals(getString(R.string.question_third_answer_correct));

        addPoint(answer);
    }

    /**
     * This method check answer on the question no 4.
     */
    private void checkFourthQuestionAnswer() {
        boolean answer = answerCorrectQuestionFourth.getText().toString().trim().equals(getString(R.string.question_fourth_answer_correct));

        addPoint(answer);
    }

    /**
     * This method check answer on the question no 5.
     */
    private void checkFifthQuestionAnswer() {
        boolean answer = answerCorrectQuestionFifth.isChecked();

        addPoint(answer);
    }

    /**
     * This method add point if statement is true.
     *
     * @param statement boolean
     */
    private void addPoint(boolean statement) {
        if (statement) {
            score += 1;
        }

        return;
    }

    /**
     * This method check all answers in the quiz.
     */
    private void checkAllQuizAnswers() {
        checkFirstQuestionAnswer();
        checkSecondQuestionAnswer();
        checkThirdQuestionAnswer();
        checkFourthQuestionAnswer();
        checkFifthQuestionAnswer();
    }

    /**
     * This method return message.
     *
     * @return message
     */
    private String getMessage() {
        for (Map.Entry<Integer, String> entry : messages.entrySet()) {
            Integer key = entry.getKey();
            Object value = entry.getValue();

            if (key == score) {
                return (String) value;
            }

        }
        return "";
    }

    /**
     * This method displays message with result of quiz on the screen.
     */
    private void displayMessage() {
        String message = "Result: " + score + "/" + scoreTotal + " points." + getMessage();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * This method reset CheckBox Views.
     */
    private void resetCheckBox() {
        List<CheckBox> answersQuestionFirst = new ArrayList<CheckBox>();

        answersQuestionFirst.add(answerCorrectFirstQuestionFirst);
        answersQuestionFirst.add(answerCorrectSecondQuestionFirst);
        answersQuestionFirst.add(answerWrongQuestionFirst);

        for (CheckBox element : answersQuestionFirst) {
            if (element.isChecked()) {
                element.setChecked(false);
            }
        }
    }

    /**
     * This method reset EditText Views.
     */
    private void resetEditText() {
        List<EditText> answersQuestionsEditText = new ArrayList<EditText>();

        answersQuestionsEditText.add(answerCorrectQuestionThird);
        answersQuestionsEditText.add(answerCorrectQuestionFourth);

        for (EditText element : answersQuestionsEditText) {
            element.setText("");
        }
    }

    /**
     * This method reset RadioGroup Views.
     */
    private void resetRadioGroup() {
        List<RadioGroup> answersQuestionRadioGroup = new ArrayList<RadioGroup>();

        answersQuestionRadioGroup.add(answersRadioGroupQuestionSecond);
        answersQuestionRadioGroup.add(answersRadioGroupQuestionFifth);

        for (RadioGroup element : answersQuestionRadioGroup) {
            element.clearCheck();
        }
    }

    /**
     * This method is called when the check button is clicked.
     */
    public void displayResult(View view) {
        score = 0;

        checkAllQuizAnswers();
        displayMessage();
    }

    /**
     * This method is called when the reset button is clicked.
     */
    public void reset(View view) {
        score = 0;

        resetCheckBox();
        resetEditText();
        resetRadioGroup();
    }
}
