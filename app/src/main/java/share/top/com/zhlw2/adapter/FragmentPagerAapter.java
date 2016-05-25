package share.top.com.zhlw2.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by ZHOU on 2016/4/13.
 */
public class FragmentPagerAapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> list;
    private Context mContext;

    public FragmentPagerAapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    public void UpList(ArrayList<Fragment> list) {
        if (list != null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }
}
