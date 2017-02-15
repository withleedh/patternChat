package gea.com.patternchat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import gea.com.patternchat.model.ChatData;
import gea.com.patternchat.presenter.MainPresenter;
import gea.com.patternchat.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final String TAG = MainActivity.class.getName();

    private ListView listView;
    private EditText editText;
    private Button sendButton;
    private ArrayAdapter adapter = null;

    MainPresenter presenter = new MainPresenter(this);

    @Override
    public void initializeInputConsole() {

    }

    @Override
    public void setListAdapter() {

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        editText = (EditText)findViewById(R.id.editText);

        presenter.onCreate();

    }

    public void onSendButtonClicked(View view) {

        String editTextString = editText.getText().toString();
        presenter.onSendButtonClicked(editTextString);
    }

    @Override
    protected void onPause() {

        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onResume() {

        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void updateListView(ChatData chatData) {

        adapter.add(chatData.getUserName() + "( " + chatData.getCreationTime() + " )" + ": " + chatData
                .getMessage());  //

    }

    @Override
    public void clearListView() {

        adapter.clear();
    }
}
