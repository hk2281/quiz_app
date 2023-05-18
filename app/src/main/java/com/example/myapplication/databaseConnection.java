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
import java.util.List;
import java.util.Map;

public class databaseConnection {
    public FirebaseFirestore database;
    public DatabaseReference dbRef;
    public long collectionSize;

    public interface MyCallback{

        void onComplete(long data);
        void onGetPlayerScore(ArrayList<State> playerScoreStateList);
    }

    public interface QuestionCallback{
        void onGetQuestion(List<Quiz>  docSnap);
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
    public long  getcollectionCount(MyCallback callback){
        try {
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
                        callback.onComplete(collectionSize);
                    } else {
                        Log.d("ff", "Count failed: ", task.getException());
                    }
                }
            });
            return collectionSize;

        }
        catch (Exception e){
            Log.d("fa", e.getMessage());
            return 0;
        }
    }

    public void getFromDb(MyCallback callback) {
        try {
            CollectionReference colRef;
            database = FirebaseFirestore.getInstance();
            colRef = database.collection("Player");
            Query query = colRef.limitToLast(10).orderBy("count_id");
            query
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful()) {
                                ArrayList<State> LastPlayerScoreStateList = new ArrayList<State>();
                                for (QueryDocumentSnapshot docum : task.getResult()) {
                                    LastPlayerScoreStateList
                                            .add(new State(
                                                    docum.toObject(Player.class).getPlName(),
                                                    docum.toObject(Player.class).getScore(),
                                                    R.drawable.flag));
                                }
                                callback.onGetPlayerScore(LastPlayerScoreStateList);
                            } else {
                                Log.d("fa", "Error getting documents: ", task.getException());
                            }
                        }
                    });


        } catch (Exception e) {
            Log.e("fa", e.getMessage());
        }


    }

    public void getQuestion(QuestionCallback callback){
        try{
            database = FirebaseFirestore.getInstance();
            database.collection("questions")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                ArrayList<Map<String,Object>> questionsMapList = new ArrayList<Map<String,Object>>();
                                ArrayList<Quiz> allQuestion = new ArrayList<Quiz>();
                                for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                                    List<String> option = new ArrayList<>();

                                    option.add(documentSnapshot.getData().get("1").toString());
                                    option.add(documentSnapshot.getData().get("2").toString());
                                    option.add(documentSnapshot.getData().get("3").toString());
                                    option.add(documentSnapshot.getData().get("4").toString());
                                    Long indexCorrectAnswer = (Long) documentSnapshot.getData().get("correct_answ");
                                    String question = documentSnapshot.getData().get("question").toString();
                                    Log.d("fa",String.valueOf(documentSnapshot.getData().get("1")));
                                    Quiz oneQuiz = new Quiz(question,option,indexCorrectAnswer.intValue());
                                    allQuestion.add(oneQuiz);
                                }
                                callback.onGetQuestion(allQuestion);
                            }else {
                                Log.e("fa","error: ", task.getException());
                            }
                        }
                    });

        }
        catch (Exception e){
            Log.e("fa", e.getMessage());
        }
    }
}