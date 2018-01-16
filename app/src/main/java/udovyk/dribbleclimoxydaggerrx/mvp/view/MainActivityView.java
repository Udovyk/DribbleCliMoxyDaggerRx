package udovyk.dribbleclimoxydaggerrx.mvp.view;


import android.view.MenuItem;

import udovyk.dribbleclimoxydaggerrx.network.model.User;

public interface MainActivityView extends BaseMvpView {

    void hideDrawer();

    void openDrawer();

    void setToolbarTitle();

    void setUserToNavigationview(User user);

    void selectDrawerItem(MenuItem menuItem);

    void setupDrawerContent();

    void setupDrawerToggleButton();


}
