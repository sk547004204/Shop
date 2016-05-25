package share.top.com.zhlw2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import share.top.com.zhlw2.R;

/**
 * Created by ZHOU on 2016/4/19.
 */
public class Item2FragmetAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<String> list;
    private int ITEM_TYPE_0 = 0;
    private ArrayList<View> viewList;

    public Item2FragmetAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
        viewList = new ArrayList<>();
    }

    public void UpDateList(ArrayList<String> list) {
        if (list != null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    public void UpViewDateList(ArrayList<View> viewList) {
        if (viewList != null) {
            this.viewList = viewList;
        }
        notifyDataSetChanged();
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView item2citytext;

        public ViewHolder1(View itemView) {
            super(itemView);
            item2citytext = (TextView) itemView.findViewById(R.id.item2citytext);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item2_adapter_look_layout, null);
        return new ViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder1) {
            ((ViewHolder1) holder).item2citytext.setText(list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
