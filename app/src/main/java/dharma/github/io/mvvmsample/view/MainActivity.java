package dharma.github.io.mvvmsample.view;

import android.os.Bundle;

import dharma.github.io.mvvmsample.R;
import dharma.github.io.mvvmsample.databinding.ActivityMainBinding;
import dharma.github.io.mvvmsample.view.base.BaseActivity;

/**
 * MainActivity
 */
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
