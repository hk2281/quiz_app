package com.example.myapplication;

public class State {

    private String name; 
    private String  score;
    private int flagResource;

    private String pl_id;

    private long count_id;
    

    public State(String name, String score, int flagResource){

        this.name=name;
        this.score=score;
        this.flagResource=flagResource;

    }
    public State(){}

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getFlagResource() {
        return this.flagResource;
    }

    public void setFlagResource(int flagResource) {
        this.flagResource = flagResource;
    }

    public String getPl_id(){return this.pl_id;}
    public void setPl_id(String pl_id){this.pl_id = pl_id;}
    public long getCount_id(){return this.count_id;}
    public void setCount_id(long count_id){this.count_id = count_id;}
}
