package udovyk.dribbleclimoxydaggerrx.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.terrakok.cicerone.Navigator;
import udovyk.dribbleclimoxydaggerrx.App;
import udovyk.dribbleclimoxydaggerrx.R;
import udovyk.dribbleclimoxydaggerrx.Screens;
import udovyk.dribbleclimoxydaggerrx.di.components.ActivityComponent;
import udovyk.dribbleclimoxydaggerrx.di.components.ApplicationComponent;
import udovyk.dribbleclimoxydaggerrx.di.modules.ActivityModule;
import udovyk.dribbleclimoxydaggerrx.mvp.view.BaseMvpView;
import udovyk.dribbleclimoxydaggerrx.navigation.SupportFragmentNavigator;
import udovyk.dribbleclimoxydaggerrx.ui.fragment.ShotAttachmentsFragment;
import udovyk.dribbleclimoxydaggerrx.ui.fragment.ShotDetailsFragment;
import udovyk.dribbleclimoxydaggerrx.ui.fragment.ShotsFragment;
import udovyk.dribbleclimoxydaggerrx.ui.fragment.LoginFragment;
import udovyk.dribbleclimoxydaggerrx.ui.fragment.StartScreenFragment;
import udovyk.dribbleclimoxydaggerrx.ui.widget.ToolbarActions;

/**
 * Created by udovik.s on 10.01.2018.
 */

public abstract class BaseActivity extends MvpAppCompatActivity implements BaseMvpView, ToolbarActions {

    protected Navigator baseNavigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.activity_home_fragment_container, R.anim.enter_from_right) {

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch (screenKey) {
                case Screens.START_SCREEN_FRAGMENT_SCREEN:
                    return StartScreenFragment.newInstance();
                case Screens.LOGIN_FRAGMENT_SCREEN:
                    return LoginFragment.newInstance();
                case Screens.SHOTS_FRAGMENT_SCREEN:
                    return ShotsFragment.newInstance();
                case Screens.SHOT_DETAILS_FRAGMENT_SCREEN:
                    ShotDetailsFragment detailsFragment = ShotDetailsFragment.newInstance();
                    //pass data
                    return detailsFragment;
                case Screens.SHOT_ATTACHMENTS_FRAGMENT_SCREEN:
                    //return ShotAttachmentsFragment.newInstance();
            }
            return null;
        }

        @Override
        protected void showSystemMessage(String message) {
            Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void exit() {
            finish();
        }
    };

        public ActivityComponent activityComponent;
        private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        activityComponent = getApplicationComponent().providesActivityComponent(new ActivityModule(this));
        super.onCreate(savedInstanceState);
        initViews();
    }

    @Override
    protected void onDestroy() {
        if(unbinder!=null){
            unbinder.unbind();
        }
        super.onDestroy();
    }

    public void initViews() {
        setContentView(getLayoutRes());
        unbinder =  ButterKnife.bind(this);
    }

    ApplicationComponent getApplicationComponent() {
        return App.getApplicationComponent();
    }

    public abstract int getLayoutRes();

    public abstract void injectDependency();

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void showMessage(String title, String message) {
        //unresolved
    }

    @Override
    public void showMessage(String message) {
        //unresolved
    }


    @Override
    public void showMessage(int title, int message) {
        showMessage(getString(title), getString(message));
    }

    @Override
    public void showMessage(int title) {
        showMessage(getString(title));
    }

    @Override
    public void showToastMessage(int title) {
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
    }
}
