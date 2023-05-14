package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class WinScreen extends DialogFragment {

    public WinScreen() {
        // Required empty public constructor
    }


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
        Intent intent = new Intent(WinScreen.this.getActivity(), MainActivity.class);
        startActivity(intent);
    }

}