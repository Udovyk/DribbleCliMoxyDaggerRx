package udovyk.dribbleclimoxydaggerrx.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import udovyk.dribbleclimoxydaggerrx.App;
import udovyk.dribbleclimoxydaggerrx.Screens;
import udovyk.dribbleclimoxydaggerrx.manager.PrefManager;
import udovyk.dribbleclimoxydaggerrx.mvp.view.MainActivityView;

/**
 * Created by udovik.s on 10.01.2018.
 */

@InjectViewState
public class MainActivityPresenter extends BasePresenter<MainActivityView> {

    @Inject
    NavigatorHolder navigatorHolder;
    @Inject
    Router router;
    @Inject
    PrefManager prefManager;

    public MainActivityPresenter() {
        App.getApplicationComponent().inject(this);
    }

    public void initScreen() {
        if (prefManager.containsToken()) {
            router.replaceScreen(Screens.SHOTS_FRAGMENT_SCREEN);
        } else {
            router.replaceScreen(Screens.START_SCREEN_FRAGMENT_SCREEN);
        }
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
