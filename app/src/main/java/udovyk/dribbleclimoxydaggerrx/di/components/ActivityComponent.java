package udovyk.dribbleclimoxydaggerrx.di.components;

import dagger.Subcomponent;
import udovyk.dribbleclimoxydaggerrx.di.modules.ActivityModule;
import udovyk.dribbleclimoxydaggerrx.di.modules.FragmentModule;
import udovyk.dribbleclimoxydaggerrx.di.scopes.ActivityScope;
import udovyk.dribbleclimoxydaggerrx.ui.activity.MainActivity;

/**
 * Created by udovik.s on 10.01.2018.
 */

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    FragmentComponent providesFragmentComponent(FragmentModule fragmentModule);

    void inject(MainActivity mainActivity);
}
