package com.example.myapplication;

public class State {

    private String name; 
    private int score;
    private int flagResource;
    

    public State(String name, int score, int flagResource){

        this.name=name;
        this.score=score;
        this.flagResource=flagResource;

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFlagResource() {
        return this.flagResource;
    }

    public void setFlagResource(int flagResource) {
        this.flagResource = flagResource;
    }
}
