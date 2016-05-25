package share.top.com.zhlw2.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by ZHOU on 2016/4/7.
 */
public class YiPagerAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<View> list;

    public YiPagerAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    public void Update(ArrayList<View> list) {
        if (list != null) {
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }
}
