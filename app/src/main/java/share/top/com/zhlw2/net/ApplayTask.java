package share.top.com.zhlw2.net;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import share.top.com.zhlw2.bean.ShopInfo;
import share.top.com.zhlw2.utils.HttpUtils;


/**
 * Created by ZHOU on 2016/4/24.
 */
public class ApplayTask extends AsyncTask<Void, Void, Boolean> {

    private String username;
    private ArrayList<ShopInfo> infos;
    private String url = "http://192.168.1.157:8080/JpushServer/servlet/UserServlet";
    private Context mContext;

    public ApplayTask(Context mContext, String username, ArrayList<ShopInfo> infos) {
        this.username = username;
        this.infos = infos;
        this.mContext = mContext;
    }


    @Override
    protected Boolean doInBackground(Void... params) {

        String content = HttpUtils.requestByPost(url, username, infos);
        Log.i("msg", "content = " + content);
        //通过标签和别名建立常连接
//        try {
//            if (content != null) {
//                JSONObject jo = new JSONObject(content);
//                String alias = jo.getString("alias");
//                String tag = jo.getString("tag");//用户可以用多别名
//                String tags[] = tag.split(",");
//                Set<String> set = new HashSet<>(Arrays.asList(tags));
//                //极光推送服务器才能根据别名和标签找到这个客户端
//                JPushInterface.setAliasAndTags(mContext, alias, set, callback);
//            } else {
//                Log.i("msg", "服务器访问超时！");
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        return null;
    }
}
