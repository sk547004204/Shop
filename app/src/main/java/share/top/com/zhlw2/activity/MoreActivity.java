package share.top.com.zhlw2.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import java.util.ArrayList;

import share.top.com.zhlw2.R;
import share.top.com.zhlw2.adapter.MeMoreAdapter;
import share.top.com.zhlw2.fragment.MeMore1;

public class MoreActivity extends BaseActivity {
    private ViewPager viewpager;
    private TabLayout tableLayout;
    private ArrayList<Fragment> list;
    private ArrayList<String> list_tag;

    private android.support.v4.app.FragmentManager manager;
    MeMoreAdapter adapter;
    TextView title;


    @Override
    public void setContentView() {
        setContentView(R.layout.order_activity);
    }

    @Override
    public void beforeView() {

    }

    @Override
    public void initView() {
        title = (TextView) findViewById(R.id.title_set);
        list = new ArrayList<>();
        list_tag = new ArrayList<>();
        viewpager = (ViewPager) findViewById(R.id.me_more_vp);
        tableLayout = (TabLayout) findViewById(R.id.me_more_tab);
    }

    @Override
    public void afterView() {
        MeMore1 meMore1 = new MeMore1();
        MeMore1 meMore2 = new MeMore1();
        MeMore1 meMore3 = new MeMore1();
        MeMore1 meMore4 = new MeMore1();
        MeMore1 meMore5 = new MeMore1();
        int number = getIntent().getIntExtra("name", 0);
        title.setText("待办事件");
        list_tag.add("待付款");
        list_tag.add("待发货");
        list_tag.add("待收货");
        list_tag.add("待评价");
        list_tag.add("售  后");
        list.add(meMore1);
        list.add(meMore2);
        list.add(meMore3);
        list.add(meMore4);
        list.add(meMore5);
        manager = getSupportFragmentManager();
        adapter = new MeMoreAdapter(manager, list, list_tag);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(number - 1);
        //吧tablayout与view关联起来
        tableLayout.setupWithViewPager(viewpager);
    }
}
