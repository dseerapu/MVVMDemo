package dharma.github.io.mvvmsample.view.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageUtils {

    @BindingAdapter("imageUrl")
    public static void setImage(ImageView image, String imageUrl) {

        /**
         * Replace Image Library of your choice like Picasso, ImageLoading Library, Fresco etc...
         */
        Glide.with(image.getContext()).load(imageUrl).into(image);
    }
}
