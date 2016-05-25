package share.top.com.zhlw2.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

/**
 * Created by ZHOU on 2016/4/11.
 */
public class ProgressManager {
    private static ProgressManager manager;
    private Context mContext;
    private ProgressDialog bar;

    private ProgressManager(Context mContext) {
        this.mContext = mContext;
    }

    public static ProgressManager getInstance(Context mContext) {
        if (manager == null) {
            manager = new ProgressManager(mContext);
        }
        return manager;
    }

    public void show() {
        bar = new ProgressDialog(mContext);
        bar.setCancelable(true);
        bar.setMessage("加载中....");
        bar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        bar.show();
    }

    public void dismiss() {
        if (bar != null) {
            bar.dismiss();
        }
    }
}
