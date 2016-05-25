package share.top.com.zhlw2.activity;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import android.support.design.widget.FloatingActionButton;

import java.util.ArrayList;

import share.top.com.zhlw2.R;
import share.top.com.zhlw2.adapter.EatFragmentAdapter;
import share.top.com.zhlw2.adapter.FragmentPagerAapter;
import share.top.com.zhlw2.adapter.Item2FragmetAdapter;
import share.top.com.zhlw2.adapter.mGridViewAdapter;
import share.top.com.zhlw2.bean.EatInfo;
import share.top.com.zhlw2.fragment.EatFragment1;
import share.top.com.zhlw2.net.ShopData;
import share.top.com.zhlw2.view.FullyGridLayoutManager;

/**
 * Created by ZHOU on 2016/4/13.
 */
public class Item1Activity extends BaseActivity {
    private ImageView goback;
    private ViewPager item2ViewPager;
    private FloatingActionButton flatingbtn;
    private FragmentPagerAapter adapter;
    private FragmentManager manager;
    private ArrayList<Fragment> list = new ArrayList<>();
    private EatFragment1 fragment1;
    private RelativeLayout titlebarLayout;
    private LinearLayout serachLayout;
    private ImageView SubmitGoBack;
    private TextView searchSubText;
    private EditText edSearch;
    private RecyclerView item2RecyclerView;
    private StaggeredGridLayoutManager managers;
    private ArrayList<String> cityList;
    private Item2FragmetAdapter cityAdapter;
    private ArrayList<View> viewList;
    private RelativeLayout cityLayoutItem;
    private RecyclerView item2RecyclerViewAdapter;
    private StaggeredGridLayoutManager linearLayoutManager;
    private GridView item2GridView;
    private FullyGridLayoutManager fullyGridLayoutManager;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_item2);
    }

    @Override
    public void beforeView() {

    }

    @Override
    public void initView() {
        fullyGridLayoutManager = new FullyGridLayoutManager(this, 2);
        item2GridView = (GridView) findViewById(R.id.item2GridView);
        item2RecyclerView = (RecyclerView) findViewById(R.id.item2RecyclerView);
        managers = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        linearLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        View view = LayoutInflater.from(this).inflate(R.layout.reycler_layout, null);
        item2RecyclerViewAdapter = (RecyclerView) view.findViewById(R.id.item2RecyclerViewAdapter);
        fullyGridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        fullyGridLayoutManager.setSmoothScrollbarEnabled(true);
        item2RecyclerViewAdapter.setLayoutManager(fullyGridLayoutManager);
        viewList = new ArrayList<>();
        cityAdapter = new Item2FragmetAdapter(this);
        cityList = new ArrayList<>();
        cityLayoutItem = (RelativeLayout) findViewById(R.id.cityLayoutItem);
        viewList.add(cityLayoutItem);
        cityAdapter.UpViewDateList(viewList);
        item2RecyclerView = (RecyclerView) findViewById(R.id.item2RecyclerView);
        item2RecyclerView.setAdapter(cityAdapter);
        SubmitGoBack = (ImageView) findViewById(R.id.SubmitGoBack);
        SubmitGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        searchSubText = (TextView) findViewById(R.id.searchSubText);
        edSearch = (EditText) findViewById(R.id.edSearch);
        titlebarLayout = (RelativeLayout) findViewById(R.id.titlebarLayout);
        serachLayout = (LinearLayout) findViewById(R.id.serachLayout);
        goback = (ImageView) findViewById(R.id.goback);
        item2ViewPager = (ViewPager) findViewById(R.id.item2ViewPager);
        flatingbtn = (FloatingActionButton) findViewById(R.id.flatingbtn);
        manager = getSupportFragmentManager();
        fragment1 = new EatFragment1();
        item2ScrollView = (ScrollView) findViewById(R.id.item2ScrollView);
    }

    private int eat;
    private mGridViewAdapter gridViewApdater;
    private ShopData data;
    private ArrayList<EatInfo> infoList;
    private ProgressDialog bar;
    private EatFragmentAdapter eatAdapter;
    private ScrollView item2ScrollView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                bar.dismiss();
                eatAdapter.UpDate(infoList);
            }
        }
    };

    private void aa(final ArrayList<EatInfo> list) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (list != null) {
                    infoList = list;
                    mHandler.sendEmptyMessage(1);
                }
            }
        }).start();
    }

    @Override
    public void afterView() {
        eatAdapter = new EatFragmentAdapter(this);
        data = ShopData.getInstance(this);
        gridViewApdater = new mGridViewAdapter(this);
        item2GridView.setAdapter(gridViewApdater);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter = new FragmentPagerAapter(manager, this);
        item2ViewPager.setAdapter(adapter);
        eat = getIntent().getIntExtra("eat", -1);
        if (eat == 1) {
            list.add(fragment1);
            fragment1.getIndex(1);
            adapter.UpList(list);
            titlebarLayout.setVisibility(View.VISIBLE);
            serachLayout.setVisibility(View.GONE);
            item2ScrollView.setVisibility(View.GONE);
        } else if (eat == 2) {
            list.add(fragment1);
            fragment1.getIndex(2);
            adapter.UpList(list);
            item2ScrollView.setVisibility(View.GONE);
            titlebarLayout.setVisibility(View.VISIBLE);
            serachLayout.setVisibility(View.GONE);
        } else if (eat == 3) {
            list.add(fragment1);
            fragment1.getIndex(3);
            adapter.UpList(list);
            item2ScrollView.setVisibility(View.GONE);
            titlebarLayout.setVisibility(View.VISIBLE);
            serachLayout.setVisibility(View.GONE);
        } else if (eat == 4) {
            list.add(fragment1);
            fragment1.getIndex(4);
            adapter.UpList(list);
            item2ScrollView.setVisibility(View.GONE);
            titlebarLayout.setVisibility(View.VISIBLE);
            serachLayout.setVisibility(View.GONE);
        } else if (eat == 5) {
            list.add(fragment1);
            fragment1.getIndex(5);
            adapter.UpList(list);
            item2ScrollView.setVisibility(View.GONE);
            titlebarLayout.setVisibility(View.VISIBLE);
        } else if (eat == 6) {
            item2ScrollView.setVisibility(View.VISIBLE);
            item2ViewPager.setVisibility(View.GONE);
            item2GridView.setVisibility(View.VISIBLE);
            item2RecyclerView.setVisibility(View.VISIBLE);
            item2RecyclerView.setLayoutManager(linearLayoutManager);
            item2RecyclerView.setAdapter(eatAdapter);
            serachLayout.setVisibility(View.VISIBLE);
            titlebarLayout.setVisibility(View.GONE);
            cityList.add("北京");
            cityList.add("上海");
            cityList.add("成都");
            cityList.add("巴中");
            cityList.add("达州");
            cityList.add("泸州");
            gridViewApdater.UpGridList(cityList);
            bar = new ProgressDialog(Item1Activity.this);
            bar.setCancelable(true);
            bar.setMessage("加载....");
            bar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            bar.show();
            data.getEating("北京", new ShopData.EatCallBack() {
                @Override
                public void getEatData(ArrayList<EatInfo> list) {
                    aa(list);
                }
            });
            searchSubText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String content = edSearch.getText().toString().trim();
                    if (!content.isEmpty() || content != null) {
                        data.getEating(content, new ShopData.EatCallBack() {
                            @Override
                            public void getEatData(ArrayList<EatInfo> list) {
                                bar = new ProgressDialog(Item1Activity.this);
                                bar.setCancelable(true);
                                bar.setMessage("加载....");
                                bar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                bar.show();
                                aa(list);
                            }
                        });
                    }
                }
            });
        }
    }
}
