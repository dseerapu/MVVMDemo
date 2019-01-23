package dharma.github.io.mvvmsample.view.base;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.android.support.AndroidSupportInjection;
import dharma.github.io.mvvmsample.network.NetWorkReceiver;
import dharma.github.io.mvvmsample.network.NetworkListenerInterface;
import dharma.github.io.mvvmsample.viewmodel.base.BaseViewModel;


/**
 * Base Fragment of all the fragments used in this app {@link dharma.github.io.mvvmsample.MVVMSampleApp}
 *
 * @param <VM>
 * @param <Binding>
 */
public abstract class BaseFragment<VM extends BaseViewModel, Binding extends ViewDataBinding> extends Fragment {

    private BaseActivity baseActivity;

    protected VM viewModel;

    protected Binding dataBinding;

    protected abstract VM getViewModel();

    @LayoutRes
    protected abstract int getLayoutResource();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        /**
         * Dependency Injection
         */
        performDependencyInjection();
        super.onCreate(savedInstanceState);

        viewModel = getViewModel();

        //Init snackbar
        setUpSnackbar();

        //Init toast
        setUpToast();

        //Options Menu
        setHasOptionsMenu(false);
    }

    private void performDependencyInjection() {
        AndroidSupportInjection.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutResource(), container, false);
        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initObservers();
        setUp(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.baseActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void onDetach() {
        baseActivity = null;
        super.onDetach();
    }

    public boolean isNetworkConnected() {
        return baseActivity != null && baseActivity.isNetworkConnected();
    }

    public void showLoading() {
        baseActivity.showLoading();
    }

    public void hideLoading() {
        baseActivity.hideLoading();
    }

    public abstract void initObservers();

    public abstract void setUp(View view);

    private void setUpSnackbar() {
        viewModel.getSnackbarMessage().observe(this,
                (Observer<Integer>) snackbarMessage -> {
                    if (baseActivity != null)
                        baseActivity.showSnackbar(getString(snackbarMessage));
                });
    }

    private void setUpToast() {
        viewModel.getToastMessage().observe(this,
                (Observer<Integer>) toastMessage -> {
                    if (baseActivity != null)
                        baseActivity.showToast(getString(toastMessage));
                });
    }

}
