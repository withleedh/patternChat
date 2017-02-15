package gea.com.patternchat.view;

import gea.com.patternchat.model.ChatData;

/**
 * Created by leedongho on 15/02/2017.
 */

public interface MainView {

    void updateListView(ChatData chatData);

    void initializeInputConsole();

    void setListAdapter();
}
