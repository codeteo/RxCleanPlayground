package css.rxclean.rxcleanplayground.main;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

/**
 * Presenter for {@link MainFragment}
 */

class MainPresenter implements MainMVP.Presenter {

    private MainMVP.View view;

    @NonNull
    private CompositeSubscription subscriptions;

    @Inject
    MainPresenter(MainMVP.View view) {
        this.view = view;

        subscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
//        loadData();
    }

    @Override
    public void unsubscribe() {
        subscriptions.clear();
    }

}
