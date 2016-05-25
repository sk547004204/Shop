package share.top.com.zhlw2.fragment;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.readystatesoftware.viewbadger.BadgeView;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import share.top.com.zhlw2.MainActivity;
import share.top.com.zhlw2.R;
import share.top.com.zhlw2.activity.LoginActivity;
import share.top.com.zhlw2.activity.MoreActivity;
import share.top.com.zhlw2.activity.MoreActivity;
import share.top.com.zhlw2.bean.ShopInfo;
import share.top.com.zhlw2.db.MyDBManager;
import share.top.com.zhlw2.utils.MyShareSDK;
import share.top.com.zhlw2.utils.ShopConnUtil;
import share.top.com.zhlw2.view.MyGridView;

/**
 * Created by ZHOU on 2016/4/8.
 */
public class MeFragment extends BaseFragment implements View.OnTouchListener, View.OnClickListener {
    private View view;
    private MyGridView meGridView;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    private SimpleAdapter adapter;
    private MyGridView mGridViewFooter;
    private List<Map<String, Object>> Nameslist;
    private View me_more;
    private View me_dfk;
    private View me_dfh;
    private View me_dsh;
    private View me_dpj;
    private View me_tk;
    private View user;
    private TextView username2;
    CircleImageView usericon;
    private View more_dfk;
    MyShareSDK share = new MyShareSDK();
    private DisplayImageOptions options;
    // 图片封装为一个数组
    private int[] icon = {R.mipmap.grid1, R.mipmap.grid11,
            R.mipmap.grid7, R.mipmap.grid4, R.mipmap.grid5,
            R.mipmap.grid6, R.mipmap.grid2, R.mipmap.grid8,
            R.mipmap.grid9, R.mipmap.grid10, R.mipmap.grid3,
            R.mipmap.grid12};
    //新建适配器
    String[] from = {"griditemimage", "griditemtext"};
    int[] to = {R.id.griditemimage, R.id.griditemtext};
    private String[] froms = {"image", "text"};
    private String[] names = {"车来了", "饿了么", "聚划算", "去哪儿", "淘宝", "支付宝"};
    private int[] icons = {R.mipmap.xz_chelaile, R.mipmap.xz_eleme,
            R.mipmap.xz_juhuasuan, R.mipmap.xz_qunawang, R.mipmap.xz_taobao, R.mipmap.xz_zhifubao,};
    private String[] iconName = {"收藏宝贝", "分享", "购币", "足迹", "卡卷", "物流", "收藏店铺",
            "通知", "消息", "会员", "内容", "开店"};

    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mefragment_layout, null);
        options = new DisplayImageOptions.Builder().cacheInMemory(true).showStubImage(R.mipmap.logo2)// 设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.logo2)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.logo2)// 设置图片加载/解码过程中错误时候显示的图片
                .showImageOnLoading(R.mipmap.logo2)
                .cacheOnDisk(true).imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        return view;
    }

    public List<Map<String, Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("griditemimage", icon[i]);
            map.put("griditemtext", iconName[i]);
            data_list.add(map);
        }
        return data_list;
    }

    public List<Map<String, Object>> getNamesData() {
        for (int i = 0; i < icons.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", icons[i]);
            map.put("text", names[i]);
            Nameslist.add(map);
        }
        return Nameslist;
    }

    @Override
    public void initView() {
        more_dfk= view.findViewById(R.id.me_dfk);
        username2 = (TextView) view.findViewById(R.id.username1);
        usericon = (CircleImageView) view.findViewById(R.id.usericon);
        meGridView = (MyGridView) view.findViewById(R.id.meGridView);
        mGridViewFooter = (MyGridView) view.findViewById(R.id.meGridViewFooter);
        me_more = view.findViewById(R.id.me_more);
        me_dfk = view.findViewById(R.id.me_dfk);
        me_dfh = view.findViewById(R.id.me_dfh);
        me_dsh = view.findViewById(R.id.me_dsh);
        me_dpj = view.findViewById(R.id.me_dpj);
        me_tk = view.findViewById(R.id.me_tk);
        me_dfk.setOnClickListener(this);
        me_dfh.setOnClickListener(this);
        me_dsh.setOnClickListener(this);
        me_dpj.setOnClickListener(this);
        me_tk.setOnClickListener(this);
        me_more.setOnClickListener(this);
        user = view.findViewById(R.id.user);
        user.setOnClickListener(this);
    }

    @Override
    public void before() {

    }

    boolean flag = true;
    private MainActivity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (MainActivity) activity;
        mActivity.setHandler(mHandler);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if (username2 != null) {
                        String name = msg.getData().getString("username");
                        String headimage = msg.getData().getString("headimage");
                        username2.setText(name);
                        Log.i("aa", "MeFragment 中拿到的图片地址" + headimage);
                        ImageLoader.getInstance().displayImage(headimage, usericon, options);
                    }
                    break;
            }
        }
    };
    @Override
    public void after() {
        showItem();
        final NotificationManager nm = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        data_list = new ArrayList<>();
        getData();
        sim_adapter = new SimpleAdapter(getActivity(), data_list, R.layout.griditem_layout, from, to);
        meGridView.setAdapter(sim_adapter);
        meGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(view, iconName[position] + "  正在开发", Snackbar.LENGTH_SHORT).show();
            }
        });
        Nameslist = new ArrayList<>();
        getNamesData();
        adapter = new SimpleAdapter(getActivity(), Nameslist, R.layout.griditem_layout, froms, to);
        mGridViewFooter.setAdapter(adapter);
        mGridViewFooter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("提示");
                dialog.setMessage("是否下载" + names[position]);
                dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (flag) {
                            flag = false;
                            Toast.makeText(getActivity(), names[position] + " 准备下载", Toast.LENGTH_SHORT).show();
                            final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getContext());
                            String url = null;
                            switch (position) {
                                case 0:
                                    url = "http://2295.com/hfxbnn";
                                    break;
                                case 1:
                                    url = "http://static10.elemecdn.com/uploads/androidapp/eleme5_9.apk";
                                    break;
                                case 2:
                                    url = "http://dxa.newhua.com/down/701126juhuasuan_android_4.5.1.apk";
                                    break;
                                case 3:
                                    url = "http://m.qunar.com/download/Qunar_android_v6.apk";
                                    break;
                                case 4:
                                    url = "http://more.tianjimedia.com/soft/down.jsp?id=11524673&f=official&path=http://download.alicdn.com/wireless/taobao4android/latest/702757.apk";
                                    break;
                                case 5:
                                    url = "http://tfs.alipayobjects.com/L1/71/100/and/alipay_wap_main.apk";
                                    break;
                            }
                            final String sdPath = "/sdcard/Download/" + names[position] + ".apk";
                            HttpUtils http = new HttpUtils();
                            HttpHandler handler = http.download(url, sdPath, true, false,
                                    new RequestCallBack<File>() {
                                        @SuppressWarnings("deprecation")
                                        @Override
                                        public void onStart() {
                                            Log.e("aa", "正在连接。。。");
                                            Toast.makeText(getActivity(), names[position] + " 开始连接服务器后台下载", Toast.LENGTH_SHORT).show();
                                        }

                                        int i = 0;
                                        boolean isStart = true;

                                        @Override
                                        public void onLoading(long total, long current,
                                                              boolean isUploading) {
                                            super.onLoading(total, current, isUploading);
                                            if (isStart) {
                                                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                                Ringtone r = RingtoneManager.getRingtone(getActivity(), notification);
                                                r.play();
                                                isStart = false;
                                                Toast.makeText(getActivity(), names[position] + " 后台开始下载", Toast.LENGTH_SHORT).show();
                                                Log.e("aa", "执行一次");
                                            }
                                            Log.e("aa", "正在下载。" + i);
                                            i++;
                                            mBuilder.setTicker("开始下载");
                                            mBuilder.setAutoCancel(true);
                                            mBuilder.setWhen(System.currentTimeMillis());
                                            mBuilder.setDefaults(Notification.DEFAULT_LIGHTS);
                                            mBuilder.setContentTitle("正在下载" + (int) ((double) current
                                                    / (double) total * 100) + "%");
                                            mBuilder.setOngoing(false);
                                            Notification notification = mBuilder.build();
                                            notification.flags = Notification.FLAG_AUTO_CANCEL;
                                            mBuilder.setContentText(names[position]);
                                            mBuilder.setSmallIcon(icons[position]);
                                            mBuilder.setProgress(100, (int) ((double) current
                                                    / (double) total * 100), false);
                                            nm.notify(4, mBuilder.build());
                                        }

                                        @Override
                                        public void onSuccess(ResponseInfo<File> responseInfo) {
                                            Log.e("aa", responseInfo.result.getPath());
                                            Snackbar.make(view, names[position] + " 下载完毕", Snackbar.LENGTH_SHORT).show();
                                            i = 0;
                                            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                            Ringtone r = RingtoneManager.getRingtone(getActivity(), notification);
                                            r.play();
                                            Log.e("aa", "路径=" + sdPath);
                                            installApk(sdPath);
                                            flag = true;
                                        }

                                        @Override
                                        public void onFailure(HttpException error, String msg) {
                                            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                            Ringtone r = RingtoneManager.getRingtone(getActivity(), notification);
                                            r.play();
                                            flag = true;
                                            Log.e("aa", msg);
                                            if (msg.equals("maybe the file has downloaded completely")) {
                                                Snackbar.make(view, "程序已下载", Snackbar.LENGTH_SHORT).show();
                                            }
                                            File file = new File(sdPath);

                                            Toast.makeText(getActivity(), "下载异常" + msg, Toast.LENGTH_SHORT).show();
                                            Log.e("aa", "暂停");
                                        }
                                    });
                        } else {
                            Snackbar.make(view, "有程序正在下载", Snackbar.LENGTH_SHORT).show();

                        }
                    }

                });
                dialog.setNeutralButton("否", null);
                dialog.show();
            }
        });
    }

    private void installApk(String url) {
        File file = new File(url);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    public void getUserInfo(String user) {
        Log.i("msg", "meFragment user = " + user);
        this.users = user;
    }

    private String users;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }


    //点击事件
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), MoreActivity.class);
        switch (v.getId()) {

            case R.id.me_dfh:
                Toast.makeText(getContext(), "待发货正在开发中", Toast.LENGTH_SHORT).show();
                intent.putExtra("name", 2);
                startActivity(intent);
                break;
            case R.id.me_dfk:
                intent.putExtra("name", 1);
                startActivity(intent);
                Toast.makeText(getContext(), "待付款正在开发中", Toast.LENGTH_SHORT).show();
                break;
            case R.id.me_dpj:
                intent.putExtra("name", 4);
                startActivity(intent);
                Toast.makeText(getContext(), "待评价正在开发中", Toast.LENGTH_SHORT).show();
                break;

            case R.id.me_dsh:
                intent.putExtra("name", 3);
                startActivity(intent);
                Toast.makeText(getContext(), "待收货正在开发中", Toast.LENGTH_SHORT).show();
                break;
            case R.id.me_tk:
                intent.putExtra("name", 5);
                startActivity(intent);
                Toast.makeText(getContext(), "退款正在开发中", Toast.LENGTH_SHORT).show();
                break;
            case R.id.me_more:
                startActivity(intent);
                Toast.makeText(getContext(), "更多订单正在开发中", Toast.LENGTH_SHORT).show();
                break;
            case R.id.user:
                startActivityForResult(new Intent(getActivity(), LoginActivity.class), 1);
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden==false){
            showItem();
        }
    }

    public void showItem(){
        final BadgeView badgeView = new BadgeView(getActivity(), more_dfk);
        MyDBManager dbManager = MyDBManager.getInstance(getActivity());
        ArrayList<ShopInfo> list = dbManager.getContent();
        if (list.size() != 0) {
            badgeView.setText(list.size() + "");
            badgeView.setBadgePosition(badgeView.POSITION_TOP_RIGHT);
            badgeView.setTextSize(20);
            badgeView.show();
        } else {
            badgeView.setVisibility(View.GONE);
        }
    }
}
