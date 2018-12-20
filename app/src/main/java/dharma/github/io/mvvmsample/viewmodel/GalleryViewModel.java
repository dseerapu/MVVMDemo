package dharma.github.io.mvvmsample.viewmodel;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dharma.github.io.mvvmsample.data.ApiService;
import dharma.github.io.mvvmsample.data.model.GalleryModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class GalleryViewModel extends ViewModel {

    private List<GalleryModel> galleryList;

}
