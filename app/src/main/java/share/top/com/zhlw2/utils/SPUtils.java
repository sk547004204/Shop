package share.top.com.zhlw2.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * Created by ZHOU on 2016/3/31.
 */
public class SPUtils {

    private Context mContext;

    public SPUtils(Context mContext) {
        this.mContext = mContext;
    }

    public void showSanckbar(View view, String str) {
        Snackbar.make(view, str, Snackbar.LENGTH_SHORT).show();
    }

    public void showPopwindow(View view, int layout, int widegt) {
        View popview = LayoutInflater.from(mContext).inflate(layout, null);
        /**
         * 1.popwindow需要的布局
         * 2。popwindow需要的放置大小及位置
         * 3.popwindow可设置进入和退出动画
         */
        PopupWindow popupWindow = new PopupWindow(popview, ViewGroup.LayoutParams.MATCH_PARENT, 300);
        ColorDrawable dw = new ColorDrawable(0xffccbb00);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setFocusable(true);//设置焦点
        popupWindow.setOutsideTouchable(true);//设置外部可触摸
        popupWindow.showAtLocation(view.findViewById(widegt), Gravity.BOTTOM, 0, 0);
    }
}
