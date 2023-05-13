package com.example.myapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseQuizFactory implements QuizFactory{

    public DatabaseQuizFactory(){};
//    public List<Quiz> allQuestions = new ArrayList<>();
//    public List<String> options1 = new ArrayList<>();
    public Quiz myQuiz;
    @Override
    public List<Quiz> getQuiz() {

        List<String> options1 = new ArrayList<>();
        List<String> options2 = new ArrayList<>();
        List<String> options3 = new ArrayList<>();
        List<Quiz> allQuestions = new ArrayList<>();

        options1.add("A) Option 1");
        options1.add("B) Option 2");
        options1.add("C) Option 3");
        options1.add("D) Option 4");

        options2.add("minsk");
        options2.add("brest");
        options2.add("grodno");
        options2.add("gomel");

        options3.add("apple");
        options3.add("sumsung");
        options3.add("sony");
        options3.add("microsoft");

        myQuiz = new Quiz("test question",options1,1);

        allQuestions.add(myQuiz);
        allQuestions.add(new Quiz("my favorite sity",options2,2));
        allQuestions.add(new Quiz("best electronic manafacture", options3,3));
        return allQuestions;
    }
}
