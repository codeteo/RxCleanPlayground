package css.rxclean.rxcleanplayground;

import css.rxclean.rxcleanplayground.utils.MainActivityScope;
import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Exposes dependencies to be used by other modules.
 */

@MainActivityScope
@Component(dependencies = ApplicationComponent.class, modules = NetworkModule.class)
public interface NetworkComponent {

    OkHttpClient okHttpClient();

    Retrofit retrofit();

}
