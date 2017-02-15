/**
 * @file MainPresenter.java
 * @author Lee, Dongho - 502646032
 * @date Feb/14/2017
 * @brief
 * @copyright (c) 2017. GE Appliances, a Haier Company (Confidential) All rights reserved.
 */
package gea.com.patternchat.presenter;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import gea.com.patternchat.model.ChatData;
import gea.com.patternchat.view.MainView;

public class MainPresenter implements Presenter {

    private MainView mainView;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    public MainPresenter(MainView mainView) {

        this.mainView = mainView;
    }

    @Override
    public void onCreate() {

        addDataBaseListener();

        mainView.setListAdapter();

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public void onSendButtonClicked(String editTextString) {

        final String userName = "USER" + new Random().nextInt(10000);

        if("remove".equalsIgnoreCase(editTextString)) {
            databaseReference.removeValue();
        }
        else {
            String currentTime = getCurrentTime();

            ChatData chatData = new ChatData(userName, currentTime, editTextString);
            databaseReference.child("message").push().setValue(chatData);
//            mainView.editText.setText("");
            mainView.initializeInputConsole();
        }
    }

    public void addDataBaseListener() {

        databaseReference.child("message").addChildEventListener(new ChildEventListener() {  // message는 child의 이벤트를 수신합니다.

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String string) {

                ChatData chatData = getChatFromServer(dataSnapshot);
                mainView.updateListView(chatData);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String string) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                ChatData chatData = getChatFromServer(dataSnapshot);
                mainView.updateListView(chatData);
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

    /**
     * Get dataBundle from fireBase
     * @param dataSnapshot
     * @return
     */
    ChatData getChatFromServer(DataSnapshot dataSnapshot) {

        ChatData chatData = dataSnapshot.getValue(ChatData.class);  // chatData를 가져오고
        return chatData;
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
