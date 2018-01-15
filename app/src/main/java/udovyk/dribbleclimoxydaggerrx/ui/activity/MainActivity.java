package udovyk.dribbleclimoxydaggerrx.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import ru.terrakok.cicerone.Router;
import udovyk.dribbleclimoxydaggerrx.R;
import udovyk.dribbleclimoxydaggerrx.Screens;
import udovyk.dribbleclimoxydaggerrx.manager.PrefManager;
import udovyk.dribbleclimoxydaggerrx.mvp.presenter.MainActivityPresenter;
import udovyk.dribbleclimoxydaggerrx.mvp.view.MainActivityView;
import udovyk.dribbleclimoxydaggerrx.network.model.User;
import udovyk.dribbleclimoxydaggerrx.ui.widget.ToolbarActions;

/**
 * Created by udovik.s on 10.01.2018.
 */

public class MainActivity extends BaseActivity implements MainActivityView, ToolbarActions{
    private static final String TAG = "MainActivity";

    @InjectPresenter
    MainActivityPresenter presenter;
    @Inject
    PrefManager prefManager;
    @Inject
    Router router;

    /*//region view binding
    @BindView(R.id.userAvatar)
    ImageView userAvatar;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.userBio)
    TextView userBio;
    @BindView(R.id.userLocation)
    TextView userLocation;*/

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.nvView)
    NavigationView nvDrawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    ActionBarDrawerToggle drawerToggle;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.initScreen();

        //Todo setupDrawerContent(nvDrawer); in presenter initScreen
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.removeNavigator();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setNavigator(baseNavigator);
    }

    @Override
    public void setupDrawerToggleButton() {
        drawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    @Override
    public void showDrawer(boolean show) {
        if (show) {
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else {
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    @Override
    public void hideDrawer() {
        mDrawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void openDrawer() {
        mDrawer.openDrawer(GravityCompat.START);
    }

    @Override
    public void setToolbarTitle() {
        //unresolved
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void selectDrawerItem(MenuItem menuItem) {
        int id  = menuItem.getItemId();
        if (id == R.id.logout_item) {
            menuItem.setChecked(true);
            prefManager.clearSharedPref();
            router.replaceScreen(Screens.START_SCREEN_FRAGMENT_SCREEN);
        }
        menuItem.setChecked(true);
        hideDrawer();
    }

    @Override
    public void setupDrawerContent() {
        nvDrawer.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        selectDrawerItem(item);
                        return true;
                    }
                }
        );
    }

    /*@Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }*/

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void setUserToNavigationview(User user) {
        /*if (user != null) {
            Picasso.with(this)
                    .load(user.getAvatarUrl())
                    .into(userAvatar);
            userName.setText(user.getName());
            userBio.setText(user.getBio());
            userLocation.setText(user.getLocation());
        } else {
            Log.d(TAG, "setInfoDataToNavView: Can't set data, user=null");
        }*/
    }

    @Override
    public Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    public void setSupportToolBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void injectDependency() {
        getActivityComponent().inject(this);
    }
}
