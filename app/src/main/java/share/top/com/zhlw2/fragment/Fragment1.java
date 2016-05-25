package share.top.com.zhlw2.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import share.top.com.zhlw2.R;
import share.top.com.zhlw2.activity.LookActivity;
import share.top.com.zhlw2.adapter.FragmentItem1Adapter;
import share.top.com.zhlw2.bean.ShopCellInfo;
import share.top.com.zhlw2.bean.ShopInfo;
import share.top.com.zhlw2.db.MyDBManager;
import share.top.com.zhlw2.net.ShopData;
import share.top.com.zhlw2.utils.SPUtils;

/**
 * Created by ZHOU on 2016/4/13.
 */
public class Fragment1 extends BaseFragment {
    private View view;
    private RecyclerView fragment1ReyclerView;
    private StaggeredGridLayoutManager manager;
    private ShopData data;
    private SPUtils utils;
    private SwipeRefreshLayout fragment1SwipeLayout;
    private FragmentItem1Adapter adapter;
    private int index;
    public void getIndex(int index) {
        this.index = index;
    }

    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout_1, null);
        manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        return view;
    }

    public interface OnSrcollCallBack {
        void startSroll();//滑动

        void stopSroll();//停止滑动
    }

    private OnSrcollCallBack back;

    public OnSrcollCallBack getBack() {
        return back;
    }

    public void setBack(OnSrcollCallBack back) {
        this.back = back;
    }

    @Override
    public void initView() {
        fragment1SwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment1SwipeLayout);
        fragment1ReyclerView = (RecyclerView) view.findViewById(R.id.fragment1ReyclerView);
        fragment1ReyclerView.setLayoutManager(manager);
    }

    @Override
    public void before() {

    }

    private ArrayList<ShopInfo> listInfo;
    private ProgressDialog dialog;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                dialog.dismiss();
                fragment1SwipeLayout.setRefreshing(false);
                adapter.UpDate(listInfo);
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
        if (index == 0) {
            data.getData(ShopData.NVZHUANG, new ShopData.CallBack() {
                @Override
                public void linkdata(ArrayList<ShopInfo> list) {
                    aa(list);
                }
            });
        } else if (index == 1) {
            data.getData(ShopData.YUNDONG, new ShopData.CallBack() {
                @Override
                public void linkdata(ArrayList<ShopInfo> list) {
                    aa(list);
                }
            });
        } else if (index == 2) {
            data.getData(ShopData.NVXIE, new ShopData.CallBack() {
                @Override
                public void linkdata(ArrayList<ShopInfo> list) {
                    aa(list);
                }
            });
        } else if (index == 3) {
            data.getData(ShopData.JIAJU, new ShopData.CallBack() {
                @Override
                public void linkdata(ArrayList<ShopInfo> list) {
                    aa(list);
                }
            });
        }
    }

    private Intent intent;

    @Override
    public void after() {
        adapter = new FragmentItem1Adapter(getActivity());
        fragment1ReyclerView.setAdapter(adapter);
        data = ShopData.getInstance(getActivity());
        dialog = new ProgressDialog(getActivity());
        dialog.setCancelable(true);
        dialog.setMessage("加载....");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
        loading();
        fragment1SwipeLayout.setRefreshing(true);
        fragment1SwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loading();
            }
        });
        adapter.setClick(new FragmentItem1Adapter.OnItemCallBackClick() {
            @Override
            public void OnItemListener(View view, final int postion) {
                intent = new Intent(getActivity(), LookActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("key", listInfo.get(postion));
                bundle.putInt("eat", 2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        fragment1ReyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                manager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    int[] lastVisibleItem = manager.findLastVisibleItemPositions(null);
//                    int totalItemCount = manager.getItemCount();
//                }
                if (back != null) {
                    if (newState == 0) {
                        back.stopSroll();
                    } else
                        back.startSroll();
                }
            }
        });
    }
}
