/**
 * @file MainViewModel.java
 * @author Lee, Dongho - 502646032
 * @date Feb/15/2017
 * @brief
 * @copyright (c) 2017. GE Appliances, a Haier Company (Confidential) All rights reserved.
 */
package gea.com.patternchat.viewmodel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import gea.com.patternchat.model.ChatData;

public class MainViewModel implements ViewModel{


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public MainViewModel() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
    
    @Override
    public void onCreate() {

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
}
