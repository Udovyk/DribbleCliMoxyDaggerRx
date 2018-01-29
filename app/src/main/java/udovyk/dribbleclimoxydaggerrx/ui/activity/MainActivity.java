package udovyk.dribbleclimoxydaggerrx.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import udovyk.dribbleclimoxydaggerrx.R;
import udovyk.dribbleclimoxydaggerrx.mvp.presenter.MainActivityPresenter;
import udovyk.dribbleclimoxydaggerrx.mvp.view.MainActivityView;
import udovyk.dribbleclimoxydaggerrx.network.model.User;
import udovyk.dribbleclimoxydaggerrx.ui.fragment.ProgressActivityListener;
import udovyk.dribbleclimoxydaggerrx.ui.widget.ToolbarActions;

/**
 * Created by udovik.s on 10.01.2018.
 */

public class MainActivity extends BaseActivity implements MainActivityView, ToolbarActions, ProgressActivityListener {
    private static final String TAG = "MainActivity";

    @InjectPresenter
    MainActivityPresenter presenter;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.nvView)
    NavigationView nvDrawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.userAvatar)
    ImageView userAvatar;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.userBio)
    TextView userBio;
    @BindView(R.id.userLocation)
    TextView userLocation;
    @BindView(R.id.pb_attachments)
    ProgressBar progressBar;

    ActionBarDrawerToggle drawerToggle;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.initScreen();
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

    public void lockDrawer() {
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public void unlockDrawer() {
        //Todo fix drawer crash back :
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        presenter.setUserToNH();
    }

    @Override
    public void initViews() {
        super.initViews();
        setupDrawerToggleButton();
        setupDrawerContent();
        mDrawer.addDrawerListener(drawerToggle);
    }

    @Override
    public void setupDrawerToggleButton() {
        drawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    public void onBackPressed() {
        hideDrawer();
        presenter.onBackPressed();

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
        int id = menuItem.getItemId();
        if (id == R.id.logout_item) {
            menuItem.setChecked(true);
            presenter.clearSharedPref();
            presenter.initScreen();
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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void showDrawerToggleButton() {
        showDrawerToggleButton(toolbar);
    }

    public void showDrawerToggleButton(Toolbar toolbar) {
        if (toolbar != null) {
            toolbar = toolbar;
        }
        drawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
            }
        };
        mDrawer.setDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
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

    @Override
    public void showPb() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePb() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUserToNavigationview(User user) {
        if (user != null) {
            Picasso.with(this)
                    .load(user.getAvatarUrl())
                    .into(userAvatar);
            userName.setText(user.getName());
            userBio.setText(user.getBio());
            userLocation.setText(user.getLocation());
        } else {
            Log.d(TAG, "setInfoDataToNavView: Can't set data, user=null");
        }
    }


}
