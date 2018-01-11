package udovyk.dribbleclimoxydaggerrx.di.modules;

import android.support.v4.app.Fragment;

import dagger.Module;

/**
 * Created by udovik.s on 10.01.2018.
 */

@Module
public class FragmentModule {
    Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }
}
