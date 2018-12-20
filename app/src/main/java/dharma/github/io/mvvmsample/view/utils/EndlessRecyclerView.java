package dharma.github.io.mvvmsample.view.utils;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import dharma.github.io.mvvmsample.R;
import dharma.github.io.mvvmsample.databinding.ProgressItemBinding;

public class EndlessRecyclerView extends RecyclerView {
    private boolean mLoading = false;
    private boolean mLoadingEnabled = true;

    @Nullable
    private OnLoadListener mOnLoadListener;

    private int totalItemCount;
    private int lastVisibleItem;
    private int visibleThreshold = 5;

    public interface OnLoadListener {
        void onLoad();
    }

    public EndlessRecyclerView(Context context) {
        this(context, null);
    }

    public EndlessRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EndlessRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnLoadListener(@Nullable OnLoadListener onLoadListener) {
        mOnLoadListener = onLoadListener;
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);

        if (mLoadingEnabled && !mLoading) {
            LayoutManager layoutManager = getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                if (gridLayoutManager.findLastVisibleItemPosition() / gridLayoutManager.getSpanCount()
                        == (gridLayoutManager.getItemCount() - 1) / gridLayoutManager.getSpanCount()) {
                    setLoading(true, true);
                }
            } else if (layoutManager instanceof LinearLayoutManager) {
                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                if (totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    setLoading(true, true);
                }
            }
        }
    }

    public void setLoading(boolean loading) {
        setLoading(loading, false);
    }

    public void setLoadingEnabled(boolean loadingEnabled) {
        if (mLoadingEnabled != loadingEnabled) {
            mLoadingEnabled = loadingEnabled;

            Adapter adapter = (Adapter) getAdapter();
            adapter.progressVisibility = mLoadingEnabled ? VISIBLE : GONE;

            if (adapter.progressVisibility == VISIBLE) {
                getAdapter().notifyItemInserted(adapter.getItemCount());
            } else {
                getAdapter().notifyItemRemoved(adapter.getItemCount());
            }
        }
    }

    public void setNoMoreItems() {
        Adapter adapter = (Adapter) getAdapter();
        getAdapter().notifyItemRemoved(adapter.getItemCount() - 1);
    }

    private void setLoading(boolean loading, boolean notify) {
        mLoading = loading;

        if (notify && mOnLoadListener != null) {
            mOnLoadListener.onLoad();
        }
    }

    public abstract static class Adapter<VH extends ViewHolder> extends RecyclerView.Adapter<VH> {
        private static final int VIEW_TYPE_PROGRESS = ProgressViewHolder.class.hashCode();

        private int progressVisibility = VISIBLE;

        @Override
        public final VH onCreateViewHolder(ViewGroup parent, int viewType) {

            if (viewType == VIEW_TYPE_PROGRESS) {

                ProgressItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.progress_item, parent, false);
                return (VH) new ProgressViewHolder(binding);
            } else {
                return onCreateDataViewHolder(parent, viewType);
            }
        }

        @Override
        public final void onBindViewHolder(VH holder, int position) {
            if (!(holder instanceof ProgressViewHolder)) {
                onBindDataViewHolder(holder, position);
            }
        }

        @Override
        public final int getItemViewType(int position) {
            if (position < getDataItemCount()) {
                return super.getItemViewType(position);
            } else {
                return VIEW_TYPE_PROGRESS;
            }
        }

        @Override
        public final int getItemCount() {
            if (getDataItemCount() == 0) {
                return 0;
            } else {
                if (progressVisibility == VISIBLE) {
                    return getDataItemCount() + 1;
                } else {
                    return getDataItemCount();
                }
            }
        }

        public abstract VH onCreateDataViewHolder(ViewGroup parent, int viewType);

        public abstract void onBindDataViewHolder(VH holder, int position);

        public abstract int getDataItemCount();

        public abstract int getDataItemViewType();
    }

    private static class ProgressViewHolder extends ViewHolder {

        ProgressViewHolder(ProgressItemBinding itemView) {
            super(itemView.getRoot());
        }
    }
}