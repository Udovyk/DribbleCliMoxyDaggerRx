package udovyk.dribbleclimoxydaggerrx.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpView;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;
import udovyk.dribbleclimoxydaggerrx.App;
import udovyk.dribbleclimoxydaggerrx.Screens;
import udovyk.dribbleclimoxydaggerrx.mvp.view.StartScreenView;

/**
 * Created by udovik.s on 11.01.2018.
 */

@InjectViewState
public class StartScreenPresenter extends BasePresenter<StartScreenView> {

    @Inject
    Router router;

    public StartScreenPresenter() {
        App.getApplicationComponent().inject(this);
    }

    public void goToLoginScreen() {
        router.replaceScreen(Screens.LOGIN_FRAGMENT_SCREEN);
    }
}
