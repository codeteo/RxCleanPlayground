package css.rxclean.rxcleanplayground.main;


import css.rxclean.rxcleanplayground.NetworkComponent;
import css.rxclean.rxcleanplayground.utils.MainFragmentScope;
import dagger.Component;

/**
 * Dagger Component for {@link MainFragment}
 */

@MainFragmentScope
@Component(dependencies = NetworkComponent.class, modules = {MainModule.class})
public interface MainComponent {

    void inject(MainFragment mainFragment);

}
