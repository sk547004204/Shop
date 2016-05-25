package share.top.com.zhlw2.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import share.top.com.zhlw2.R;

/**
 * Created by ZHOU on 2016/4/19.
 */
public class mGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> list;
    private LayoutInflater mInflater;

    public mGridViewAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
        mInflater = LayoutInflater.from(mContext);
    }


    public void UpGridList(ArrayList<String> list) {
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
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        TextView item2citytext;
    }

    ViewHolder holder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item2_adapter_look_layout, null);
            holder.item2citytext = (TextView) convertView.findViewById(R.id.item2citytext);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.item2citytext.setText(list.get(position));
        return convertView;
    }
}
