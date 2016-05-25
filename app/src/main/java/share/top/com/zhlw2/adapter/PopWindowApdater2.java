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
public class PopWindowApdater2 extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<String> sizeList;
    private PopWindowAdapter adapter;

    public PopWindowApdater2(Context mContext) {
        this.mContext = mContext;
        adapter = new PopWindowAdapter(mContext);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                sizeList = adapter.getSizeList();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    class SizeViewHolder extends RecyclerView.ViewHolder {
        TextView popItemText;

        public SizeViewHolder(View itemView) {
            super(itemView);
            popItemText = (TextView) itemView.findViewById(R.id.popItemText);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_item_layout_1, null);
        return new SizeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SizeViewHolder) {
            ((SizeViewHolder) holder).popItemText.setText(sizeList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return sizeList.size();
    }
}
