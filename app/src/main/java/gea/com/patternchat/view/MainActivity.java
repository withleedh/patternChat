package gea.com.patternchat.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;

import gea.com.patternchat.R;
import gea.com.patternchat.databinding.ActivityMainBinding;
import gea.com.patternchat.model.ChatData;
import gea.com.patternchat.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {


//    ArrayAdapter adapter = null;

    MainViewModel mainViewModel = new MainViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        activityMainBinding.setViewModel(mainViewModel);

        mainViewModel.onCreate();
    }


//        final String userName = "USER" + new Random().nextInt(10000);  // 랜덤한 유저 이름 설정 ex) user1234


//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
//        listView.setAdapter(adapter);

//        sendButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                String currentTime = getCurrentTime();
//
//                    ChatData chatData = new ChatData(userName, currentTime, editText.getText().toString());
//                    databaseReference.child("message").push().setValue(chatData);
//                    editText.setText("");
//            }
//        });

    @Override
    protected void onPause() {

        super.onPause();
        mainViewModel.onPause();
    }

    @Override
    protected void onResume() {

        super.onResume();
        mainViewModel.onResume();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        mainViewModel.onDestroy();
    }

    /**
     * Reflect changed value to listView
     *
     * @param chatData Chat data loaded from FireBase.
     */
    private void reflectToListViewAdapter(ChatData chatData) {

//        adapter.add(chatData.getUserName() + "( " + chatData.getCreationTime() + " )" + ": " + chatData
//                .getMessage());  //
    }

    /**
     * Get dataBundle from fireBase
     *
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
