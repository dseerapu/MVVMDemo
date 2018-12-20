package dharma.github.io.imageloader.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.InputStream;

/**
 * Compressed bitmap tool class
 */
public class ImageCompress {

    /**
     * Automatically compress images according to View width and height
     *
     * @param viewHeight View height
     * @param viewWidth  View width
     * @return Bitmap Compressed image object
     */
    public static Bitmap getImage(File imageFile, int viewHeight, int viewWidth) {

        BitmapFactory.Options options = new BitmapFactory.Options();

        // Calculate picture length and width
        options.inJustDecodeBounds = true;

        if (imageFile != null) {
            BitmapFactory.decodeFile(imageFile.getPath(), options);
        }

        int height = options.outHeight;
        int width = options.outWidth;

        calculateSampleSize(height, width, viewHeight, viewWidth, options);

        if (imageFile != null) {
            return BitmapFactory.decodeFile(imageFile.getPath(), options);
        }
        return null;

    }

    public static Bitmap getImage(InputStream imageStream, int viewHeight, int viewWidth) {

        BitmapFactory.Options options = new BitmapFactory.Options();

        // Calculate picture length and width
        options.inJustDecodeBounds = true;

        if (imageStream != null) {
            BitmapFactory.decodeStream(imageStream, null, options);
        }

        int height = options.outHeight;
        int width = options.outWidth;

        calculateSampleSize(height, width, viewHeight, viewWidth, options);

        if (imageStream != null) {
            return BitmapFactory.decodeStream(imageStream, null, options);
        } else {
            return null;
        }

    }

    private static void calculateSampleSize(int height, int width, int viewHeight, int viewWidth, BitmapFactory.Options options) {
        // Default is not scaled
        int compressSampleSize = 1;

        if (height > 0 && width > 0) {//Prevent 0x0
            // Calculate the optimal sample size based on length and width
            if (height > viewHeight || width > viewWidth) {
                int heightRadio = 1;
                int widthRadio = 1;
                if (viewHeight > 0) {
                    heightRadio = height / viewHeight;
                }
                if (viewWidth > 0) {
                    widthRadio = width / viewWidth;
                }
                compressSampleSize = heightRadio > widthRadio ? heightRadio : widthRadio;
            }
        }

        options.inSampleSize = compressSampleSize;
        // options.inPreferredConfig = Bitmap.Config.RGB_565;

        // Really parsing Bitmap
        options.inJustDecodeBounds = false;
    }

}