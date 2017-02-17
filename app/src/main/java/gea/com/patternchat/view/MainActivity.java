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

    MainViewModel mainViewModel = new MainViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Bind view using DataBinding library that Android supports.
        // Binding xml's name is activity_main. BindingClass name is made with following xml's name.
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Bind layout with ViewModel(business logic)
        activityMainBinding.setViewModel(mainViewModel);

        mainViewModel.onCreate();
    }

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

}
