package share.top.com.zhlw2.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import share.top.com.zhlw2.R;
import share.top.com.zhlw2.adapter.YiPagerAdapter;


public class YingDaoActivity extends BaseActivity {
    private ViewPager mViewPager_Y;
    private int isshow = 0;
    private View[] points = new View[3];
    private TextView yindao_btn;

    @Override
    public void setContentView() {
        if (getData() == 0) {
            saveData(1);
            setContentView(R.layout.activity_yingg_dao);
            isshow = 0;
        } else {
            setContentView(R.layout.activity_logo);
            isshow = 1;
        }
    }

    @Override
    public void beforeView() {

    }

    //保存数据
    private void saveData(int i) {
        SharedPreferences sp = this.getSharedPreferences("frist", 0);
        SharedPreferences.Editor se = sp.edit();
        se.putInt("version", i);
        se.commit();
    }

    private int getData() {
        SharedPreferences sp = this.getSharedPreferences("frist", 0);
        int version = sp.getInt("version", 0);
        return version;
    }


    @Override
    public void initView() {
        if (isshow == 0) {
            mViewPager_Y = (ViewPager) findViewById(R.id.YinViewPager);
            points[0] = findViewById(R.id.oneView);
            points[1] = findViewById(R.id.twoView);
            points[2] = findViewById(R.id.threeView);
        }
    }

    private ArrayList<View> list;

    public ArrayList<View> initList() {
        list = new ArrayList<>();
        list.add(getLayoutInflater().inflate(R.layout.pager_layout_1, null));
        list.add(getLayoutInflater().inflate(R.layout.pager_layout_2, null));
        View view3 = getLayoutInflater().inflate(R.layout.pager_layout_3, null);
        yindao_btn = (TextView) view3.findViewById(R.id.yindao_btn);
        yindao_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(YingDaoActivity.this, LogoActivity.class));
            }
        });
        list.add(view3);
        return list;
    }

    @Override
    public void afterView() {
        if (isshow == 0) {
            initList();
            YiPagerAdapter adapter = new YiPagerAdapter(this);
            adapter.Update(list);
            mViewPager_Y.setAdapter(adapter);
            mViewPager_Y.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    change_PointColor(viewpager_index, position % points.length);
                    viewpager_index = position % points.length;
                    if (position > list.size()) {
                        finish();
                        startActivity(new Intent(YingDaoActivity.this, YingDaoActivity.class));
                    }
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } else {
            startActivity(new Intent(YingDaoActivity.this, LogoActivity.class));
            finish();
        }
    }

    int viewpager_index = 0;

    public void change_PointColor(int lastIndex, int newIndex) {
        //老的坐标变成黑色,adware_style_default
        points[lastIndex].setBackgroundResource(R.mipmap.adware_style_default);
        points[newIndex].setBackgroundResource(R.mipmap.adware_style_selected);
    }


}
