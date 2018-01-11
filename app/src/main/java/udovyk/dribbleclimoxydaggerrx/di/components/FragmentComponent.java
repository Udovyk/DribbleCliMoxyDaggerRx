package udovyk.dribbleclimoxydaggerrx.di.components;

import dagger.Subcomponent;
import udovyk.dribbleclimoxydaggerrx.di.modules.FragmentModule;
import udovyk.dribbleclimoxydaggerrx.di.scopes.FragmentScope;
import udovyk.dribbleclimoxydaggerrx.ui.fragment.LoginFragment;
import udovyk.dribbleclimoxydaggerrx.ui.fragment.ShotAttachmentsFragment;
import udovyk.dribbleclimoxydaggerrx.ui.fragment.ShotDetailsFragment;
import udovyk.dribbleclimoxydaggerrx.ui.fragment.ShotsFragment;
import udovyk.dribbleclimoxydaggerrx.ui.fragment.StartScreenFragment;

/**
 * Created by udovik.s on 10.01.2018.
 */

@FragmentScope
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(StartScreenFragment fragment);
    void inject(LoginFragment fragment);
    void inject(ShotsFragment fragment);
    void inject(ShotDetailsFragment fragment);
    void inject(ShotAttachmentsFragment fragment);
}
