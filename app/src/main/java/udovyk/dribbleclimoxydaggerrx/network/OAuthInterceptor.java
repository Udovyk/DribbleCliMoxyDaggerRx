package udovyk.dribbleclimoxydaggerrx.network;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;

import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import udovyk.dribbleclimoxydaggerrx.MyApplication;
import udovyk.dribbleclimoxydaggerrx.network.model.OAuthToken;

@Singleton
public class OAuthInterceptor implements Interceptor {
    private static final String TAG = "OAuthInterceptor";

    private String accessToken;
    private String accessTokenType;

    @Override
    public Response intercept(Chain chain) throws IOException {
        //find the token
        OAuthToken oAuthToken = OAuthToken.Factory.create();
        accessToken = oAuthToken.getAccessToken();
        accessTokenType = oAuthToken.getTokenType();
        //add it to the request
        Request.Builder builder = chain.request().newBuilder();
        if (!TextUtils.isEmpty(accessToken) && !TextUtils.isEmpty(accessTokenType)) {
            Log.e(TAG, "In the interceptor adding the header authorization with : " + accessTokenType + " " + accessToken);
            builder.header("Authorization", accessTokenType + " " + accessToken);
        } else {
            Log.e(TAG, "In the interceptor there is a trouble with : " + accessTokenType + " " + accessToken);
            //you should launch the loginActivity to fix that:
            startLoginActivity();
        }

        Response response = chain.proceed(builder.build());
        //handle 401 unauthorized
        if (response.code() == 401) {
            startLoginActivity();
            return response;
        }
        return chain.proceed(builder.build());
    }

    //Todo use a Router?
    private void startLoginActivity() {
        Intent i = new Intent(MyApplication.instance, LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.instance.startActivity(i);
    }
}
