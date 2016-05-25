package share.top.com.zhlw2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import share.top.com.zhlw2.R;

/**
 * Created by ZHOU on 2016/4/15.
 */
public class PopWindowAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<String> colorList;
    private ArrayList<String> sizeList;
    private int ITME_TYPE_1 = 0, ITEM_TYPE_2 = 1;
    private StaggeredGridLayoutManager manager;
    private PopWindowApdater2 adapter2;
    private PopWindowAdapter1 adapter1;

    public PopWindowAdapter(Context mContext) {
        this.mContext = mContext;
        colorList = new ArrayList<>();
        sizeList = new ArrayList<>();
        adapter2 = new PopWindowApdater2(mContext);
        adapter1 = new PopWindowAdapter1(mContext);
    }

    public ArrayList<String> getColorList() {
        return colorList;
    }

    public void setColorList(ArrayList<String> colorList) {
        if (colorList != null)
            this.colorList = colorList;
        notifyDataSetChanged();
    }

    public ArrayList<String> getSizeList() {
        return sizeList;
    }

    public void setSizeList(ArrayList<String> sizeList) {
        if (sizeList != null)
            this.sizeList = sizeList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return ITME_TYPE_1;
        else
            return ITEM_TYPE_2;
    }

    class PreantViewHolder1 extends RecyclerView.ViewHolder {
        RecyclerView pop_preant_RecyclerView_1;

        public PreantViewHolder1(View itemView) {
            super(itemView);
            pop_preant_RecyclerView_1 = (RecyclerView) itemView.findViewById(R.id.pop_preant_RecyclerView_1);
        }
    }

    class PreantViewHolder2 extends RecyclerView.ViewHolder {
        RecyclerView pop_preant_RecyclerView_2;

        public PreantViewHolder2(View itemView) {
            super(itemView);
            pop_preant_RecyclerView_2 = (RecyclerView) itemView.findViewById(R.id.pop_preant_RecyclerView_2);
        }
    }

    private View view1, view2;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view1 = LayoutInflater.from(mContext).inflate(R.layout.pop_window_preant_layout_1, null);
        view2 = LayoutInflater.from(mContext).inflate(R.layout.pop_window_preant_layout_2, null);
        if (viewType == ITME_TYPE_1)
            return new PreantViewHolder1(view1);
        else if (viewType == ITEM_TYPE_2)
            return new PreantViewHolder2(view2);
        else
            return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PreantViewHolder1) {
            manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            ((PreantViewHolder1) holder).pop_preant_RecyclerView_1.setLayoutManager(manager);
            ((PreantViewHolder1) holder).pop_preant_RecyclerView_1.setAdapter(adapter1);
        } else if (holder instanceof PreantViewHolder2) {
            manager = new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL);
            ((PreantViewHolder2) holder).pop_preant_RecyclerView_2.setLayoutManager(manager);
            ((PreantViewHolder2) holder).pop_preant_RecyclerView_2.setAdapter(adapter2);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
