package share.top.com.zhlw2.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import share.top.com.zhlw2.R;
import share.top.com.zhlw2.activity.LookActivity;
import share.top.com.zhlw2.adapter.EatFragmentAdapter;
import share.top.com.zhlw2.bean.EatInfo;
import share.top.com.zhlw2.net.ShopData;

/**
 * Created by ZHOU on 2016/4/18.
 */
public class EatFragment1 extends BaseFragment {
    private View view;
    private SwipeRefreshLayout fragment1SwipeLayout;
    private RecyclerView fragment1ReyclerView;
    private StaggeredGridLayoutManager manager;
    private EatFragmentAdapter adapter;
    private ProgressDialog bar;
    private ShopData data;

    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout_1, null);
        return view;
    }

    private int index;

    public void getIndex(int index) {
        this.index = index;
    }

    @Override
    public void initView() {
        data = ShopData.getInstance(getActivity());
        adapter = new EatFragmentAdapter(getActivity());
        manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        fragment1SwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment1SwipeLayout);
        fragment1ReyclerView = (RecyclerView) view.findViewById(R.id.fragment1ReyclerView);
    }

    private ArrayList<EatInfo> infoList;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                bar.dismiss();
                fragment1SwipeLayout.setRefreshing(false);
                adapter.UpDate(infoList);
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


    private ArrayList<EatInfo> list;

    private Intent intent;

    private void LookInEatInfo(int position) {
        if (position == 1) {
            adapter.setBack(new EatFragmentAdapter.EatCallBack() {
                @Override
                public void EatItemListener(View v, int position) {
                    intent = new Intent(getActivity(), LookActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("key", infoList.get(position));
                    bundle.putInt("eat", 1);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }else if (position==2){
            adapter.setBack(new EatFragmentAdapter.EatCallBack() {
                @Override
                public void EatItemListener(View v, int position) {
                    intent = new Intent(getActivity(), LookActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("key", infoList.get(position));
                    bundle.putInt("eat", 1);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }else if (position==3){
            adapter.setBack(new EatFragmentAdapter.EatCallBack() {
                @Override
                public void EatItemListener(View v, int position) {
                    intent = new Intent(getActivity(), LookActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("key", infoList.get(position));
                    bundle.putInt("eat", 1);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }else if (position==4){
            adapter.setBack(new EatFragmentAdapter.EatCallBack() {
                @Override
                public void EatItemListener(View v, int position) {
                    intent = new Intent(getActivity(), LookActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("key", infoList.get(position));
                    bundle.putInt("eat", 1);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }else if (position==5){
            adapter.setBack(new EatFragmentAdapter.EatCallBack() {
                @Override
                public void EatItemListener(View v, int position) {
                    intent = new Intent(getActivity(), LookActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("key", infoList.get(position));
                    bundle.putInt("eat", 1);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
    }


    private void loading() {
        if (index == 1) {
            data.getEating("北京", new ShopData.EatCallBack() {
                @Override
                public void getEatData(ArrayList<EatInfo> list) {
                    aa(list);
                }
            });
            LookInEatInfo(1);
        } else if (index == 2) {
            data.getEating("上海", new ShopData.EatCallBack() {
                @Override
                public void getEatData(ArrayList<EatInfo> list) {
                    aa(list);
                }
            });
            LookInEatInfo(2);
        } else if (index == 3) {
            data.getEating("巴中", new ShopData.EatCallBack() {
                @Override
                public void getEatData(ArrayList<EatInfo> list) {
                    aa(list);
                }
            });
            LookInEatInfo(3);
        } else if (index == 4) {
            data.getEating("达州", new ShopData.EatCallBack() {
                @Override
                public void getEatData(ArrayList<EatInfo> list) {
                    aa(list);
                }
            });
            LookInEatInfo(4);
        } else if (index == 5) {
            data.getEating("泸州", new ShopData.EatCallBack() {
                @Override
                public void getEatData(ArrayList<EatInfo> list) {
                    aa(list);
                }
            });
            LookInEatInfo(5);
        } else if (index == 6) {

        }
    }


    @Override
    public void before() {

    }

    @Override
    public void after() {
        fragment1ReyclerView.setLayoutManager(manager);
        fragment1ReyclerView.setAdapter(adapter);
        bar = new ProgressDialog(getActivity());
        bar.setCancelable(true);
        bar.setMessage("加载....");
        bar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        bar.show();
        fragment1SwipeLayout.setRefreshing(true);
        loading();
        fragment1SwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loading();
            }
        });
    }
}
