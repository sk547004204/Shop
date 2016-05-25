package share.top.com.zhlw2.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import share.top.com.zhlw2.R;
import share.top.com.zhlw2.adapter.CardAdapter;
import share.top.com.zhlw2.bean.ShopInfo;
import share.top.com.zhlw2.db.MyDBManager;

/**
 * Created by hpp on 2016/4/18.
 */
public class MeMore1 extends BaseFragment{
    View view;
    private ArrayList<ShopInfo> list = new ArrayList<>();
    private CardAdapter adapter;
    private MyDBManager manager;
    private RecyclerView meFragmentRecyler;
    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.me_more1,null);
        return view;
    }

    @Override
    public void initView() {
        manager = MyDBManager.getInstance(getActivity());
        list = manager.getContent();
        meFragmentRecyler = (RecyclerView) view.findViewById(R.id.me_more_rv);
        adapter = new CardAdapter(getActivity());
    }

    @Override
    public void before() {

    }

    @Override
    public void after() {
        meFragmentRecyler.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter.AddData(list);
        meFragmentRecyler.setAdapter(adapter);
        //设置Item增加、移除动画
        meFragmentRecyler.setItemAnimator(new DefaultItemAnimator());
        adapter.getClick();
        adapter.setClick(new CardAdapter.OnItemCallBackClick() {
            @Override
            public void OnItemListener(View view, int position) {
                //这里跳转到对应的商品信息里
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
