package css.rxclean.rxcleanplayground.main;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import css.rxclean.rxcleanplayground.MyApplication;
import css.rxclean.rxcleanplayground.R;
import css.rxclean.rxcleanplayground.data.User;

/**
 * Fragment screen for {@link MainActivity}
 */

public class MainFragment extends Fragment implements MainMVP.View {

    @BindView(R.id.tv_main_fragment_user) TextView tvUser;
    @BindView(R.id.btn_main_fragment_retry) Button btnRetry;

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
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void showData(User user) {
        tvUser.setText(user.login);
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void enableRetryButton() {
        btnRetry.setVisibility(View.VISIBLE);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.retryRequest();
                btnRetry.setVisibility(View.GONE);
            }
        });
    }

}
