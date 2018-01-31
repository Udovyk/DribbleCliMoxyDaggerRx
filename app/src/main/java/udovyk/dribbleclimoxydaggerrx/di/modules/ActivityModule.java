package udovyk.dribbleclimoxydaggerrx.di.modules;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;

/**
 * Created by udovik.s on 10.01.2018.
 */

@Module
public class ActivityModule {

    AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }
}
