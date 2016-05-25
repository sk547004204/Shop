package share.top.com.zhlw2.utils;


import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import share.top.com.zhlw2.bean.ShopInfo;


/**
 * Created by ZHOU on 2016/4/24.
 */
public class HttpUtils {

    public static String requestByPost(String url, String username, ArrayList<ShopInfo> info) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            //提交的数据
            Gson gson = new Gson();
            String infoStr = gson.toJson(info);
            String shopinfos = "{ code:200,shopinfo:" + infoStr + "}";
            List<BasicNameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("infoStr", shopinfos));
            //参数作为表单提交
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "utf-8");
            post.setEntity(entity);
            //执行请求
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                //获取服务器响应的文本
                String content = EntityUtils.toString(response.getEntity());
                return content;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String HttpByPost(String url, String username, String pass) {
        try {
            String uu = url + "?username=" + username + "&password=" + pass;
            URL pathUrl = new URL(uu);
            HttpURLConnection conn = (HttpURLConnection) pathUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setDoOutput(true);
            conn.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String content = null;
            StringBuffer sb = new StringBuffer();
            while ((content = reader.readLine()) != null) {
                sb.append(content);
                sb.append("\r\n");
            }
            reader.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
