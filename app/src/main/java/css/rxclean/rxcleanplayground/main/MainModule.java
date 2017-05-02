package css.rxclean.rxcleanplayground.main;

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

}
