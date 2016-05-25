package share.top.com.zhlw2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import share.top.com.zhlw2.R;

/**
 * Created by ZHOU on 2016/4/15.
 */
public class PopWindowAdapter1 extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<String> colorList;

    public PopWindowAdapter1(Context mContext) {
        this.mContext = mContext;
        colorList = new ArrayList<>();
    }

    class PopWindow1 extends RecyclerView.ViewHolder {
        TextView popItemText;

        public PopWindow1(View itemView) {
            super(itemView);
            popItemText = (TextView) itemView.findViewById(R.id.popItemText);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_item_layout_1, null);
        return new PopWindow1(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PopWindow1 popWindow1 = (PopWindow1) holder;
        popWindow1.popItemText.setText(colorList.get(position));
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }
}
