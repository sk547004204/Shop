package share.top.com.zhlw2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;

import share.top.com.zhlw2.R;
import share.top.com.zhlw2.bean.ShopInfo;

/**
 * Created by ZHOU on 2016/4/12.
 */
public class item1RecyclerAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<ShopInfo> list;
    private DisplayImageOptions options;

    public item1RecyclerAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
        options = new DisplayImageOptions.Builder().cacheInMemory(true).showStubImage(R.mipmap.logo2)// 设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.logo2)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.logo2)// 设置图片加载/解码过程中错误时候显示的图片
                .showImageOnLoading(R.mipmap.logo2)
                .cacheOnDisk(true).imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    public void UpDate(ArrayList<ShopInfo> list) {
        if (list != null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemAdapterImage;
        TextView itemAdapterName, newPriceText, oldPriceText;

        public ViewHolder(View itemView) {
            super(itemView);
            itemAdapterImage = (ImageView) itemView.findViewById(R.id.itemAdapterImage);
            itemAdapterName = (TextView) itemView.findViewById(R.id.itemAdapterName);
            newPriceText = (TextView) itemView.findViewById(R.id.newPriceText);
            oldPriceText = (TextView) itemView.findViewById(R.id.oldPriceText);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_activity_adapter_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).itemAdapterName.setText(list.get(position).getName());
            ((ViewHolder) holder).newPriceText.setText("￥" + list.get(position).getPrice_new());
            ((ViewHolder) holder).oldPriceText.setText(list.get(position).getPrice_oid());
            if (list.get(position).getImg() != null) {
                //采用ImageLoader框架加载图片
                ImageLoader.getInstance().displayImage(list.get(position).getImg(), ((ViewHolder) holder).itemAdapterImage, options);
            } else {
                ((ViewHolder) holder).itemAdapterImage.setImageResource(R.mipmap.logo2);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
