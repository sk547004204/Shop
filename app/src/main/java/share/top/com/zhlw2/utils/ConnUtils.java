package share.top.com.zhlw2.utils;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by ZHOU on 2016/4/7.
 */
public class ConnUtils {
    private static ConnUtils utils;
    private Context mContext;
    private RequestQueue queue;
    //服务器地址
    public static String versionPath = "http://192.168.1.100:8080/MyWeb/appvesion.html";

    private ConnUtils(Context mContext) {
        this.mContext = mContext;
        queue = Volley.newRequestQueue(mContext);
    }

    public static ConnUtils getInstace(Context mContext) {
        if (utils == null) {
            utils = new ConnUtils(mContext);
        }
        return utils;
    }

    //获取数据
    public void getHttpData(String url, final INetWork iNetWork) {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (iNetWork == null) return;
                if (TextUtils.isEmpty(response)) {
                    iNetWork.onNetError(new VolleyError("Empty Body", new Throwable("Empty Body")));
                    return;
                }
                iNetWork.onNetSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (iNetWork != null) {
                    iNetWork.onNetError(error);
                }
            }
        });
        queue.add(request);
    }

    public interface INetWork {
        void onNetSuccess(String response);//请求成功的回调函数

        void onNetError(VolleyError error);//请求失败的回调函数
    }

    //解析数据
    public <T> T parseJson(String result, Class<T> clazz) {
        T t = null;
        try {
            t = JSON.parseObject(result, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

}
