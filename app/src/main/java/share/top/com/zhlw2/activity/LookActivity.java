package share.top.com.zhlw2.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.io.File;
import java.util.ArrayList;

import share.top.com.zhlw2.R;
import share.top.com.zhlw2.adapter.LookAdapter;
import share.top.com.zhlw2.bean.EatInfo;
import share.top.com.zhlw2.bean.ShopCellInfo;
import share.top.com.zhlw2.bean.ShopInfo;
import share.top.com.zhlw2.db.MyDBManager;
import share.top.com.zhlw2.net.ShopData;
import share.top.com.zhlw2.utils.MyShareSDK;
import share.top.com.zhlw2.utils.SPUtils;

public class LookActivity extends BaseActivity implements View.OnClickListener {

    private TextView adress;
    private LookAdapter adapter;
    private LinearLayout lookLinearLayout;
    private ViewPager lookViewPager;
    private ImageView lookshopcar, lookshopmore, lookgoback;
    private ProgressDialog bar;
    private ShopData data;
    private SPUtils utils;
    private SimpleDraweeView[] imags;
    private FloatingActionsMenu floatMenu;
    private FloatingActionButton floatbtnSave, floatbtnShopCar, floatbtnShop;
    private TextView lookshopName, lookoldPrice, looknewPrice;
    private MyDBManager DBmanager;
    private View fenxiang;
    static int a = 0;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_look);
    }

    @Override
    public void beforeView() {

    }

    @Override
    public void initView() {
        fenxiang = findViewById(R.id.fenxiang);
        fenxiang.setOnClickListener(this);
        DBmanager = MyDBManager.getInstance(this);
        adress = (TextView) findViewById(R.id.adress);
        lookoldPrice = (TextView) findViewById(R.id.lookoldPrice);
        looknewPrice = (TextView) findViewById(R.id.looknewPrice);
        lookLinearLayout = (LinearLayout) findViewById(R.id.lookLinearLayout);
        lookshopName = (TextView) findViewById(R.id.lookshopName);
        floatbtnSave = (FloatingActionButton) findViewById(R.id.floatbtnSave);
        floatbtnSave.setOnClickListener(this);
        floatbtnShop = (FloatingActionButton) findViewById(R.id.floatbtnShop);
        floatbtnShopCar = (FloatingActionButton) findViewById(R.id.floatbtnShopCar);
        floatbtnShopCar.setOnClickListener(this);
        floatbtnShop.setOnClickListener(this);
        floatMenu = (FloatingActionsMenu) findViewById(R.id.floatMenu);
        lookViewPager = (ViewPager) findViewById(R.id.lookViewPager);
        lookshopcar = (ImageView) findViewById(R.id.lookshopcar);
        lookshopcar.setOnClickListener(this);
        lookshopmore = (ImageView) findViewById(R.id.lookshopmore);
        lookshopmore.setOnClickListener(this);
        lookgoback = (ImageView) findViewById(R.id.lookgoback);
        lookgoback.setOnClickListener(this);
        options = new DisplayImageOptions.Builder().cacheInMemory(true).showStubImage(R.mipmap.logo2)// 设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.logo2)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.logo2)// 设置图片加载/解码过程中错误时候显示的图片
                .showImageOnLoading(R.mipmap.logo2)
                .cacheOnDisk(true).imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    private ShopInfo info;
    private int index;
    private EatInfo eatInfo;

    private boolean isadd = true;

    @Override
    public void afterView() {
        getSQLData();
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                Message ms = new Message();
                ms.obj = DBmanager.lookIsHave();
                ms.what = 3;
                mHandler.sendMessage(ms);

            }
        });
        th.setDaemon(true);
        th.start();
        data = ShopData.getInstance(this);
        adapter = new LookAdapter(this);
        bar = new ProgressDialog(this);
        utils = new SPUtils(this);
        bar.setCancelable(true);
        bar.setMessage("加载中....");
        bar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        bar.show();
        Bundle bundle = this.getIntent().getExtras();
        index = bundle.getInt("eat");
        if (index == 2) {
            info = (ShopInfo) bundle.getSerializable("key");
            loading(info.getUrl());
            adapter.getIndex(2);
        } else {
            eatInfo = (EatInfo) bundle.getSerializable("key");
            adapter.getIndex(1);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    initLists();
                    mHandler.sendEmptyMessage(2);
                }
            }).start();
        }
    }

    private void loading(String url) {
        if (index == 2) {
            data.getSellInfoData(url, new ShopData.SellInfoCallBack() {
                @Override
                public void sellInfoData(ShopCellInfo info) {
                    getData(info);
                }
            });
        }
    }

    private ShopCellInfo infos;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                initList();
            } else if (msg.what == 2) {
                bar.dismiss();
                utils.showSanckbar(lookViewPager, "加载成功！");
            } else if (msg.what == 3) {
                list = (ArrayList<String>) msg.obj;
            }
            bar.dismiss();
        }
    };

    private ArrayList<SimpleDraweeView> imageList;
    private ArrayList<String> urlList;

    private void initLists() {
        urlList = new ArrayList<>();
        urlList.add(eatInfo.getImg());
        imags = new SimpleDraweeView[urlList.size()];
        if (imags.length != 0 && imags != null) {
            imageList = new ArrayList<>();
            for (int i = 0; i < imags.length; i++) {
                imags[i] = new SimpleDraweeView(this);
                adapter.UpDataUrl(urlList);
                imags[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                imags[i].setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageList.add(imags[i]);
                adapter.UpDataList(imageList);
                lookViewPager.setAdapter(adapter);
                lookLinearLayout.setVisibility(View.VISIBLE);
                lookshopName.setText(eatInfo.getName());
                lookoldPrice.setText(eatInfo.getPrice_old());
                looknewPrice.setTextColor(Color.RED);
                looknewPrice.setText("优惠价: " + eatInfo.getPrice_new());
                if (eatInfo.getAdress().equals("") || TextUtils.isEmpty(eatInfo.getAdress())) {
                    adress.setText("具体详情请上官网");
                } else
                    adress.setText(eatInfo.getAdress());
            }
        }
    }

    private void initList() {
        imags = new SimpleDraweeView[infos.getImglist().size()];
        if (imags.length != 0 && imags != null) {
            imageList = new ArrayList<>();
            urlList = new ArrayList<>();
            for (int i = 0; i < imags.length; i++) {
                imags[i] = new SimpleDraweeView(this);
                urlList.add(infos.getImglist().get(i));
                adapter.UpDataUrl(urlList);
                imags[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                imags[i].setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageList.add(imags[i]);
                adapter.UpDataList(imageList);
                lookViewPager.setAdapter(adapter);
                lookLinearLayout.setVisibility(View.VISIBLE);
                lookshopName.setText(infos.getName());
                lookoldPrice.setText(info.getPrice_oid());
                looknewPrice.setTextColor(Color.RED);
                looknewPrice.setText(" 活动价格 ￥:" + info.getPrice_new());
            }
        }
    }

    ArrayList<String> list = new ArrayList<>();

    private void getData(final ShopCellInfo info) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                infos = info;
                mHandler.sendEmptyMessage(1);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }


    public boolean getSQLData() {
        if (info != null) {
            for (int i = 0; i < list.size(); i++) {
                if (info.getName().equals(list.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lookshopcar:

                if (index == 2) {
                    if (isadd) {
                        if (getSQLData()) {
                            DBmanager.addShopToSql(info);
                            isadd = false;
                            utils.showSanckbar(lookViewPager, "购物车添加成功！");
                        }else{
                            utils.showSanckbar(lookViewPager, "购物车存在该商品");
                        }
                    } else {
                        utils.showSanckbar(lookViewPager, "购物车存在该商品");
                    }

                } else if (index == 1) {
                    Toast.makeText(this, "由于支付问题,暂未开通此功能", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.lookgoback:
                finish();
                break;
            case R.id.fenxiang:
                if (index == 2) {
                    final String sdPath = "/sdcard/Download/Img1/" + info.getName() + ".jpg";
                    File file = new File(sdPath);
                    if (!file.exists()) {
                        HttpUtils http = new HttpUtils();
                        HttpHandler handler = http.download(info.getImg(), sdPath, true, false,
                                new RequestCallBack<File>() {
                                    @Override
                                    public void onSuccess(ResponseInfo<File> responseInfo) {
                                    }

                                    @Override
                                    public void onFailure(HttpException e, String s) {
                                    }
                                });
                    }
                    MyShareSDK share = new MyShareSDK();
                    share.showShare(LookActivity.this, info, sdPath);
                } else if (index == 1) {
                    final String sdPath = "/sdcard/Download/Img2/" + eatInfo.getName() + ".jpg";
                    File file = new File(sdPath);
                    if (!file.exists()) {
                        HttpUtils http = new HttpUtils();
                        HttpHandler handler = http.download(eatInfo.getImg(), sdPath, true, false,
                                new RequestCallBack<File>() {
                                    @Override
                                    public void onSuccess(ResponseInfo<File> responseInfo) {
                                    }

                                    @Override
                                    public void onFailure(HttpException e, String s) {
                                    }
                                });
                    }
                    MyShareSDK share = new MyShareSDK();
                    share.showShare1(LookActivity.this, eatInfo, sdPath);
                }

                break;
            case R.id.lookshopmore:
                Toast.makeText(this, "此功能暂未开放", Toast.LENGTH_SHORT).show();
                break;
            case R.id.floatbtnSave:
                if (index == 2) {
                    showPopWindow2();
                } else if (index == 1) {
                    showPopWindow1();
                }
                break;
            case R.id.floatbtnShop:
                if (index == 2) {
                    Toast.makeText(this, "由于支付问题,暂未开通此功能", Toast.LENGTH_SHORT).show();

                } else if (index == 1) {
                    Toast.makeText(this, "由于支付问题,暂未开通此功能", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.floatbtnShopCar:
                if (index == 2) {
                    if (isadd) {
                        if (getSQLData()) {
                            DBmanager.addShopToSql(info);
                            isadd = false;
                            utils.showSanckbar(lookViewPager, "购物车添加成功！");
                        }else{
                            utils.showSanckbar(lookViewPager, "购物车存在该商品");
                        }
                    } else {
                        utils.showSanckbar(lookViewPager, "购物车存在该商品");
                    }

                } else if (index == 1) {
                    Toast.makeText(this, "由于支付问题,暂未开通此功能", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    private ImageView popimageLook;
    private DisplayImageOptions options;
    private TextView poptextPriceLook;

    public void showPopWindow2() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pop_window_layout, null);
        PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(Color.parseColor("#ffffff"));
        window.setBackgroundDrawable(dw);
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        popimageLook = (ImageView) view.findViewById(R.id.popimageLook);
        ImageLoader.getInstance().displayImage(info.getImg(), popimageLook, options);
        poptextPriceLook = (TextView) view.findViewById(R.id.poptextPriceLook);
        poptextPriceLook.setText("￥" + info.getPrice_new());
        window.showAtLocation(LookActivity.this.findViewById(R.id.floatbtnSave),
                Gravity.BOTTOM, 0, 0);
    }

    public void showPopWindow1() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pop_window_layout, null);
        PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(Color.parseColor("#ffffff"));
        window.setBackgroundDrawable(dw);
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        popimageLook = (ImageView) view.findViewById(R.id.popimageLook);
        ImageLoader.getInstance().displayImage(eatInfo.getImg(), popimageLook, options);
        poptextPriceLook = (TextView) view.findViewById(R.id.poptextPriceLook);
        poptextPriceLook.setText("优惠价" + eatInfo.getPrice_new());

        window.showAtLocation(LookActivity.this.findViewById(R.id.floatbtnSave),
                Gravity.BOTTOM, 0, 0);
    }
}
