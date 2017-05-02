package css.rxclean.rxcleanplayground.main;

import css.rxclean.rxcleanplayground.network.GithubService;
import css.rxclean.rxcleanplayground.utils.schedulers.BaseSchedulerProvider;
import css.rxclean.rxcleanplayground.utils.schedulers.SchedulerProvider;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 *
 */

@Module
class MainModule {

    MainMVP.View view;

    MainModule(MainMVP.View view) {
        this.view = view;
    }

    @Provides
    MainMVP.View provideMainMVPView(){
        return view;
    }

    @Provides
    BaseSchedulerProvider baseSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }

    @Provides
    GithubService providesGithubService(Retrofit retrofit) {
        return retrofit.create(GithubService.class);
    }

}
