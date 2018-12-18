package dharma.github.io.mvvmsample.view.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Dharma Sai Seerapu on 18th Dec 2018
 * Base Activity for the activities in this app{@link dharma.github.io.mvvmsample.MVVMSampleApp}
 *
 * @param <Binding>
 */
public abstract class BaseActivity<Binding extends ViewDataBinding> extends AppCompatActivity implements HasSupportFragmentInjector {

    /**
     * Abstract method to init layout of activity
     *
     * @return
     */
    @LayoutRes
    protected abstract int getLayoutResource();

    /**
     * Default Android Injector
     */
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentAndroidInjector;

    /**
     * Binding layout
     */
    public Binding dataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        dataBinding = DataBindingUtil.setContentView(this, getLayoutResource());
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentAndroidInjector;
    }
}
