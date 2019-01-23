package dharma.github.io.mvvmsample.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class CommonUtils {

    /**
     * Showing Progress Dialog
     *
     * @param context
     * @return {@link ProgressDialog}
     */
    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}
