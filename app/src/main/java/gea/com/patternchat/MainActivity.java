package gea.com.patternchat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import gea.com.patternchat.model.ChatData;

public class MainActivity extends AppCompatActivity {


    private String userName;

    private ListView listView;
    private EditText editText;
    private Button sendButton;
    private ArrayAdapter adapter = null;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        editText = (EditText)findViewById(R.id.editText);
        sendButton = (Button)findViewById(R.id.button);

        // set default userName by random value
        userName = "MVC USER";

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        listView.setAdapter(adapter);


        setChildEventListener();

        setSendButtonOnClickEvent();

    }

    @Override
    protected void onResume(){

        super.onResume();
    }

    @Override
    protected void onPause(){

        super.onPause();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    void setSendButtonOnClickEvent() {

        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String currentTime = getCurrentTime();

                ChatData chatData = new ChatData(userName, currentTime, editText.getText().toString());
                databaseReference.child("message").push().setValue(chatData);
                editText.setText("");
            }
        });
    }

    void setChildEventListener() {

        databaseReference.child("message").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String string) {

                ChatData chatData = getChatFromServer(dataSnapshot);
                reflectToListViewAdapter(chatData);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String string) {

                ChatData chatData = getChatFromServer(dataSnapshot);
                reflectToListViewAdapter(chatData);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                ChatData chatData = getChatFromServer(dataSnapshot);
                reflectToListViewAdapter(chatData);
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
     * Reflect changed value to listView
     *
     * @param chatData Chat data loaded from FireBase.
     */
    private void reflectToListViewAdapter(ChatData chatData) {

        adapter.add(chatData.getUserName() + "( " + chatData.getCreationTime() + " )" + ": " + chatData
                .getMessage());  //
    }

    /**
     * Get dataBundle from fireBase
     *
     * @param dataSnapshot
     * @return
     */
    ChatData getChatFromServer(DataSnapshot dataSnapshot) {

        ChatData chatData = dataSnapshot.getValue(ChatData.class);
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
