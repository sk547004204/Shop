package share.top.com.zhlw2.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import share.top.com.zhlw2.R;
import share.top.com.zhlw2.activity.Item1Activity;
import share.top.com.zhlw2.activity.Item2Activity;
import share.top.com.zhlw2.activity.Item3Activity;
import share.top.com.zhlw2.adapter.HomeFragmentRecyclerAdapter;
import share.top.com.zhlw2.bean.ShopInfo;
import share.top.com.zhlw2.net.ShopData;
import share.top.com.zhlw2.utils.SPUtils;

/**
 * Created by ZHOU on 2016/4/8.
 */
public class HomeFragment extends BaseFragment implements View.OnTouchListener {
    private View view;
    private SwipeRefreshLayout HomeSwipeLayout;
    private RecyclerView HomeReyclerView;
    private HomeFragmentRecyclerAdapter adapter;
    private ArrayList<View> list;
    private LinearLayoutManager manager;
    private LinearLayout homeFragmentlayout;
    private RelativeLayout fragmentitem3;
    private ShopData data;
    private SPUtils utils;

    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homefragment_layout, null);
        return view;
    }

    @Override
    public void initView() {
        utils = new SPUtils(getActivity());
        fragmentitem3 = (RelativeLayout) view.findViewById(R.id.fragmentitem3);
        homeFragmentlayout = (LinearLayout) view.findViewById(R.id.homeFragmentlayout);
        HomeReyclerView = (RecyclerView) view.findViewById(R.id.HomeRecyclerView);
        HomeReyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getActivity());
        HomeReyclerView.setLayoutManager(manager);
        list = new ArrayList();
        HomeSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.HomeSwipeLayout);
        data = ShopData.getInstance(getActivity());
    }

    @Override
    public void before() {

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                HomeSwipeLayout.setRefreshing(false);
                adapter.UpdateShopInfo(listInfo);
            }
        }
    };

    private ArrayList<ShopInfo> listInfo;

    private void aa(final ArrayList<ShopInfo> list) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (list != null) {
                    listInfo = list;
                    mHandler.sendEmptyMessage(1);
                }
            }
        }).start();
    }

    Intent intent;

    @Override
    public void after() {
        HomeSwipeLayout.setColorSchemeColors(Color.BLUE, Color.BLACK);
        ViewPager pager = new ViewPager(getActivity());
        adapter = new HomeFragmentRecyclerAdapter(getActivity());
        HomeReyclerView.setAdapter(adapter);
        list.add(pager);
        list.add(homeFragmentlayout);
        list.add(fragmentitem3);
        adapter.Update(list);
        loading();
        adapter.setCilck(new HomeFragmentRecyclerAdapter.ItemLayoutCilck() {
            @Override
            public void itemLayoutListener(View view) {
                switch (view.getId()) {
                    case R.id.item2layout1:
                        intent = new Intent(getActivity(), Item2Activity.class);
                        intent.putExtra("position", 0);
                        startActivity(intent);
                        break;
                    case R.id.item2layout2:
                        intent = new Intent(getActivity(), Item2Activity.class);
                        intent.putExtra("position", 1);
                        startActivity(intent);
                        break;
                    case R.id.item2layout3:
                        intent = new Intent(getActivity(), Item2Activity.class);
                        intent.putExtra("position", 2);
                        startActivity(intent);
                        break;
                    case R.id.item2layout4:
                        intent = new Intent(getActivity(), Item3Activity.class);
                        intent.putExtra("position", 3);
                        startActivity(intent);
                        break;
                }
            }

            @Override
            public void itemListener(View view, int position) {

            }
        });
        HomeSwipeLayout.setRefreshing(true);
        HomeSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loading();
            }
        });
        adapter.setBack(new HomeFragmentRecyclerAdapter.EatCallBack() {
            @Override
            public void itemEatListener(View view) {
                switch (view.getId()) {
                    case R.id.eatlayout1:
                        intent = new Intent(getActivity(), Item1Activity.class);
                        intent.putExtra("eat", 1);
                        startActivity(intent);
                        break;
                    case R.id.eatlayout2:
                        intent = new Intent(getActivity(), Item1Activity.class);
                        intent.putExtra("eat", 2);
                        startActivity(intent);
                        break;
                    case R.id.eatlayout3:
                        intent = new Intent(getActivity(), Item1Activity.class);
                        intent.putExtra("eat", 3);
                        startActivity(intent);
                        break;
                    case R.id.eatlayout4:
                        intent = new Intent(getActivity(), Item1Activity.class);
                        intent.putExtra("eat", 4);
                        startActivity(intent);
                        break;
                    case R.id.eatlayout5:
                        intent = new Intent(getActivity(), Item1Activity.class);
                        intent.putExtra("eat", 5);
                        startActivity(intent);
                        break;
                    case R.id.eatlayout6:
                        intent = new Intent(getActivity(), Item1Activity.class);
                        intent.putExtra("eat", 6);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    public void loading() {
        data.getData(ShopData.NVXIE, new ShopData.CallBack() {
            @Override
            public void linkdata(ArrayList<ShopInfo> list) {
                aa(list);
            }

        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnTouchListener(this);
    }
}
