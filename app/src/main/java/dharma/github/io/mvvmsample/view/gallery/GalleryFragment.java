package dharma.github.io.mvvmsample.view.gallery;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import dharma.github.io.mvvmsample.R;
import dharma.github.io.mvvmsample.constants.Constants;
import dharma.github.io.mvvmsample.data.model.GalleryModel;
import dharma.github.io.mvvmsample.data.model.User;
import dharma.github.io.mvvmsample.databinding.MainFragmentBinding;
import dharma.github.io.mvvmsample.network.NetWorkReceiver;
import dharma.github.io.mvvmsample.network.NetworkListenerInterface;
import dharma.github.io.mvvmsample.utils.AppLogger;
import dharma.github.io.mvvmsample.view.base.BaseFragment;
import dharma.github.io.mvvmsample.view.fullimage.FullImageFragment;
import dharma.github.io.mvvmsample.view.utils.EndlessRecyclerView;
import dharma.github.io.mvvmsample.view.utils.FragmentUtils;
import dharma.github.io.mvvmsample.view.utils.OnItemClick;

public class GalleryFragment extends BaseFragment<GalleryViewModel, MainFragmentBinding>
        implements EndlessRecyclerView.OnLoadListener, OnItemClick<User>, NetworkListenerInterface {

    private static final String TAG = GalleryFragment.class.getSimpleName();

    @Inject
    GalleryAdapter galleryAdapter;

    @Inject
    GridLayoutManager gridLayoutManager;

    @Inject
    ViewModelProvider.Factory factory;

    private GalleryViewModel galleryViewModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        NetWorkReceiver.getInstance().setNetworkListenerInterface(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        NetWorkReceiver.getInstance().removeListener();
    }

    /**
     * Instance of GalleryFragment {@link GalleryFragment}
     *
     * @return
     */
    public static Fragment newInstance() {
        return new GalleryFragment();
    }

    @Override
    protected GalleryViewModel getViewModel() {

        galleryViewModel = ViewModelProviders.of(this, factory).get(GalleryViewModel.class);
        return galleryViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void initObservers() {

        galleryViewModel.getGalleryList().observe(this, this::getGalleryItemsWithUserAndProfileImage);
        galleryViewModel.registerForAllGalleriesLoaded().observe(this, this::allGalleryModelsLoaded);
    }

    private void allGalleryModelsLoaded(Void aVoid) {
        onItemsLoadedForFirstTime(galleryViewModel.getAllUsers());
    }

    private void getGalleryItemsWithUserAndProfileImage(List<GalleryModel> galleryModels) {
        galleryViewModel.loadAllUsers();
    }

    @Override
    public void setUp(View view) {

        dataBinding.recylerView.setLayoutManager(gridLayoutManager);

        galleryAdapter.setCallback(this);
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

        viewModel.getGalleryListForTheFirstTime();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.main_fragment;
    }


    public void onItemsLoadedForFirstTime(@NonNull List<User> usersList) {

        galleryAdapter.addInitialData(usersList);
        dataBinding.recylerView.setLoading(false);
    }

    public void onItemsLoaded(@NonNull List<User> galleryModels) {

        galleryAdapter.addNewData(galleryModels);
        dataBinding.recylerView.setLoading(false);
    }

    public void onItemsError(@NonNull Throwable throwable) {
        AppLogger.e(TAG, "onItemsError");
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
    public void onItemClicked(User user) {
        if (null != getActivity()) {
            String imageUrl = user.getProfileImage().getLarge();
            Bundle args = new Bundle();
            args.putString(Constants.IMAGE_URL, imageUrl);
            FullImageFragment detailFragment = new FullImageFragment();
            detailFragment.setArguments(args);
            FragmentUtils.replaceFragment((AppCompatActivity) getActivity(), detailFragment,
                    R.id.container, false, FragmentUtils.TRANSITION_SLIDE_LEFT_RIGHT);
        }
    }

    @Override
    public void isConnectedToWifi() {
        AppLogger.i("isConnectedToWifi");
    }

    @Override
    public void isConnectedToMobileNetwork() {
        AppLogger.i("isConnectedToMobileNetwork");
    }

    @Override
    public void isDisconnected() {
        AppLogger.i("isDisconnected");
    }
}
