package share.top.com.zhlw2.fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import share.top.com.zhlw2.R;
import share.top.com.zhlw2.adapter.FragmentItem1Adapter;
import share.top.com.zhlw2.adapter.item1RecyclerAdapter;
import share.top.com.zhlw2.bean.ShopInfo;
import share.top.com.zhlw2.net.ShopData;
import share.top.com.zhlw2.utils.SPUtils;

/**
 * Created by ZHOU on 2016/4/16.
 */
public class Fragment3 extends BaseFragment {
    private View view;
    private SwipeRefreshLayout fragment2SwipeLayout;
    private RecyclerView fragment2RecyclerView;
    private ShopData data;
    private FragmentItem1Adapter adapter;
    private ProgressDialog bar;
    private StaggeredGridLayoutManager manager;

    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout_2, null);
        return view;
    }

    @Override
    public void initView() {
        fragment2SwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment2SwipeLayout);
        fragment2RecyclerView = (RecyclerView) view.findViewById(R.id.fragment2RecyclerView);
    }

    @Override
    public void before() {

    }

    @Override
    public void after() {
        fragment2SwipeLayout.setColorSchemeColors(Color.BLUE, Color.BLACK);
        manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        adapter = new FragmentItem1Adapter(getActivity());
        fragment2RecyclerView.setLayoutManager(manager);
        fragment2RecyclerView.setAdapter(adapter);
        data = ShopData.getInstance(getActivity());
        bar = new ProgressDialog(getActivity());
        bar.setCancelable(true);
        bar.setMessage("加载中....");
        bar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        bar.show();
        loading();
        fragment2SwipeLayout.setRefreshing(true);
        fragment2SwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loading();
            }
        });
    }

    private ArrayList<ShopInfo> listInfo;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                bar.dismiss();
                fragment2SwipeLayout.setRefreshing(false);
                adapter.UpDate(listInfo);
                //Snackbar.make(fragment2SwipeLayout, "加载成功！", Snackbar.LENGTH_SHORT).show();
            }
        }
    };

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

    public void loading() {
        data.getData(ShopData.NANXIE, new ShopData.CallBack() {
            @Override
            public void linkdata(ArrayList<ShopInfo> list) {
                aa(list);
            }

        });
    }
}
