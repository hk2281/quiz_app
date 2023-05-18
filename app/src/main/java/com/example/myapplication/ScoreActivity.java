package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LinkedList<State> states = new LinkedList<>();
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        context = this.getApplicationContext();
        setInitialData();
        Log.d("fo","here");
    }
    private void setInitialData(){
        databaseConnection forGetPlayersScore = new databaseConnection();
        forGetPlayersScore.getFromDb(new databaseConnection.MyCallback() {
            @Override
            public void onComplete(long data) {

            }

            @Override
            public void onGetPlayerScore(ArrayList<State> playerScoreStateList) {
                for(State stat: playerScoreStateList){
                    states.add(stat);
                }
                states = reverseLinkedList(states);
                Log.d("fo", String.valueOf(states.size()));
                mRecyclerView = findViewById(R.id.recyclerView);
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                mAdapter = new StateAdapter_yt(context,states);

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);
            }
        });



    }


    public static LinkedList<State> reverseLinkedList(LinkedList<State> mylist)
    {
        LinkedList<State> revLinkedList = new LinkedList<State>();
        for (int i = mylist.size() - 1; i >= 0; i--) {

            // Append the elements in reverse order
            revLinkedList.add(mylist.get(i));
        }
        // Return the reversed arraylist
        return revLinkedList;
    }


}