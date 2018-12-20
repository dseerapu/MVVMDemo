package dharma.github.io.mvvmsample.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import dharma.github.io.mvvmsample.R;
import dharma.github.io.mvvmsample.constants.Constants;
import dharma.github.io.mvvmsample.data.model.GalleryModel;
import dharma.github.io.mvvmsample.databinding.MainFragmentBinding;
import dharma.github.io.mvvmsample.view.base.BaseFragment;
import dharma.github.io.mvvmsample.view.utils.EndlessRecyclerView;
import dharma.github.io.mvvmsample.view.utils.FragmentUtils;
import dharma.github.io.mvvmsample.view.utils.OnItemClick;
import dharma.github.io.mvvmsample.viewmodel.GalleryModelsCallback;
import dharma.github.io.mvvmsample.viewmodel.GalleryViewModel;

public class MainFragment extends BaseFragment<GalleryViewModel, MainFragmentBinding> implements GalleryModelsCallback, EndlessRecyclerView.OnLoadListener, OnItemClick<GalleryModel> {

    private static final String TAG = MainFragment.class.getSimpleName();
    private GalleryAdapter galleryAdapter;

    /**
     * Instance of MainFragment {@link MainFragment}
     *
     * @return
     */
    public static Fragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected Class<GalleryViewModel> getViewModel() {
        return GalleryViewModel.class;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        initViews();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel.addGalleryItemsCallback(this);
        viewModel.getGalleryList();

    }

    private void initViews() {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        dataBinding.recylerView.setLayoutManager(manager);
        galleryAdapter = new GalleryAdapter(this);
        dataBinding.recylerView.setAdapter(galleryAdapter);
        dataBinding.recylerView.setOnLoadListener(this);

        /*
         * For handle pull down refresh list
         */
        dataBinding.swiperefresh.setOnRefreshListener(() -> {
            galleryAdapter.removeWholeData();
            viewModel.getGalleryListForTheFirstTime();
            dataBinding.swiperefresh.setRefreshing(false);
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.main_fragment;
    }

    @Override
    public void onItemsLoadedForFirstTime(@NonNull List<GalleryModel> galleryModels) {
        Log.e(TAG, "onItemsLoadedForFirstTime");

        galleryAdapter.addInitialData(galleryModels);
        dataBinding.recylerView.setLoading(false);
    }

    @Override
    public void onItemsLoaded(@NonNull List<GalleryModel> galleryModels) {
        Log.e(TAG, "onItemsLoaded");
        galleryAdapter.addNewData(galleryModels);
        dataBinding.recylerView.setLoading(false);
    }

    @Override
    public void onItemsError(@NonNull Throwable throwable) {
        Log.e(TAG, "onItemsError");
    }

    @Override
    public void onLoad() {
        viewModel.loadMoreGalleryItems();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.action_delete:
                galleryAdapter.removeWholeData();
                dataBinding.recylerView.setLoading(true);
                break;
        }
        return super.onOptionsItemSelected(item);


    }

    @Override
    public void onItemClicked(GalleryModel galleryModel) {
        if (null != getActivity()) {
            String imageUrl = galleryModel.getUser().getProfileImage().getLarge();
            Bundle args = new Bundle();
            args.putString(Constants.IMAGE_URL, imageUrl);
            FullImageFragment detailFragment = new FullImageFragment();
            detailFragment.setArguments(args);
            FragmentUtils.replaceFragment((AppCompatActivity) getActivity(), detailFragment, R.id.container, true, FragmentUtils.TRANSITION_SLIDE_LEFT_RIGHT);
        }
    }
}
