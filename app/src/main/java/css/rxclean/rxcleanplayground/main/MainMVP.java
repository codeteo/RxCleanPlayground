package css.rxclean.rxcleanplayground.main;

import css.rxclean.rxcleanplayground.BasePresenter;
import css.rxclean.rxcleanplayground.BaseView;
import css.rxclean.rxcleanplayground.data.User;

/**
 * Contract between the View and the Presenter for {@link MainActivity}
 */

interface MainMVP {

    interface View extends BaseView<Presenter> {

        void showData(User user);

        void showError();

        void enableRetryButton();

    }

    interface Presenter extends BasePresenter {

        void retryRequest();

    }

}
