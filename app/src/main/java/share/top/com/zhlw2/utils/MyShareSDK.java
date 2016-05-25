package share.top.com.zhlw2.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.io.File;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;
import share.top.com.zhlw2.bean.EatInfo;
import share.top.com.zhlw2.bean.ShopInfo;

/**
 * Created by hpp on 2016/3/28.
 */
public class MyShareSDK {
    public void qqLogin(final Activity activity) {
        final Platform qq = ShareSDK.getPlatform(QQ.NAME);
        qq.SSOSetting(false);  //设置false表示使用SSO授权方式
        qq.setPlatformActionListener(new PlatformActionListener() {
            String username;
            String imageurl;


            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                username = qq.getDb().get("nickname");
                Log.e("aa", "获取到的name=" + username);
                imageurl = qq.getDb().getUserIcon();
                Log.e("aa", "获取到的icon=" + imageurl);
                Bundle bundle = new Bundle();
                bundle.putString("name", username);
                bundle.putString("url", imageurl);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                activity.setResult(101, intent);
                activity.finish();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        }); // 设置分享事件回调
        qq.authorize();
    }

    public void showShare(Context context, ShopInfo info, String sdPath) {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(info.getName());
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(info.getUrl());
        // text是分享文本，所有平台都需要这个字段
        oks.setText("特价：￥" + info.getPrice_new() + "  原价：" + info.getPrice_oid());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(sdPath);//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(info.getImg());
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("赞一个");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("Go够购");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(info.getUrl());
        // 启动分享GUI
        oks.show(context);
    }

    public void showShare1(Context context, EatInfo info, String sdPath) {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(info.getName());
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(info.getUrl());
        // text是分享文本，所有平台都需要这个字段
        oks.setText("特价：" + info.getPrice_new() + "  原价：" + info.getPrice_old());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(sdPath);//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(info.getImg());
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("赞一个");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("Go够购");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(info.getUrl());
        // 启动分享GUI
        oks.show(context);
    }
}
