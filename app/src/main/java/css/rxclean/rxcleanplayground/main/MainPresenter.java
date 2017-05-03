package css.rxclean.rxcleanplayground.main;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import css.rxclean.rxcleanplayground.data.User;
import css.rxclean.rxcleanplayground.network.GithubService;
import css.rxclean.rxcleanplayground.utils.schedulers.BaseSchedulerProvider;
import css.rxclean.rxcleanplayground.utils.testHelpers.EspressoIdlingResource;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action0;
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

    @NonNull
    private final GithubService githubService;

    @Inject
    MainPresenter(@NonNull MainMVP.View view,
                  @NonNull BaseSchedulerProvider schedulerProvider, @NonNull GithubService githubService) {
        this.view = view;
        this.schedulerProvider = schedulerProvider;
        this.githubService = githubService;

        subscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        subscriptions.clear();
    }


    @Override
    public void loadData() {
        getRepos();
    }

    private void getRepos() {
        subscriptions.clear();

        // The network request might be handled in a different thread so make sure Espresso knows
        // that the app is busy until the response is handled.
        EspressoIdlingResource.increment(); // App is busy until further notice

        Subscription subscription = githubService.getRepos()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement(); // Set app as idle.
                        }
                    }
                })
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError();
                        view.enableRetryButton();
                    }

                    @Override
                    public void onNext(User user) {
                        view.showData(user);
                    }
                });

        subscriptions.add(subscription);

    }

    @Override
    public void retryRequest() {
        getRepos();
    }
}
