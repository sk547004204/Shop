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
 * Created by ZHOU on 2016/4/13.
 */
public class FragmentItem1Adapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<ShopInfo> list;
    private DisplayImageOptions options;

    public FragmentItem1Adapter(Context mContext) {
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

    public interface OnItemCallBackClick {
        void OnItemListener(View view, int postion);
    }

    private OnItemCallBackClick click;

    public OnItemCallBackClick getClick() {
        return click;
    }

    public void setClick(OnItemCallBackClick click) {
        this.click = click;
    }

    public void UpDate(ArrayList<ShopInfo> list) {
        if (list != null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }


    class ViewHolder1 extends RecyclerView.ViewHolder {
        ImageView fragment_item_image;
        TextView fragment_item_text;

        public ViewHolder1(View itemView) {
            super(itemView);
            fragment_item_image = (ImageView) itemView.findViewById(R.id.fragment_item_image);
            fragment_item_text = (TextView) itemView.findViewById(R.id.fragment_item_text);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_item_1_layout, null);
        ViewHolder1 holder1 = new ViewHolder1(view);
        holder1.setIsRecyclable(true);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click != null) {
                    click.OnItemListener(v, (Integer) v.getTag());
                }
            }
        });
        return holder1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder1) holder).fragment_item_text.setText(list.get(position).getName());
        if (list.get(position).getImg() != null) {
            //采用ImageLoader框架加载图片
            ImageLoader.getInstance().displayImage(list.get(position).getImg(), ((ViewHolder1) holder).fragment_item_image, options);
        } else {
            ((ViewHolder1) holder).fragment_item_image.setImageResource(R.mipmap.logo2);
        }
        ((ViewHolder1) holder).itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
