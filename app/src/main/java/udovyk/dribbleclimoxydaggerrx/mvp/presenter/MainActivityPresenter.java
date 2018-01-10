package udovyk.dribbleclimoxydaggerrx.mvp.presenter;

import javax.inject.Inject;

import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import udovyk.dribbleclimoxydaggerrx.App;
import udovyk.dribbleclimoxydaggerrx.manager.PrefManager;
import udovyk.dribbleclimoxydaggerrx.mvp.view.MainActivityView;

/**
 * Created by udovik.s on 10.01.2018.
 */

public class MainActivityPresenter extends BasePresenter<MainActivityView>{

    @Inject
    NavigatorHolder navigatorHolder;
    @Inject
    Router router;
    @Inject
    PrefManager prefManager;

    public MainActivityPresenter() {
        App.getApplicationComponent().inject(this);
    }
}
