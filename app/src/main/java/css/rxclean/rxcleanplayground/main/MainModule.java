package css.rxclean.rxcleanplayground.main;

import css.rxclean.rxcleanplayground.utils.schedulers.BaseSchedulerProvider;
import css.rxclean.rxcleanplayground.utils.schedulers.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

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

}
