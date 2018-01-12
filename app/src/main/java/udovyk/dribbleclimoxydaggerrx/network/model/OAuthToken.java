package udovyk.dribbleclimoxydaggerrx.network.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import udovyk.dribbleclimoxydaggerrx.App;
import udovyk.dribbleclimoxydaggerrx.common.TokenDetails;


public class OAuthToken {
    private static final String TAG = "OAuthToken";

    private static final String OAUTH_SHARED_PREFERENCE_NAME = "OAuthPrefs";

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("scope")
    private String scope;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OAuthToken{");
        sb.append("accessToken='").append(accessToken).append('\'');
        sb.append(", tokenType=' ").append(tokenType).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getScope() {
        return scope;
    }


    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }


    public static class Factory {
        private static final String TAG = "OAuthToken.Factory";

        public static OAuthToken create() {

            OAuthToken oAuthToken = new OAuthToken();
            SharedPreferences sp = App.INSTANCE.getSharedPreferences(OAUTH_SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
            if (sp.contains(TokenDetails.SP_TOKEN_KEY) && sp.contains(TokenDetails.SP_TOKEN_TYPE_KEY)) {
                oAuthToken.setAccessToken(sp.getString(TokenDetails.SP_TOKEN_KEY, null));
                oAuthToken.setTokenType(sp.getString(TokenDetails.SP_TOKEN_TYPE_KEY, null));
            }
            else {
                Log.d(TAG, "failed to create token, SP contains null");
                return null;
            }
            return oAuthToken;
        }
    }



}
