/**
 * @file MainViewModel.java
 * @author Lee, Dongho - 502646032
 * @date Feb/15/2017
 * @brief
 * @copyright (c) 2017. GE Appliances, a Haier Company (Confidential) All rights reserved.
 */
package gea.com.patternchat.viewmodel;

import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import gea.com.patternchat.model.ChatData;

public class MainViewModel implements ViewModel {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    private ChatData chatData;
    public ObservableField<String> chatDataObservableField = new ObservableField<>();

    public MainViewModel() {

    }

    @Override
    public void onCreate() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        chatData = new ChatData();
        chatData.setUserName("USER");
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

                chatDataObservableField.set(chatData.getUserName() + "( " + chatData.getCreationTime() + " )" + ": " + chatData
                        .getMessage());

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

        Log.d("onSendButtonClicked : ","SENDING CHAT TO FIREBASE SERVER");
        databaseReference.child("message").push().setValue(chatData);

    }


    public void onTextChanged(CharSequence charSequence, int start, int before, int count){

        Log.d("onTextChanged", charSequence.toString());

        chatData.setCreationTime(getCurrentTime());
        chatData.setMessage(charSequence.toString());
    }

    /**
     * Get current Time base on device's TIMEZONE.
     *
     * @return typed simpleDataFormat (yyyy:MM:dd-hh:mm:ss)
     */
    String getCurrentTime() {

        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd-hh:mm:ss");

        String currentTime = simpleDateFormat.format(new Date(currentTimeMillis));
        return currentTime;
    }
}
