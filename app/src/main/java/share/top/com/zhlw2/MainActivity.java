package share.top.com.zhlw2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.viewbadger.BadgeView;

import java.util.ArrayList;

import share.top.com.zhlw2.activity.BaseActivity;
import share.top.com.zhlw2.activity.SettingActivity;
import share.top.com.zhlw2.bean.ShopInfo;
import share.top.com.zhlw2.bean.User;
import share.top.com.zhlw2.db.MyDBManager;
import share.top.com.zhlw2.fragment.HomeFragment;
import share.top.com.zhlw2.fragment.MeFragment;
import share.top.com.zhlw2.fragment.ShopFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ImageView homeImage, gouImage, meImage;
    private LinearLayout actionBarLayout;
    private TextView homeText, gouText, meText;
    private LinearLayout homeLayout, gouLayout, meLayout;
    private Fragment[] fragments = new Fragment[3];
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private TextView titleLine;
    private RelativeLayout homeTitleLayout, gouTitleLayout, meTitleLayout;
    private ImageView settingImage;
    private MeFragment fragment;
    private View goulin;
    private ShopFragment shopFragment;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        fragment = new MeFragment();
        shopFragment = new ShopFragment();
    }

    @Override
    public void beforeView() {

    }

    @Override
    public void initView() {
        titleLine = (TextView) findViewById(R.id.titleLine);
        actionBarLayout = (LinearLayout) findViewById(R.id.actionBarLayout);
        homeImage = (ImageView) findViewById(R.id.homeImg);
        gouImage = (ImageView) findViewById(R.id.gouImg);
        meImage = (ImageView) findViewById(R.id.meImg);
        homeLayout = (LinearLayout) findViewById(R.id.homeLayout);
        gouLayout = (LinearLayout) findViewById(R.id.gouLayout);
        meLayout = (LinearLayout) findViewById(R.id.meLayout);
        homeText = (TextView) findViewById(R.id.HomeText);
        gouText = (TextView) findViewById(R.id.gouText);
        meText = (TextView) findViewById(R.id.meText);
        homeTitleLayout = (RelativeLayout) findViewById(R.id.homeTitleLayout);
        gouTitleLayout = (RelativeLayout) findViewById(R.id.gouTitleLayout);
        meTitleLayout = (RelativeLayout) findViewById(R.id.meTitleLayout);
        settingImage = (ImageView) findViewById(R.id.settingImage);
        goulin = findViewById(R.id.goulin);
    }


    private void cleanState() {
        for (int i = 0; i < fragments.length; i++) {
            if (fragments[i] != null) {
                transaction.hide(fragments[i]);
            }
        }
    }

    public void choseState(int position) {
        transaction = manager.beginTransaction();
        cleanState();
        CleanInfoState();
        if (fragments[position] == null) {
            switch (position) {
                case 0:
                    fragments[position] = new HomeFragment();
                    break;
                case 1:
                    fragments[position] = new ShopFragment();
                    break;
                case 2:
                    fragments[position] = new MeFragment();
                    break;
            }
            transaction.add(R.id.content, fragments[position]);
        } else {
            transaction.show(fragments[position]);
        }
        transaction.commit();
    }


    //清理原有的状态
    public void CleanInfoState() {
        homeTitleLayout.setVisibility(View.GONE);
        meTitleLayout.setVisibility(View.GONE);
        gouTitleLayout.setVisibility(View.GONE);
        homeLayout.setBackgroundColor(Color.parseColor("#F5F5F5"));
        gouLayout.setBackgroundColor(Color.parseColor("#F5F5F5"));
        homeImage.setImageResource(R.mipmap.home_undian);
        gouText.setTextColor(Color.parseColor("#000000"));
        homeText.setTextColor(Color.parseColor("#000000"));
        gouImage.setImageResource(R.mipmap.gou_undian);
        meLayout.setBackgroundColor(Color.parseColor("#F5F5F5"));
        meImage.setImageResource(R.mipmap.me_undian);
        meText.setTextColor(Color.parseColor("#000000"));
    }

    @Override
    public void afterView() {
        final BadgeView badgeView = new BadgeView(MainActivity.this, goulin);
        cleanState();
        choseState(0);
        homeTitleLayout.setVisibility(View.VISIBLE);
        homeLayout.setOnClickListener(this);
        gouLayout.setOnClickListener(this);
        meLayout.setOnClickListener(this);
        homeLayout.setBackgroundColor(Color.parseColor("#F5F5F5"));
        homeImage.setImageResource(R.mipmap.home_dian);
        homeText.setTextColor(Color.parseColor("#EA8010"));
        settingImage.setOnClickListener(this);
        shopFragment.setCallBack(new ShopFragment.ListCallBack() {
            @Override
            public void getListSize(int size) {
                Log.e("aa", "hixing" + size);
                badgeView.setText(size + "");
                badgeView.setBadgePosition(badgeView.POSITION_TOP_RIGHT);
                badgeView.setTextSize(8);
                badgeView.show();
            }
        });
        MyDBManager dbManager = MyDBManager.getInstance(this);
        ArrayList<ShopInfo> list = dbManager.getContent();
        if (list.size() != 0) {
            badgeView.setText(list.size() + "");
            badgeView.setBadgePosition(badgeView.POSITION_TOP_RIGHT);
            badgeView.setTextSize(8);
            badgeView.show();
        } else {
            badgeView.setVisibility(View.GONE);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeLayout:
                choseState(0);
                titleLine.setVisibility(View.VISIBLE);
                actionBarLayout.setBackgroundColor(Color.parseColor("#ffffff"));
                homeLayout.setBackgroundColor(Color.parseColor("#F5F5F5"));
                homeImage.setImageResource(R.mipmap.home_dian);
                homeText.setTextColor(Color.parseColor("#EA8010"));
                homeTitleLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.gouLayout:
                titleLine.setVisibility(View.VISIBLE);
                choseState(1);
                actionBarLayout.setBackgroundColor(Color.parseColor("#ffffff"));
                gouLayout.setBackgroundColor(Color.parseColor("#F5F5F5"));
                gouImage.setImageResource(R.mipmap.gou_dian);
                gouText.setTextColor(Color.parseColor("#EA8010"));
                gouTitleLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.meLayout:
                titleLine.setVisibility(View.GONE);
                choseState(2);
                actionBarLayout.setBackgroundColor(Color.parseColor("#FF5001"));
                meLayout.setBackgroundColor(Color.parseColor("#F5F5F5"));
                meImage.setImageResource(R.mipmap.me_dian);
                meText.setTextColor(Color.parseColor("#EA8010"));
                meTitleLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.settingImage:
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                overridePendingTransition(R.anim.search_anim_in, R.anim.search_anim_out);
                break;
        }
    }

    boolean isExit;

    // 退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    // 定义一个handler 重置isExit
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101) {
            Bundle bundle = data.getExtras();
            String name = bundle.getString("name");
            String headimage = bundle.getString("url");
            inter(name, headimage);
            getSqlDataUserInfo();
        }
    }

    private Handler mHandlers = new Handler();

    public void setHandler(Handler mHandler) {
        this.mHandlers = mHandler;
    }

    MyDBManager myDBManager;

    public void inter(String username, String headimage) {
        myDBManager = MyDBManager.getInstance(this);
        myDBManager.addUserToSql(new User(username, headimage, 1000));
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        bundle.putString("headimage", headimage);
        msg.setData(bundle);
        msg.what = 1;
        mHandlers.sendMessage(msg);
    }


    public void getSqlDataUserInfo() {
        myDBManager = MyDBManager.getInstance(this);
        ArrayList<User> userInfo = myDBManager.getUserInfo();
        Log.e("aa", "获取用户个数为" + userInfo.size());
        Log.e("aa", "获取用户" + userInfo);
        Message msg = new Message();
        msg.what = 2;
        msg.obj = userInfo;
        mHandlers.sendMessage(msg);
    }

    // 实现退出方法
    public void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);

            MyDBManager mager=MyDBManager.getInstance(this);
            mager.delData();
            Log.e("aa", "删除后用户个数" + mager.getUserInfo().size());
            mager.delshopinfoData();
            Log.e("aa", "删除后商品个数" + mager.getContent().size());
            startActivity(intent);
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
