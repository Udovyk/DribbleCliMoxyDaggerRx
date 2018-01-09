package udovyk.dribbleclimoxydaggerrx.network.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.File;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import udovyk.dribbleclimoxydaggerrx.network.ApiService;
import udovyk.dribbleclimoxydaggerrx.network.OAuthInterceptor;

public class RetrofitBuilder {

    public static final String BASE_URL = "https://dribbble.com/";
    public static final String BASE_URL_OAUTH = "https://api.dribbble.com/v1/";

    public static ApiService getSimpleClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        ApiService dribbbleClient = retrofit.create(ApiService.class);

        return dribbbleClient;
    }

    /***********************************************************
     * OkHttp Clients
     **********************************************************/
    public static ApiService getOAuthClient(Context ctx) {
        // now it's using the cach
        // Using my HttpClient
        Retrofit raCustom = new Retrofit.Builder()
                .client(getOAuthOkHttpClient(ctx))
                .baseUrl(BASE_URL_OAUTH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService webServer = raCustom.create(ApiService.class);
        return webServer;
    }

    /**
     * Return a simple OkHttpClient:
     * have a cache
     * have a HttpLogger
     */
    @NonNull
    public static OkHttpClient getSimpleOkHttpClient(Context ctx) {
        // Define the OkHttp Client with its cache!
        // Assigning a CacheDirectory
        File myCacheDir=new File(ctx.getCacheDir(),"OkHttpCache");
        // You should create it...
        int cacheSize=1024*1024;
        Cache cacheDir=new Cache(myCacheDir,cacheSize);
        HttpLoggingInterceptor httpLogInterceptor=new HttpLoggingInterceptor();
        httpLogInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                //add a cache
                .cache(cacheDir)
                .addInterceptor(httpLogInterceptor)
                .build();
    }

    /**
     * Return a OAuth OkHttpClient:
     * have a cache
     * have a HttpLogger
     * add automaticly the token in the header of each request because of the oAuthInterceptor
     */
    @NonNull
    public static OkHttpClient getOAuthOkHttpClient(Context ctx) {
        // Define the OkHttp Client with its cache!
        // Assigning a CacheDirectory
        File myCacheDir=new File(ctx.getCacheDir(),"OkHttpCache");
        // You should create it...
        int cacheSize=1024*1024;
        Cache cacheDir=new Cache(myCacheDir,cacheSize);
        Interceptor oAuthInterceptor=new OAuthInterceptor();
        HttpLoggingInterceptor httpLogInterceptor=new HttpLoggingInterceptor();
        httpLogInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .cache(cacheDir)
                .addInterceptor(oAuthInterceptor)
                .addInterceptor(httpLogInterceptor)
                .build();
    }
}
