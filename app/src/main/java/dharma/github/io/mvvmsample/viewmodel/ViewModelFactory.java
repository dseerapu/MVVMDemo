package dharma.github.io.mvvmsample.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 * Developed by Dharma Sai Seerapu on 19th Dec 2018
 * Added Common ViewModelFactory
 */
@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> factors;

    @Inject
    public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> factors) {
        this.factors = factors;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        Provider<? extends ViewModel> creator = factors.get(modelClass);

        if (creator == null) {
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : factors.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }

        if (creator == null) {
            throw new IllegalArgumentException("Unknown class :\t" + modelClass);
        }

        try {

            return (T) creator.get();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
