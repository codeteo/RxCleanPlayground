package css.rxclean.rxcleanplayground.main;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import css.rxclean.rxcleanplayground.data.User;
import css.rxclean.rxcleanplayground.network.GithubService;
import css.rxclean.rxcleanplayground.utils.schedulers.BaseSchedulerProvider;
import css.rxclean.rxcleanplayground.utils.schedulers.ImmediateSchedulerProvider;
import rx.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link MainPresenter}
 */

public class MainPresenterTest {

    @Mock
    private MainMVP.View view;

    @Mock
    private GithubService githubService;

    private BaseSchedulerProvider schedulerProvider;

    private MainPresenter presenter;

    @Before
    public void setUp() throws Exception {
        // injects mocks
        MockitoAnnotations.initMocks(this);

        // make sure all schedulers are immediate
        schedulerProvider = new ImmediateSchedulerProvider();

        presenter = new MainPresenter(view, schedulerProvider, githubService);

    }

    @Test
    public void loadData_shouldShowData() throws Exception {
        // when
        when(githubService.getRepos()).thenReturn(user());
        presenter.loadData();

        // then
        verify(view).showData(any(User.class));

    }

    @Test
    public void loadData_withError_shouldShowError() throws Exception{
        //when
        when(githubService.getRepos()).thenReturn(Observable.<User>error(new Throwable("error message")));
        presenter.loadData();

        verify(view).showError();

    }

    @Test
    public void retryRequest_shouldShowData() throws Exception {
        // when
        when(githubService.getRepos()).thenReturn(user());
        presenter.loadData();

        // then
        verify(view).showData(any(User.class));

    }

    private Observable<User> user() {
        return Observable.just(new User("login", "avatar"));
    }

}