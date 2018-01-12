package udovyk.dribbleclimoxydaggerrx.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import udovyk.dribbleclimoxydaggerrx.di.qualifiers.ApplicationContext;
import udovyk.dribbleclimoxydaggerrx.di.scopes.ActivityScope;
import udovyk.dribbleclimoxydaggerrx.di.scopes.ApplicationScope;
import udovyk.dribbleclimoxydaggerrx.manager.ApiManager;
import udovyk.dribbleclimoxydaggerrx.network.ApiService;
import udovyk.dribbleclimoxydaggerrx.network.OAuthInterceptor;

import static udovyk.dribbleclimoxydaggerrx.common.ApiConstants.BASE_URL;
import static udovyk.dribbleclimoxydaggerrx.common.ApiConstants.BASE_URL_OAUTH;

/**
 * Created by udovik.s on 10.01.2018.
 */

@Module
public class ApiModule {
    private static final int DISK_CACHE_SIZE = 10 * 1024 * 1024;

    @Provides
    @ApplicationScope
    public Retrofit getClient(OkHttpClient client) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(BASE_URL)
                .build();
    }

    @Provides
    @ActivityScope
    public ApiService provideApiService(Retrofit retrofit) {
        return  retrofit.create(ApiService.class);
    }

    @Provides
    @ApplicationScope
    public ApiManager provideApiManager(ApiService apiService) {
        return new ApiManager(apiService);
    }

    @Provides
    @ApplicationScope
    OkHttpClient provideOkhttpClient(Context context) { return createOkHttpClient(context); }

    private static OkHttpClient createOkHttpClient(Context context) {
        return new OkHttpClient.Builder()
                .cache(new Cache(context.getCacheDir(), DISK_CACHE_SIZE))
                .addInterceptor(new OAuthInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }


}
