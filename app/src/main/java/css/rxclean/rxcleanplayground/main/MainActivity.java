package css.rxclean.rxcleanplayground.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import css.rxclean.rxcleanplayground.R;

public class MainActivity extends AppCompatActivity {

    FrameLayout mainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainContainer = (FrameLayout) findViewById(R.id.fl_main_container);

        getFragmentManager().beginTransaction()
                .add(R.id.fl_main_container, MainFragment.newInstance(), null)
                .commit();

    }

}
