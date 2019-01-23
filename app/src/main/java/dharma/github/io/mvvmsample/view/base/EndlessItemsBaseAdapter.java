package dharma.github.io.mvvmsample.view.base;

import java.util.ArrayList;
import java.util.List;

import dharma.github.io.mvvmsample.view.utils.EndlessRecyclerView;

/**
 * Base Adapter for all the Adapters in this application {@link dharma.github.io.mvvmsample.MVVMSampleApp}
 *
 * @param <T>
 * @param <Item>
 */
public abstract class EndlessItemsBaseAdapter<T extends EndlessRecyclerView.ViewHolder, Item> extends EndlessRecyclerView.Adapter<T> {

    public List<Item> listItems;

    public void addInitialData(List<Item> data) {
        listItems = new ArrayList<>();
        listItems.addAll(new ArrayList<>(data));
        notifyDataSetChanged();
    }

    public void addNewData(List<Item> newData) {
        listItems.addAll(new ArrayList<>(newData));
        notifyDataSetChanged();
    }

    public void removeWholeData() {
        if (listItems == null) {
            return;
        }

        listItems.clear();
        notifyDataSetChanged();
    }

    public Item getItem(int position) {
        if (listItems == null || listItems.isEmpty()) {
            return null;
        }

        return listItems.get(position);
    }
}
