package share.top.com.zhlw2.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import share.top.com.zhlw2.R;
import share.top.com.zhlw2.adapter.GoFragmentPagerAdapter;
import share.top.com.zhlw2.fragment.Fragment1;
import share.top.com.zhlw2.fragment.Fragment2;
import share.top.com.zhlw2.fragment.Fragment3;
import share.top.com.zhlw2.fragment.Fragment4;

public class Item3Activity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager item3ViewPager;
    private FragmentManager manager;
    private ArrayList<Fragment> fragmentList;
    private ArrayList<String> titleList;
    private GoFragmentPagerAdapter adapter;
    private ImageView goback;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_item3);
        manager = getSupportFragmentManager();
    }

    @Override
    public void beforeView() {

    }

    @Override
    public void initView() {
        goback = (ImageView) findViewById(R.id.goback);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        item3ViewPager = (ViewPager) findViewById(R.id.item3ViewPager);
    }

    @Override
    public void afterView() {
        initList();
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initList() {
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        titleList.add("女装");
        titleList.add("男装");
        titleList.add("运动");
        titleList.add("女鞋");
        titleList.add("男鞋");
        titleList.add("内衣");
        titleList.add("童装");
        titleList.add("户外");
        titleList.add("家具");
        titleList.add("箱包");
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());
        fragmentList.add(new Fragment4());
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());
        fragmentList.add(new Fragment4());
        fragmentList.add(new Fragment3());
        fragmentList.add(new Fragment3());
        fragmentList.add(new Fragment3());
        fragmentList.add(new Fragment3());
        adapter = new GoFragmentPagerAdapter(manager);
        item3ViewPager.setAdapter(adapter);
        adapter.UpDateList(fragmentList, titleList);
        tabLayout.setupWithViewPager(item3ViewPager);
    }
}
