package com.example.myapplication;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.AggregateQuery;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.AggregateSource;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class databaseConnection {
    public FirebaseFirestore database;
    public DatabaseReference dbRef;
    public long collectionSize;

    public interface Mycolback{

        void onComplet();
    }

    public void postToDb(Player currentPlayer) {
        try{

            database = FirebaseFirestore.getInstance();
            database.collection("Player")
                    .add(currentPlayer)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("fa", "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    });

        }
        catch (Exception e){
            Log.e("fa", e.getMessage());
        }

    }
    public long  getcollectionCount(Mycolback callback){

        CollectionReference colRef;
        database = FirebaseFirestore.getInstance();
        Query query = database.collection("Player");
        AggregateQuery countQuery = query.count();
        Log.d("fa", "yes");
        countQuery.get(AggregateSource.SERVER).addOnCompleteListener(new OnCompleteListener<AggregateQuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<AggregateQuerySnapshot> task) {
                if (task.isSuccessful()) {
                    // Count fetched successfully
                    Log.d("fa", "yes");
                    AggregateQuerySnapshot snapshot = task.getResult();

                    Log.d("ff", "Count: " + snapshot.getCount());
                    collectionSize = snapshot.getCount();
                    callback.onComplet();
                } else {
                    Log.d("ff", "Count failed: ", task.getException());
                }
            }
        });
        return collectionSize;

    }

    public void getFromDb() {
        try {
            CollectionReference colRef;
            database = FirebaseFirestore.getInstance();
            colRef = database.collection("Player");
            Log.d("fa", "inpost");
            Query query = colRef.limitToLast(2).orderBy("score");
            query
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {

                                for (QueryDocumentSnapshot docum : task.getResult()) {
//                                    Map<String, Object> map = (Map<String, Object>) docum.get("map_value");
                                    Log.d("fa", docum.toObject(Player.class).getPlName());
                                }
                            } else {
                                Log.d("fa", "Error getting documents: ", task.getException());
                            }
                        }
                    });


        } catch (Exception e) {
            Log.d("fa", e.getMessage());
        }


    }
}