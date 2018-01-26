package udovyk.dribbleclimoxydaggerrx.mvp.view;


import android.view.MenuItem;

import com.arellomobile.mvp.MvpView;

import udovyk.dribbleclimoxydaggerrx.network.model.User;

public interface MainActivityView extends MvpView {

    void hideDrawer();

    void openDrawer();

    void setToolbarTitle();

    void setUserToNavigationview(User user);

    void selectDrawerItem(MenuItem menuItem);

    void setupDrawerContent();

    void setupDrawerToggleButton();


}
