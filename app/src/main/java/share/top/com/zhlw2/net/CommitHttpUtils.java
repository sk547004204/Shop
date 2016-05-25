package share.top.com.zhlw2.net;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import share.top.com.zhlw2.bean.ShopInfo;
import share.top.com.zhlw2.bean.User;

/**
 * Created by ZHOU on 2016/4/21.
 */
public class CommitHttpUtils {
    private static CommitHttpUtils utils;
    private User user;

    private CommitHttpUtils() {
        user = new User();
    }

    public static CommitHttpUtils getInstace() {
        if (utils == null) {
            utils = new CommitHttpUtils();
        }
        return utils;
    }

    /**
     * 根据当前的用户提交订单
     */
    public void requestByPost(String url, String name, ArrayList<ShopInfo> infos) {
        double zPrice = 0;
        double sumPrice = 0;
        URL urlpath;
        HttpURLConnection conn;
        if (name.equals(user.getUsername())) {
            for (ShopInfo info : infos) {
                zPrice = Double.parseDouble(info.getPrice_new());
                sumPrice += zPrice;
            }
            if (user.getVip() >= sumPrice) {
                //支付成功
                if (back != null) {
                    back.success("支付成功");
                    try {
                        urlpath = new URL(url);
                        conn = (HttpURLConnection) urlpath.openConnection();
                        conn.setDoOutput(true);
                        conn.setDoInput(true);
                        conn.setRequestMethod("POST");
                        conn.setUseCaches(false);
                        conn.setInstanceFollowRedirects(true);
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        conn.connect();
                        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                        String contentInfo = name + URLEncoder.encode(infos.toString(), "utf-8");
                        out.writeBytes(contentInfo);
                        out.flush();
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                //支付失败
                if (back != null) {
                    back.defeated("支付失败");
                }
            }
        }
    }

    private CommitCallBack back;

    public CommitCallBack getBack() {
        return back;
    }

    public void setBack(CommitCallBack back) {
        this.back = back;
    }

    public interface CommitCallBack {
        //支付成功
        void success(String result);

        //支付失败
        void defeated(String result);
    }

}
