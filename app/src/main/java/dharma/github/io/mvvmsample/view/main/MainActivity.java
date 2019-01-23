package dharma.github.io.mvvmsample.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import dharma.github.io.mvvmsample.R;
import dharma.github.io.mvvmsample.databinding.ActivityMainBinding;
import dharma.github.io.mvvmsample.view.base.BaseActivity;
import dharma.github.io.mvvmsample.view.gallery.GalleryFragment;
import dharma.github.io.mvvmsample.view.utils.FragmentUtils;

import static dharma.github.io.mvvmsample.view.utils.FragmentUtils.TRANSITION_NONE;

/**
 * MainActivity
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainActivityViewModel> implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    MainActivityViewModel mainActivityModule;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public MainActivityViewModel getViewModel() {
        return mainActivityModule;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUp();

    }

    @Override
    protected void setUp() {
        FragmentUtils.replaceFragment(this, GalleryFragment.newInstance(), R.id.container, false, TRANSITION_NONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
