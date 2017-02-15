/**
 * @file MainViewModel.java
 * @author Lee, Dongho - 502646032
 * @date Feb/15/2017
 * @brief
 * @copyright (c) 2017. GE Appliances, a Haier Company (Confidential) All rights reserved.
 */
package gea.com.patternchat.viewmodel;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainViewModel implements ViewModel {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

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
}
