package udovyk.dribbleclimoxydaggerrx.network;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;

import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import udovyk.dribbleclimoxydaggerrx.network.model.OAuthToken;

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
        }

        return chain.proceed(builder.build());
    }

}
