package share.top.com.zhlw2.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.CheckBox;

import java.util.ArrayList;

import share.top.com.zhlw2.MainActivity;
import share.top.com.zhlw2.R;
import share.top.com.zhlw2.activity.LoginActivity;
import share.top.com.zhlw2.adapter.CardAdapter;
import share.top.com.zhlw2.bean.ShopInfo;
import share.top.com.zhlw2.bean.User;
import share.top.com.zhlw2.db.MyDBManager;
import share.top.com.zhlw2.net.ApplayTask;

/**
 * Created by ZHOU on 2016/4/8.
 */
public class ShopFragment extends BaseFragment implements View.OnTouchListener {
    private View view;
    private ArrayList<ShopInfo> list = new ArrayList<>();
    private CardAdapter adapter;
    private MyDBManager manager;
    private RecyclerView meFragmentRecyler;
    private CheckBox ck;
    private TextView tv_carnull, tv_all, tv_priceall, tv_count;
    private ImageView img_carnull;
    private ArrayList<ShopInfo> commitlist = new ArrayList<>();
    private View buy;
    private ArrayList<User> userlist;

    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.goufragment_layout, null);
        return view;
    }

    @Override
    public void initView() {
        manager = MyDBManager.getInstance(getActivity());
        userlist = manager.getUserInfo();
        list = manager.getContent();
        Log.e("aa", list.toString());
        //滚动默认竖直
        meFragmentRecyler = (RecyclerView) view.findViewById(R.id.meFragmentRecyler);
        tv_carnull = (TextView) view.findViewById(R.id.tv_carnull);
        img_carnull = (ImageView) view.findViewById(R.id.img_carnull);
        tv_all = (TextView) view.findViewById(R.id.tv_allprice);
        tv_priceall = (TextView) view.findViewById(R.id.tv_all);
        ck = (CheckBox) view.findViewById(R.id.ck_all);
        tv_count = (TextView) view.findViewById(R.id.tv_count);
        buy = view.findViewById(R.id.buy);
        adapter = new CardAdapter(getActivity());
    }

    private MainActivity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (MainActivity) activity;
        mActivity.setHandler(mHandler);
    }


    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 2) {
                userlist = (ArrayList<User>) msg.obj;
                Toast.makeText(getActivity(), "登陆成功", Toast.LENGTH_SHORT).show();
                adapter.UpdateData(list);
            }
        }
    };

    @Override
    public void before() {
    }

    public void showdata() {
        if (list.size() != 0) {
            tv_carnull.setVisibility(View.GONE);
            img_carnull.setVisibility(View.GONE);
        }
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
        checked();
        delData();
        ck.setOncheckListener(new CheckBox.OnCheckListener() {
            @Override
            public void onCheck(CheckBox checkBox, boolean b) {
                commitlist.clear();
                if (b == true) {
                    int sum = 0;
                    int sum1 = 0;

                    for (int i = 0; i < list.size(); i++) {
                        commitlist.add(list.get(i));

                    }
                    for (int j = 0; j < commitlist.size(); j++) {
                        commitlist.get(j).setIsChecked(true);
                        sum += Integer.parseInt(commitlist.get(j).getPrice_new());
                        sum1 += Integer.parseInt(commitlist.get(j).getPrice_oid().substring(1, commitlist.get(j).getPrice_oid().length()));
                        tv_all.setText("￥" + sum);
                        tv_priceall.setText("￥" + sum1);
                    }
                    Log.e("aa", "sum=" + sum + "  sum1=" + sum1 + "  size=" + commitlist.size());
                    adapter.UpdateData(commitlist);
                    tv_count.setText(commitlist.size() + "");
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        Log.e("msg", "未选中" + list.get(i).isChecked());
                        list.get(i).setIsChecked(false);
                        tv_all.setText("￥" + "0");
                        tv_priceall.setText("￥" + "0");
                        tv_count.setText("(" + 0 + ")");
                        adapter.UpdateData(list);
                    }
                }
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("aa", "提交数据个数=" + commitlist.size());
                Log.e("aa", "用户名=" + userlist.get(0).getUsername());
                Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();
                if (commitlist.size() == 0) {
                    Toast.makeText(getActivity(), "还没有", Toast.LENGTH_SHORT).show();
                }
                ApplayTask task = new ApplayTask(getActivity(), userlist.get(0).getUsername(), commitlist);
                task.execute();
            }
        });

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            list = manager.getContent();
            Log.e("aa", "购物车商品个数" + list.size());
            if (list != null && list.size() != 0) {
                Log.e("aa", "用户个数=" + userlist.size());
                if (userlist.size() > 0) {
                    showdata();
                    adapter.UpdateData(list);
                }
                adapter.UpdateData(list);
            } else {
                Log.e("aa", "购物车为空");
            }
        }
    }

    @Override
    public void after() {
        Log.e("aa", "数据库用户个数" + userlist.size());
        if (userlist.size() == 0) {
            img_carnull.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), 1);
                }
            });
        } else {
            adapter.UpdateData(list);
        }

    }

    public void delData() {
        adapter.setDelClickListener(new CardAdapter.DelClickListener() {
            @Override
            public void onDelData(View v, final int position) {
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        manager.delData(list.get(position).get_id());

                        if (list.get(position).isChecked()) {
                            int ss = 0;
                            for (int j = 0; j < commitlist.size(); j++) {
                                if (commitlist.get(j).getName().equals(list.get(position).getName())) {
                                    ss = j;
                                }
                            }
                            Toast.makeText(getActivity(), "aa" + ss, Toast.LENGTH_SHORT).show();
                            commitlist.remove(ss);
                            tv_count.setText(commitlist.size() + "");
                        }
                        list.remove(position);
                        if (callBack != null) {
                            callBack.getListSize(list.size());
                            Log.e("zzzz", "zhixing==" + list.size());
                        }
                        adapter.UpdateData(list);
                        Toast.makeText(getActivity(), "成功移除购物车", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void checked() {
        adapter.setChecked(new CardAdapter.IsChecked() {
            @Override
            public void success(int position, boolean ischecked) {
                commitlist.add(list.get(position));
                Log.i("msg", "list111" + commitlist);
                tv_count.setText(commitlist.size() + "");
                int sum = 0;
                int sum1 = 0;
                for (int i = 0; i < commitlist.size(); i++) {
                    sum += Integer.parseInt(commitlist.get(i).getPrice_new());
                    sum1 += Integer.parseInt(commitlist.get(i).getPrice_oid().substring(1, commitlist.get(i).getPrice_oid().length()));
                    Log.e("aa", "cccccccc" + sum + "ss" + sum1);
                    tv_all.setText("￥" + sum);
                    tv_priceall.setText("￥" + sum1);

                    adapter.UpdateData(list);
                }


            }

            @Override
            public void defult(int position, boolean noischeckd) {
                Log.i("aa", "posisiont的答应" + position + "");
                int ss = 0;
                for (int j = 0; j < commitlist.size(); j++) {
                    if (commitlist.get(j).getName().equals(list.get(position).getName())) {
                        ss = j;
                    }
                }
                Toast.makeText(getActivity(), "aa" + ss, Toast.LENGTH_SHORT).show();
                commitlist.remove(ss);
                if (commitlist.size() == 0) {
                    tv_all.setText("￥" + 0);
                    tv_priceall.setText("￥" + 0);
                }
                int sum = 0;
                int sum1 = 0;
                for (int i = 0; i < commitlist.size(); i++) {
                    sum += Integer.parseInt(commitlist.get(i).getPrice_new());
                    sum1 += Integer.parseInt(commitlist.get(i).getPrice_oid().substring(1, commitlist.get(i).getPrice_oid().length()));
                    Log.e("aa", "cccccccc" + sum + "ss" + sum1);
                    tv_all.setText("￥" + sum);
                    tv_priceall.setText("￥" + sum1);
                    adapter.UpdateData(list);
                }
                tv_count.setText(commitlist.size() + "");
                Log.i("aa", "ss=" + ss);
            }
        });
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnTouchListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    ListCallBack callBack;

    public ListCallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(ListCallBack callBack) {
        this.callBack = callBack;
    }

    public interface ListCallBack {
        void getListSize(int size);
    }
}
