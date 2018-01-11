package udovyk.dribbleclimoxydaggerrx;

import ru.terrakok.cicerone.Cicerone;
import udovyk.dribbleclimoxydaggerrx.di.components.ApplicationComponent;
import udovyk.dribbleclimoxydaggerrx.di.modules.ApiModule;
import udovyk.dribbleclimoxydaggerrx.di.modules.ApplicationModule;
import udovyk.dribbleclimoxydaggerrx.di.modules.CiceroneModule;

public class App extends android.app.Application {

    public static App INSTANCE;
    private static ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        INSTANCE = this;
        super.onCreate();
        appComponent = DaggerApplicationComponent
                .builder()
                .apiModule(new ApiModule())
                .applicationModule(new ApplicationModule(this))
                .ciceronModule(new CiceroneModule(Cicerone.create()))
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return appComponent;
    }
}
