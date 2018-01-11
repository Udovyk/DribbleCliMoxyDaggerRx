package udovyk.dribbleclimoxydaggerrx.di.modules;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import udovyk.dribbleclimoxydaggerrx.di.qualifiers.ApplicationContext;

/**
 * Created by udovik.s on 10.01.2018.
 */

@Module(includes = {ApiModule.class, CiceroneModule.class})
public class ApplicationModule {
    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context provideApplicationContext() { return application; }

    @Provides
    Application provideApplication() {return application; }

}
