package dharma.github.io.mvvmsample.view.base;

import java.util.ArrayList;
import java.util.List;

import dharma.github.io.mvvmsample.view.utils.EndlessRecyclerView;

/**
 * Base Adapter for all the Adapters in this application {@link dharma.github.io.mvvmsample.MVVMSampleApp}
 *
 * @param <T>
 * @param <D>
 */
public abstract class EndlessItemsBaseAdapter<T extends EndlessRecyclerView.ViewHolder, D> extends EndlessRecyclerView.Adapter<T> {

    public List<D> listItems;

    public void addInitialData(List<D> data) {
        listItems = new ArrayList<>();
        listItems.addAll(new ArrayList<>(data));
        notifyDataSetChanged();
    }

    public void addNewData(List<D> newData) {
        listItems.addAll(new ArrayList<>(newData));
        notifyDataSetChanged();
    }

    public void removeWholeData() {
        listItems.clear();
        notifyDataSetChanged();
    }

    public D getItem(int position) {
        if (listItems == null || listItems.isEmpty()) {
            return null;
        }

        return listItems.get(position);
    }
}
