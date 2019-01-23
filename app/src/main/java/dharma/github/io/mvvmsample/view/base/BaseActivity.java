package dharma.github.io.mvvmsample.view.base;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import dharma.github.io.mvvmsample.R;
import dharma.github.io.mvvmsample.utils.CommonUtils;
import dharma.github.io.mvvmsample.utils.NetworkUtils;
import dharma.github.io.mvvmsample.viewmodel.base.BaseViewModel;

/**
 * Created by Dharma Sai Seerapu on 18th Dec 2018
 * Base Activity for the activities in this app{@link dharma.github.io.mvvmsample.MVVMSampleApp}
 *
 * @param <Binding>
 */
public abstract class BaseActivity<Binding extends ViewDataBinding, VM extends BaseViewModel>
        extends AppCompatActivity {

    /**
     * Network utils
     */
    @Inject
    NetworkUtils networkUtils;

    /**
     * Progress Dialog
     */
    private ProgressDialog progressDialog;

    /**
     * ViewModel viewModel
     */
    private VM viewModel;

    /**
     * Abstract method to init layout of activity
     *
     * @return
     */
    @LayoutRes
    protected abstract int getLayoutResource();

    /**
     * Binding layout
     */
    public Binding dataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        performDependencyInjection();
        setUpViewModel();
        setUpSnackbar();
        setUpToast();
        setUpProgressDialog();

        dataBinding = DataBindingUtil.setContentView(this, getLayoutResource());
    }

    private void setUpViewModel() {

        this.viewModel = viewModel == null ? getViewModel() : viewModel;
    }

    private void setUpSnackbar() {
        viewModel.getSnackbarMessage().observe(this,
                (Observer<Integer>) snackbarMessage -> showSnackbar(getString(snackbarMessage)));
    }

    private void setUpToast() {
        viewModel.getToastMessage().observe(this,
                (Observer<Integer>) toastMessage -> showToast(getString(toastMessage)));
    }

    private void setUpProgressDialog() {
        viewModel.getProgressDialogStatus().observe(this,
                (Observer<Boolean>) status -> {
                    if (status)
                        BaseActivity.this.showLoading();
                    else BaseActivity.this.hideLoading();
                });
    }

    public void showLoading() {
        hideLoading();
        progressDialog = CommonUtils.showLoadingDialog(this);
    }

    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    protected void showSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message,
                Snackbar.LENGTH_SHORT);

        View view = snackbar.getView();

        TextView snackTV = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        snackTV.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));

        snackbar.show();
    }

    protected boolean isNetworkConnected() {
        if (networkUtils.isNetworkConnected())
            return true;
        else {
            showSnackbar(getString(R.string.no_internet));
            return false;
        }
    }

    protected void showToast(String toastMessage) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
    }

    public void onFragmentAttached() {

    }

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract VM getViewModel();

    protected abstract void setUp();
}
