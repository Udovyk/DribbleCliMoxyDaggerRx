package udovyk.dribbleclimoxydaggerrx.mvp.presenter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import udovyk.dribbleclimoxydaggerrx.App;
import udovyk.dribbleclimoxydaggerrx.manager.ApiManager;
import udovyk.dribbleclimoxydaggerrx.manager.PrefManager;
import udovyk.dribbleclimoxydaggerrx.mvp.view.LoginView;


@InjectViewState
public class LoginPresenter extends BasePresenter<LoginView> {

    private static String TAG = "LoginPresenter";

    @Inject
    ApiManager apiManager;
    @Inject
    PrefManager prefManager;

    public LoginPresenter() {
        App.getApplicationComponent().inject(this);
    }


    public AlertDialog.Builder buildDialog(Context c) {
        return getViewState().buildDialog(c);
    }


    public void setLoginActivityContentView() {
        getViewState().setLoginActivityContentView();
    }

    public void startMainActivity() {
        getViewState().startMainActivity();
    }


    public void getAccessToken(String code) {
        apiManager.getAccessToken(code)
                .subscribe(oAuthToken -> {
                    Log.d(TAG, "trying to save token data into SP");
                    String accessToken = oAuthToken.getAccessToken();
                    String tokenType = oAuthToken.getTokenType();
                    Log.d(TAG, "getToken: token == " + accessToken);

                    prefManager.saveAccessToken(accessToken, tokenType);
                    Log.d(TAG, "---Saving was successful---" + accessToken);
                    getViewState().startMainActivity();
                }, throwable -> {
                    Log.e(TAG, "---Saving token was failed: getToken: response body == null---");
                } );

    }



}
