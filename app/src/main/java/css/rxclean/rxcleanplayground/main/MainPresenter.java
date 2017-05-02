package css.rxclean.rxcleanplayground.main;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import css.rxclean.rxcleanplayground.data.User;
import css.rxclean.rxcleanplayground.network.GithubService;
import css.rxclean.rxcleanplayground.utils.schedulers.BaseSchedulerProvider;
import rx.Observer;
import rx.Subscription;
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
        loadData();
    }

    @Override
    public void unsubscribe() {
        subscriptions.clear();
    }

    private void loadData() {
        subscriptions.clear();

        Subscription subscription = githubService.getRepos()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {
                        // show completed
                    }

                    @Override
                    public void onError(Throwable e) {
                        // show error
                    }

                    @Override
                    public void onNext(User user) {
                        // show data
                    }
                });

        subscriptions.add(subscription);

    }

}
