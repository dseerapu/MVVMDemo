package dharma.github.io.mvvmsample.view.base;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Base Adapter for all the Adapter in this application {@link dharma.github.io.mvvmsample.MVVMSampleApp}
 *
 * @param <T>
 * @param <Item>
 */
public abstract class BaseAdapter<T extends RecyclerView.ViewHolder, Item> extends RecyclerView.Adapter<T> {

    public List<Item> listItems;

    public abstract void addInitialData(List<Item> data);

    public abstract void addNewData(List<Item> newData);
}
