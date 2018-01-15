package udovyk.dribbleclimoxydaggerrx.mvp.presenter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;
import udovyk.dribbleclimoxydaggerrx.App;
import udovyk.dribbleclimoxydaggerrx.Screens;
import udovyk.dribbleclimoxydaggerrx.common.Utils;
import udovyk.dribbleclimoxydaggerrx.manager.ApiManager;
import udovyk.dribbleclimoxydaggerrx.manager.PrefManager;
import udovyk.dribbleclimoxydaggerrx.mvp.view.LoginView;
import udovyk.dribbleclimoxydaggerrx.network.model.OAuthToken;


@InjectViewState
public class LoginPresenter extends BasePresenter<LoginView> {
    private static String TAG = "LoginPresenter";

    @Inject
    ApiManager apiManager;
    @Inject
    PrefManager prefManager;
    @Inject
    Router router;

    public LoginPresenter() {
        App.getApplicationComponent().inject(this);
    }

    public void getAccessToken(String code) {
        apiManager.getAccessToken(code)
                .subscribe(oAuthTokenResponse -> {
                    Log.d(TAG, "trying to save token data into SP");
                    OAuthToken token = oAuthTokenResponse.body();
                    String accessToken = token.getAccessToken();
                    String tokenType = token.getTokenType();
                    Log.d(TAG, "getToken: token == " + accessToken);

                    prefManager.saveAccessToken(accessToken, tokenType);
                    Log.d(TAG, "---Saving was successful---" + accessToken);
                    router.replaceScreen(Screens.SHOTS_FRAGMENT_SCREEN);
                }, throwable -> {
                    throwable.printStackTrace();
                    Log.e(TAG, "---Saving token was failed: getToken: response body == null---");
                } );

    }

    public void clearWebView() {
        Utils.clearWebView();
    }



}
