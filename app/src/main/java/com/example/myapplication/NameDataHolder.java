package com.example.myapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NameDataHolder {
        public static String dataArray;

        public NameDataHolder(String dataArray) {
            NameDataHolder.dataArray = dataArray;
        }
        public NameDataHolder(){
            Log.d("fa", "in empty constructor");
        }

        public void setDataArray(String dataArray) {
            NameDataHolder.dataArray = dataArray;
        }

        public void AddNameToArray(String nameData){
            Log.d("fa","in adder foo");
            NameDataHolder.dataArray = nameData;
        }

        public String getDataArray() {
            return NameDataHolder.dataArray;
        }

}
