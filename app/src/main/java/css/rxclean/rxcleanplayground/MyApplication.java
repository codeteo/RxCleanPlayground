package css.rxclean.rxcleanplayground;

import android.app.Application;

/**
 * Created by css on 5/2/17.
 */

public class MyApplication extends Application {

    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
