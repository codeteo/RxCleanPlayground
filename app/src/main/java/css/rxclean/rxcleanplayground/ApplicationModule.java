package css.rxclean.rxcleanplayground;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import css.rxclean.rxcleanplayground.utils.BaseUrlInterceptor;
import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;

/**
 * Base Dagger Module.
 */

@Module
public final class ApplicationModule {

    public static final HttpUrl PRODUCTION_API_BASE_URL = HttpUrl.parse(Constants.BASE_URL);

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    HttpUrl providesBaseUrl() {
        return PRODUCTION_API_BASE_URL;
    }

    @Provides
    @Singleton
    // For Testing Purposes
    static BaseUrlInterceptor providesBaseUrlInterceptor() {
        return new BaseUrlInterceptor(Constants.BASE_URL);
    }

}