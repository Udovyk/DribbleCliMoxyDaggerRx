package udovyk.dribbleclimoxydaggerrx.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import udovyk.dribbleclimoxydaggerrx.App;
import udovyk.dribbleclimoxydaggerrx.Screens;
import udovyk.dribbleclimoxydaggerrx.manager.ApiManager;
import udovyk.dribbleclimoxydaggerrx.manager.PrefManager;
import udovyk.dribbleclimoxydaggerrx.mvp.view.MainActivityView;
import udovyk.dribbleclimoxydaggerrx.network.model.User;

/**
 * Created by udovik.s on 10.01.2018.
 */

@InjectViewState
public class MainActivityPresenter extends BasePresenter<MainActivityView> {
    private static final String TAG = "MainPresenter";

    @Inject
    NavigatorHolder navigatorHolder;
    @Inject
    Router router;
    @Inject
    PrefManager prefManager;
    @Inject
    ApiManager apiManager;


    public MainActivityPresenter() {
        App.getApplicationComponent().inject(this);
    }

    public void initScreen() {
        if (prefManager.containsToken()) {
            getAuthUser();
            router.replaceScreen(Screens.SHOTS_FRAGMENT_SCREEN);
        } else {
            router.replaceScreen(Screens.START_SCREEN_FRAGMENT_SCREEN);
        }
    }

    public void getAuthUser() {
        apiManager.getUserInfo().subscribe(
                userResponse -> {
                    User user = userResponse.body();
                    Log.d(TAG, "!!!! user success," + user.getName());
                    getViewState().setUserToNavigationview(user);
                }, throwable -> {
                    throwable.printStackTrace();
                    Log.d(TAG, "---request to AuthUser was failed");
                }
        );
    }


    public void setUserToNH() {
        if (prefManager.containsToken()) {
            getAuthUser();
        }
    }

    public void onBackPressed() {
        router.exit();
    }


    public void clearSharedPref() {
        prefManager.clearSharedPref();
    }

    public void setNavigator(Navigator navigator) {
        navigatorHolder.setNavigator(navigator);
    }

    public void removeNavigator() {
        navigatorHolder.removeNavigator();
    }
}
