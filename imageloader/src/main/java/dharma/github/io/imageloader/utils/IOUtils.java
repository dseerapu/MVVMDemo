package dharma.github.io.imageloader.utils;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * IOUtils
 */
public class IOUtils {

	/**
	 * Save stream as file
	 * @param inputStream Input stream
	 * @param file File object
	 */
	public static void writeStreamToFile(InputStream inputStream, File file) {

		// Create an output stream for saving images
		BufferedOutputStream bufferedOutputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

		// Write data
		byte[] buffer = new byte[1024];
		int len = 0;
		try {
			while ((len = inputStream.read(buffer)) != -1) {
				bufferedOutputStream.write(buffer, 0, len);
			}
			bufferedOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Close flow
			if (bufferedOutputStream != null) {
				try {
					bufferedOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					bufferedOutputStream = null;
				}
			}
		}

	}

	/**
	 * Obtain the storage File object in the SD card cache directory
	 */
	public static File getDiskCacheFile(String filename, Context context) {
		return new File(getLocalCachePath(context), filename);
	}


	/**
	 * Get local cache location
	 */
	public static String getLocalCachePath(Context context) {

		// Sdcard location
		String cachePath = null;

		// SD exists using SD cache
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())
				|| !Environment.isExternalStorageRemovable()) {

			File externalCacheDir = context.getExternalCacheDir();
			if (externalCacheDir != null) {
				cachePath = externalCacheDir.getPath();
			}
		}

		if (cachePath == null) {
			cachePath =context.getCacheDir().getPath();
		}

		cachePath += "/httpcache";

		// If the folder does not exist, create a folder
		File dirFile = new File(cachePath);

		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		return cachePath;
	}

	/**
	 * Delete the files in the directory, non-recursive, do not delete the directory
	 *
	 * @param dirFile
	 *            Directory File object
	 * @return Successfully deleted
	 */
	public static boolean removeDir(File dirFile) {

		if (!dirFile.isDirectory()) {
			return false;
		}
		File files[] = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {

			if (files[i].isDirectory()) {
				break;
			}

			if (!files[i].delete()) {
				return false;
			}
		}
		return true;
	}

}