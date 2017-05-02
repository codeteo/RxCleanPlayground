package css.rxclean.rxcleanplayground.main;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import css.rxclean.rxcleanplayground.utils.schedulers.BaseSchedulerProvider;
import rx.subscriptions.CompositeSubscription;

/**
 * Presenter for {@link MainFragment}
 */

class MainPresenter implements MainMVP.Presenter {

    @NonNull
    private final MainMVP.View view;

    @NonNull
    private final BaseSchedulerProvider schedulerProvider;

    @NonNull
    private CompositeSubscription subscriptions;

    @Inject
    MainPresenter(@NonNull MainMVP.View view, @NonNull BaseSchedulerProvider schedulerProvider) {
        this.view = view;
        this.schedulerProvider = schedulerProvider;

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
