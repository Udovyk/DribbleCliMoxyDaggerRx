package udovyk.dribbleclimoxydaggerrx.manager;

import android.content.SharedPreferences;

import javax.inject.Inject;

import udovyk.dribbleclimoxydaggerrx.App;
import udovyk.dribbleclimoxydaggerrx.common.TokenDetails;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by udovik.s on 09.01.2018.
 */

public class PrefManager {

    @Inject
    public PrefManager() {}

    public void saveAccessToken(String accessToken, String tokenType) {
        SharedPreferences sp = App.INSTANCE.getSharedPreferences(TokenDetails.OAUTH_SHARED_PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        if (sp.contains(TokenDetails.SP_TOKEN_KEY) || sp.contains(TokenDetails.SP_TOKEN_TYPE_KEY)) {
            ed.clear().commit();
        }
        ed.putString(TokenDetails.SP_TOKEN_KEY, accessToken);
        ed.putString(TokenDetails.SP_TOKEN_TYPE_KEY, tokenType);
        ed.commit();
    }

    public boolean containsToken() {
        return App.INSTANCE
                .getSharedPreferences(TokenDetails.OAUTH_SHARED_PREFERENCE_NAME, MODE_PRIVATE)
                .contains(TokenDetails.SP_TOKEN_KEY) ? true : false;
    }

    //Todo clear sp
    public void clearSharedPref() {
        //impl
    }
}
