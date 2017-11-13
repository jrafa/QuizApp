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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createMessages();
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
        CheckBox answerCorrectFirst = (CheckBox) findViewById(R.id.question_first_answer_correct_first_check_box);
        CheckBox answerCorrectSecond = (CheckBox) findViewById(R.id.question_first_answer_correct_second_check_box);
        CheckBox answerWrong = (CheckBox) findViewById(R.id.question_first_answer_wrong_check_box);

        boolean answer = answerCorrectFirst.isChecked() && answerCorrectSecond.isChecked() && !answerWrong.isChecked();

        addPoint(answer);
    }

    /**
     * This method check answer on the question no 2.
     */
    private void checkSecondQuestionAnswer() {
        RadioButton answerCorrect = (RadioButton) findViewById(R.id.question_second_answer_correct_radio_button);

        addPoint(answerCorrect.isChecked());
    }

    /**
     * This method check answer on the question no 3.
     */
    private void checkThirdQuestionAnswer() {
        EditText answerCorrect = (EditText) findViewById(R.id.question_third_answer_correct_edit_text);
        boolean answer = answerCorrect.getText().toString().trim().equals(getString(R.string.question_third_answer_correct));

        addPoint(answer);
    }

    /**
     * This method check answer on the question no 4.
     */
    private void checkFourthQuestionAnswer() {
        EditText answerCorrect = (EditText) findViewById(R.id.question_fourth_answer_correct_edit_text);
        boolean answer = answerCorrect.getText().toString().trim().equals(getString(R.string.question_fourth_answer_correct));

        addPoint(answer);
    }

    /**
     * This method check answer on the question no 5.
     */
    private void checkFifthQuestionAnswer() {
        RadioButton answerCorrect = (RadioButton) findViewById(R.id.question_fifth_answer_correct_radio_button);
        boolean answer = answerCorrect.isChecked();

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

        answersQuestionFirst.add((CheckBox) findViewById(R.id.question_first_answer_correct_first_check_box));
        answersQuestionFirst.add((CheckBox) findViewById(R.id.question_first_answer_correct_second_check_box));
        answersQuestionFirst.add((CheckBox) findViewById(R.id.question_first_answer_wrong_check_box));

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

        answersQuestionsEditText.add((EditText) findViewById(R.id.question_third_answer_correct_edit_text));
        answersQuestionsEditText.add((EditText) findViewById(R.id.question_fourth_answer_correct_edit_text));

        for (EditText element : answersQuestionsEditText) {
            element.setText("");
        }
    }

    /**
     * This method reset RadioGroup Views.
     */
    private void resetRadioGroup() {
        List<RadioGroup> answersQuestionRadioGroup = new ArrayList<RadioGroup>();

        answersQuestionRadioGroup.add((RadioGroup) findViewById(R.id.question_second_radio_group));
        answersQuestionRadioGroup.add((RadioGroup) findViewById(R.id.question_fifth_radio_group));

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
