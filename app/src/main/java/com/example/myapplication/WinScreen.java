package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class WinScreen extends DialogFragment {

    public WinScreen() {
        // Required empty public constructor
    }
    private SharedPreferences sharedPrefs;
    SharedPreferences.Editor ed;

    public static WinScreen newInstance(String title) {
        WinScreen frag = new WinScreen();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage("Are you sure?");
        alertDialogBuilder.setPositiveButton("OK",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                returnToMainActivity();
            }
        });
        return alertDialogBuilder.create();
    }

    public void returnToMainActivity(){
        sharedPrefs = getContext().getSharedPreferences("storage", Context.MODE_PRIVATE);

//        NameDataHolder getName = new NameDataHolder();
        Log.d("fa", sharedPrefs.getString("name",""));
        Log.d("fa",String.valueOf(sharedPrefs.getInt("score", 0)));
        sendToDbCurrentPlayerSession(
                sharedPrefs.getString("name",""),
                String.valueOf(sharedPrefs.getInt("score", 0)));

//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
        Intent intent = new Intent(WinScreen.this.getActivity(), MainActivity.class);
        startActivity(intent);
    }
    public void sendToDbCurrentPlayerSession(String playerName, String score){
        databaseConnection forGetCount = new databaseConnection();
        databaseConnection forPostData = new databaseConnection();
        forGetCount.getcollectionCount(new databaseConnection.MyCallback(){
            @Override
            public  void  onComplete(long count){
                Log.d("ff", String.valueOf(count));
                forPostData.postToDb(new Player(playerName,score,count));
            }
            @Override
            public void onGetPlayerScore(ArrayList<State> data){}
        });

    }

}