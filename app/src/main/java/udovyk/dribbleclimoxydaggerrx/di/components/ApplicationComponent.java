package udovyk.dribbleclimoxydaggerrx.di.components;

import dagger.Component;
import udovyk.dribbleclimoxydaggerrx.di.modules.ActivityModule;
import udovyk.dribbleclimoxydaggerrx.di.modules.ApplicationModule;
import udovyk.dribbleclimoxydaggerrx.di.scopes.ApplicationScope;
import udovyk.dribbleclimoxydaggerrx.mvp.presenter.LoginPresenter;
import udovyk.dribbleclimoxydaggerrx.mvp.presenter.MainActivityPresenter;
import udovyk.dribbleclimoxydaggerrx.mvp.presenter.ShotAttachmentsPresenter;
import udovyk.dribbleclimoxydaggerrx.mvp.presenter.ShotDetailsPresenter;
import udovyk.dribbleclimoxydaggerrx.mvp.presenter.ShotsPresenter;
import udovyk.dribbleclimoxydaggerrx.mvp.presenter.StartScreenPresenter;

/**
 * Created by udovik.s on 10.01.2018.
 */

@ApplicationScope
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    ActivityComponent providesActivityComponent(ActivityModule activityModule);

    void inject(MainActivityPresenter presenter);
    void inject(StartScreenPresenter presenter);
    void inject(LoginPresenter presenter);
    void inject(ShotsPresenter presenter);
    void inject(ShotDetailsPresenter presenter);
    void inject(ShotAttachmentsPresenter presenter);
}
