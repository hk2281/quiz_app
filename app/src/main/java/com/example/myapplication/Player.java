package com.example.myapplication;

import java.util.UUID;

public class Player {
    private String name;
    private String  score;
    private String pl_id;

    private long count_id;

    Player(String name, String score, long count_id){
        this.name=name;
        this.score=score;
        UUID uuid = UUID.randomUUID();
        this.pl_id = String.valueOf(uuid);
        this.count_id=count_id;
    }

    Player(){

    }

    public void setPlName(String name){this.name=name;}
    public String getPlName(){return this.name;}
    public void setScore(String score){this.score=score;}
    public String getScore(){return this.score;}
    public String getPl_id(){return this.pl_id;}
    public void setPl_id(String pl_id){this.pl_id = pl_id;}
    public long getCount_id(){return this.count_id;}
    public void setCount_id(long count_id){this.count_id = count_id;}
}
