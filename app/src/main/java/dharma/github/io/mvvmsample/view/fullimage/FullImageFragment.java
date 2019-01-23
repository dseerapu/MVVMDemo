package dharma.github.io.mvvmsample.view.fullimage;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.view.View;

import javax.inject.Inject;

import dharma.github.io.mvvmsample.R;
import dharma.github.io.mvvmsample.constants.Constants;
import dharma.github.io.mvvmsample.databinding.FullImageFragmentBinding;
import dharma.github.io.mvvmsample.view.base.BaseFragment;

public class FullImageFragment extends BaseFragment<FullImageViewModel, FullImageFragmentBinding> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private FullImageViewModel viewModel;

    @Override
    protected FullImageViewModel getViewModel() {

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FullImageViewModel.class);
        return viewModel;
    }

    @Override
    public void initObservers() {

    }

    @Override
    public void setUp(View view) {

        if (null != getArguments()) {
            final String imageUrl = getArguments().getString(Constants.IMAGE_URL);
            dataBinding.setGalleryItem(imageUrl);
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.full_image_fragment;
    }
}
