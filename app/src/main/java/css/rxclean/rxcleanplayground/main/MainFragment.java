package css.rxclean.rxcleanplayground.main;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import css.rxclean.rxcleanplayground.MyApplication;
import css.rxclean.rxcleanplayground.R;

/**
 * Fragment screen for {@link MainActivity}
 */

public class MainFragment extends Fragment implements MainMVP.View {

    @Inject
    MainPresenter mainPresenter;

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mainPresenter == null) {

            DaggerMainComponent.builder()
                    .networkComponent(MyApplication.getNetworkComponent())
                    .mainModule(new MainModule(this))
                    .build()
                    .inject(this);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        mainPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mainPresenter.unsubscribe();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        return view;
    }

}
