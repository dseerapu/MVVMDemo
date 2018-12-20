package dharma.github.io.mvvmsample.view;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import dharma.github.io.mvvmsample.R;
import dharma.github.io.mvvmsample.data.model.GalleryModel;
import dharma.github.io.mvvmsample.databinding.GalleryItemBinding;
import dharma.github.io.mvvmsample.view.base.EndlessItemsBaseAdapter;
import dharma.github.io.mvvmsample.view.utils.OnItemClick;

public class GalleryAdapter extends EndlessItemsBaseAdapter<GalleryAdapter.GalleryViewHolder, GalleryModel> {

    private OnItemClick<GalleryModel> onItemClick;

    public GalleryAdapter(OnItemClick<GalleryModel> onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public GalleryViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {

        GalleryItemBinding galleryItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.gallery_item, parent, false);

        return new GalleryViewHolder(galleryItemBinding);
    }

    @Override
    public void onBindDataViewHolder(GalleryViewHolder holder, int position) {
        GalleryModel galleryModel = getItem(position);
        if (galleryModel != null) {
            holder.bindItem(galleryModel, onItemClick);
        }
    }

    @Override
    public int getDataItemCount() {
        return listItems == null ? 0 : listItems.size();
    }

    @Override
    public int getDataItemViewType() {
        return 2;
    }

    static final class GalleryViewHolder extends RecyclerView.ViewHolder {

        private GalleryItemBinding binding;

        public GalleryViewHolder(@NonNull GalleryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindItem(GalleryModel galleryModel, OnItemClick<GalleryModel> onItemClick) {
            binding.setGalleryItem(galleryModel);

            binding.layoutItem.setOnClickListener(v -> onItemClick.onItemClicked(galleryModel));
        }
    }
}
