package udovyk.dribbleclimoxydaggerrx;

public class MyApplication extends android.app.Application {
    public static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
