package css.rxclean.rxcleanplayground.main;

import css.rxclean.rxcleanplayground.BasePresenter;
import css.rxclean.rxcleanplayground.BaseView;

/**
 * Contract between the View and the Presenter for {@link MainActivity}
 */

public interface MainMVP {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

    }

}
