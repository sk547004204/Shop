package share.top.com.zhlw2.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ZHOU on 2016/4/7.
 */
public class ShareUtils {

    private static ShareUtils utils;
    private Context mContext;

    private ShareUtils(Context mContext) {
        this.mContext = mContext;
    }

    public static ShareUtils getInstance(Context mContext) {
        if (utils == null) {
            utils = new ShareUtils(mContext);
        }
        return utils;
    }

    //读取数据
    public int getUserData() {
        SharedPreferences sp = mContext.getSharedPreferences("saveData", 0);
        int version = sp.getInt("verison", 0);
        return version;
    }

    //保存数据
    public void SaveData(int version) {
        SharedPreferences sp = mContext.getSharedPreferences("saveData", 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("version", version);
        edit.commit();
    }
}
