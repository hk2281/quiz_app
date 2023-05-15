package com.example.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StateAdapter_yt extends RecyclerView.Adapter<StateAdapter_yt.StateViewHolder>{

    private ArrayList<State> mStateList;
    public Context  context;
    public static class StateViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mNameView;
        public TextView mScoreView;


    
        public StateViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mNameView = itemView.findViewById(R.id.textView);
            mScoreView = itemView.findViewById(R.id.textView2);
        }
    }

    public StateAdapter_yt(Context context, ArrayList<State> mStateList) {
        this.mStateList = mStateList;
        this.context = context;
    }

    @Override
    public StateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        StateViewHolder svh = new StateViewHolder(v);
        return svh;
    }
    @Override
    public void onBindViewHolder(StateViewHolder holder, int position) {
        State currentItem = mStateList.get(position);

        holder.mImageView.setImageResource(currentItem.getFlagResource());
        holder.mNameView.setText(currentItem.getName());
        holder.mScoreView.setText(String.valueOf(currentItem.getScore()));
    }
    @Override
    public int getItemCount() {
        return mStateList.size();
    }
}
