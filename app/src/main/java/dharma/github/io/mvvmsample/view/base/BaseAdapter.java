package dharma.github.io.mvvmsample.view.base;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Base Adapter for all the Adapter in this application {@link dharma.github.io.mvvmsample.MVVMSampleApp}
 *
 * @param <T>
 * @param <D>
 */
public abstract class BaseAdapter<T extends RecyclerView.ViewHolder, D> extends RecyclerView.Adapter<T> {

    public abstract void setData(List<D> data);

    public abstract void addNewData(List<D> newData);
}
