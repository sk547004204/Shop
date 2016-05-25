package share.top.com.zhlw2.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.support.design.widget.FloatingActionButton;

import java.util.ArrayList;

import share.top.com.zhlw2.R;
import share.top.com.zhlw2.adapter.FragmentPagerAapter;
import share.top.com.zhlw2.fragment.Fragment1;
import share.top.com.zhlw2.fragment.Fragment2;
import share.top.com.zhlw2.fragment.Fragment3;
import share.top.com.zhlw2.fragment.Fragment4;
import share.top.com.zhlw2.view.MyScrollview;

public class Item2Activity extends BaseActivity {

    private ImageView goback;
    private ViewPager item2ViewPager;
    private FragmentPagerAapter adapter;
    private ArrayList<Fragment> list = new ArrayList<>();
    private FragmentManager manager;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    private MyScrollview item2ScrollView;
    private FloatingActionButton flatingbtn;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_item2);
    }

    @Override
    public void beforeView() {

    }

    private int positions;

    @Override
    public void initView() {
        flatingbtn = (FloatingActionButton) findViewById(R.id.flatingbtn);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        item2ScrollView = (MyScrollview) findViewById(R.id.item2ScrollView);
        item2ScrollView.setVisibility(View.GONE);
        manager = getSupportFragmentManager();
        goback = (ImageView) findViewById(R.id.goback);
        item2ViewPager = (ViewPager) findViewById(R.id.item2ViewPager);
        adapter = new FragmentPagerAapter(manager, this);
        item2ViewPager.setAdapter(adapter);
        positions = getIntent().getIntExtra("position", -1);
        if (positions == 0) {
            //4个fragment
            list.add(fragment1);
            fragment1.getIndex(0);
            adapter.UpList(list);
            initViewPagerListener();
        } else if (positions == 1) {
            //2个fragment//内衣和运动
            list.add(fragment1);
            fragment1.getIndex(1);
            adapter.UpList(list);
            initViewPagerListener();
        } else if (positions == 2) {
            //2个fragment
            list.add(fragment1);
            fragment1.getIndex(2);
            adapter.UpList(list);
            initViewPagerListener();
        } else if (positions == 3) {
            list.add(fragment1);
            fragment1.getIndex(3);
            adapter.UpList(list);
            initViewPagerListener();
        }
    }

    @Override
    public void afterView() {
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fragment1.setBack(new Fragment1.OnSrcollCallBack() {
            @Override
            public void startSroll() {
                ViewCompat.animate(flatingbtn).translationX(flatingbtn.getWidth()).start();
            }

            @Override
            public void stopSroll() {
                ViewCompat.animate(flatingbtn).translationX(-flatingbtn.getWidth()/2).start();
            }
        });
    }


    public void initViewPagerListener() {
        item2ViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
