package share.top.com.zhlw2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by ZHOU on 2016/4/7.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    private Context mContext;
    private ArrayList<T> list;
    private LayoutInflater mInflater;

    public MyBaseAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
        mInflater = LayoutInflater.from(mContext);
    }

    public void addList(ArrayList<T> list) {
        if (list != null) {
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void Update(ArrayList<T> list) {
        if (list != null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    public int getCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public T getItem(int position) {
        if (list == null || list.size() <= 0)
            return null;
        if (position > list.size())
            return null;
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return getMyView(position, convertView, parent);
    }

    public abstract View getMyView(int position, View convertView, ViewGroup parent);

}
