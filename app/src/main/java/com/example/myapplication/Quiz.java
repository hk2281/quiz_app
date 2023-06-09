package com.example.myapplication;

import android.util.Log;

import java.util.List;

public class Quiz {
    //this class is used to store the question, options and correct answer index
    private String question;
    private List<String> options;
    private int correctAnswerIndex;

    public Quiz(String question, List<String> options, int correctAnswerIndex) {

        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex-1;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public String getCorrectAnswer() {
        return options.get(correctAnswerIndex);
    }
}
