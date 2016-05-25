package share.top.com.zhlw2.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ZHOU on 2016/4/8.
 */
public abstract class BaseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = setContentView(inflater, container, savedInstanceState);
        before();
        initView();
        after();
        return view;
    }

    public abstract View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract void initView();

    public abstract void before();

    public abstract void after();
}
