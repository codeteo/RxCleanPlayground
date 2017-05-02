package css.rxclean.rxcleanplayground.network;

import css.rxclean.rxcleanplayground.data.User;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Retrofit interface to get repos for square from Github
 */

public interface GithubService {

    @GET("users/square")
    Observable<User> getRepos();

}