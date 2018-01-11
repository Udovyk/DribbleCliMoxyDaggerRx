package udovyk.dribbleclimoxydaggerrx.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import udovyk.dribbleclimoxydaggerrx.di.qualifiers.ApplicationContext;
import udovyk.dribbleclimoxydaggerrx.di.scopes.ApplicationScope;
import udovyk.dribbleclimoxydaggerrx.network.OAuthInterceptor;

import static udovyk.dribbleclimoxydaggerrx.common.ApiConstants.BASE_URL;
import static udovyk.dribbleclimoxydaggerrx.common.ApiConstants.BASE_URL_OAUTH;

/**
 * Created by udovik.s on 10.01.2018.
 */

@Module
public class ApiModule {

    private static final int DISK_CACHE_SIZE = 1024 * 1024;

    @Provides
    @ApplicationScope
    Retrofit getSimpleClient() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    @Provides
    @ApplicationScope
    Retrofit getOauthClient(@ApplicationContext Context context) {
        return new Retrofit.Builder()
                .client(
                        new OkHttpClient.Builder()
                                .cache(new Cache(context.getCacheDir(), DISK_CACHE_SIZE))
                                .addInterceptor(new OAuthInterceptor())
                                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                                .build()
                )
                .baseUrl(BASE_URL_OAUTH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
