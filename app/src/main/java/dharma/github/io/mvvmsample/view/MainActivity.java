package dharma.github.io.mvvmsample.view;

import android.os.Bundle;
import android.view.MenuItem;

import dharma.github.io.mvvmsample.R;
import dharma.github.io.mvvmsample.databinding.ActivityMainBinding;
import dharma.github.io.mvvmsample.view.base.BaseActivity;
import dharma.github.io.mvvmsample.view.utils.FragmentUtils;

import static dharma.github.io.mvvmsample.view.utils.FragmentUtils.TRANSITION_NONE;

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
        FragmentUtils.replaceFragment(this, MainFragment.newInstance(), R.id.container, false, TRANSITION_NONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}
