package dharma.github.io.mvvmsample.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import dharma.github.io.mvvmsample.R;
import dharma.github.io.mvvmsample.constants.Constants;
import dharma.github.io.mvvmsample.databinding.FullImageFragmentBinding;
import dharma.github.io.mvvmsample.view.base.BaseFragment;
import dharma.github.io.mvvmsample.viewmodel.GalleryViewModel;

public class FullImageFragment extends BaseFragment<GalleryViewModel, FullImageFragmentBinding> {

    @Override
    protected Class<GalleryViewModel> getViewModel() {
        return GalleryViewModel.class;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
