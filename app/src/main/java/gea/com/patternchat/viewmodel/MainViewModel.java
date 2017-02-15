/**
 * @file MainViewModel.java
 * @author Lee, Dongho - 502646032
 * @date Feb/15/2017
 * @brief
 * @copyright (c) 2017. GE Appliances, a Haier Company (Confidential) All rights reserved.
 */
package gea.com.patternchat.viewmodel;

import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import gea.com.patternchat.model.ChatData;

public class MainViewModel implements ViewModel {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    public ObservableField<String> chatDataObservableField = new ObservableField<>();

    public MainViewModel() {

    }

    @Override
    public void onCreate() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

        databaseReference.child("message").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String string) {

                ChatData chatData = dataSnapshot.getValue(ChatData.class);
                Log.d("MainViewModel", chatData.getUserName() +" : " + chatData.getMessage() );

                chatDataObservableField.set(chatData.getMessage());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String string) {


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String string) {

                // When child is moved
                // Do nothing.
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // When pushing a data is failed.
                Log.d("CHAT", "Failed to post data to server");
            }
        });
    }

    @Override
    public void onDestroy() {

    }


    public void onSendButtonClicked(View view){

        Log.d("onSendButtonClicked","CLICKED");

    }
}
