package udovyk.dribbleclimoxydaggerrx.manager;

import android.content.Context;
import android.content.SharedPreferences;

import udovyk.dribbleclimoxydaggerrx.MyApplication;
import udovyk.dribbleclimoxydaggerrx.common.TokenDetails;

/**
 * Created by udovik.s on 09.01.2018.
 */

public class PrefManager {

    public static void saveAccessToken(String accessToken, String tokenType) {
        SharedPreferences sp = MyApplication.instance.getSharedPreferences(TokenDetails.OAUTH_SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        if (sp.contains(TokenDetails.SP_TOKEN_KEY) || sp.contains(TokenDetails.SP_TOKEN_TYPE_KEY)) {
            ed.clear().commit();
        }
        ed.putString(TokenDetails.SP_TOKEN_KEY, accessToken);
        ed.putString(TokenDetails.SP_TOKEN_TYPE_KEY, tokenType);
        ed.commit();
    }
}
