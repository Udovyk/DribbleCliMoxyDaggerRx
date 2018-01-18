package udovyk.dribbleclimoxydaggerrx.di.modules;

import android.content.Context;
import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;
import udovyk.dribbleclimoxydaggerrx.ui.adapters.ShotsAdapter;

/**
 * Created by udovik.s on 10.01.2018.
 */

@Module
public class FragmentModule {
    Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    ShotsAdapter provideAdapter(Context context) {
        return new ShotsAdapter(context);
    }
}
