package css.rxclean.rxcleanplayground;

import android.app.Application;
import android.util.Log;

/**
 * Application class builds and provides Dagger Components with Application scope.
 */

public class MyApplication extends Application {

    private static ApplicationComponent applicationComponent;
    private static NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        networkComponent = DaggerNetworkComponent.builder()
                .applicationComponent(applicationComponent)
                .networkModule(new NetworkModule())
                .build();

    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static NetworkComponent getNetworkComponent() {
        return networkComponent;
    }

}
