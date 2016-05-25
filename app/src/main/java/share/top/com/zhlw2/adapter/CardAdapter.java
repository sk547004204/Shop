package share.top.com.zhlw2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.CheckBox;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;

import share.top.com.zhlw2.R;
import share.top.com.zhlw2.bean.ShopInfo;

/**
 * Created by luxinhua on 2016/4/14.
 */
public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mcontext;
    private LayoutInflater mInflater;
    private DisplayImageOptions options;
    private ArrayList<ShopInfo> list;

    public CardAdapter(Context mcontext) {
        this.mcontext = mcontext;
        mInflater = LayoutInflater.from(mcontext);
        list = new ArrayList<>();
        options = new DisplayImageOptions.Builder().cacheInMemory(true).showStubImage(R.mipmap.logo2)// 设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.logo2)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.logo2)// 设置图片加载/解码过程中错误时候显示的图片
                .showImageOnLoading(R.mipmap.logo2)
                .cacheOnDisk(true).imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    public void AddData(ArrayList<ShopInfo> list) {
        if (list != null) {
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void UpdateData(ArrayList<ShopInfo> list) {
        if (list != null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    public interface OnItemCallBackClick {
        void OnItemListener(View view, int position);
    }
    private OnItemCallBackClick click;//item点击的回调
    public OnItemCallBackClick getClick() {
        return click;
    }
    public void setClick(OnItemCallBackClick click) {
        this.click = click;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mcontext).inflate(R.layout.itemcard, null);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click != null) {
                    click.OnItemListener(v, (Integer) v.getTag());

                }
            }
        });

        return new CardHolder(view);
    }
    public interface IsChecked {
        void success(int position, boolean ischecked);
        void defult(int position, boolean noischeckd);
    }
    private IsChecked checked;

    public IsChecked getChecked() {
        return checked;
    }
    public void setChecked(IsChecked checked) {
        this.checked = checked;
    }


    public interface DelClickListener{
        void onDelData(View v,int position);
    }
    private DelClickListener delClickListener;

    public DelClickListener getDelClickListener() {
        return delClickListener;
    }

    public void setDelClickListener(DelClickListener delClickListener) {
        this.delClickListener = delClickListener;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        CardHolder cardHolder = (CardHolder) holder;
        ShopInfo info = list.get(position);
        cardHolder.tv_price.setText("￥" + info.getPrice_new());
        cardHolder.tv_name.setText(info.getName());
        cardHolder.ck.setChecked(info.isChecked());
        if (delClickListener!=null)
        delClickListener.onDelData(cardHolder.delImg,position);
        cardHolder.ck.setOncheckListener(new CheckBox.OnCheckListener() {
            @Override
            public void onCheck(CheckBox checkBox, boolean b) {
                if (b) {
                    list.get(position).setIsChecked(true);
                    if (checked != null) {
                        checked.success(position, b);
//                        Toast.makeText(mcontext, "list的选择框=" + list.get(position).isChecked(), Toast.LENGTH_LONG).show();

                    }
                } else {
                    if (checked != null)
                        checked.defult(position, false);
                    list.get(position).setIsChecked(false);
//                    Toast.makeText(mcontext, "list的选择框=" + list.get(position).isChecked(), Toast.LENGTH_LONG).show();
                }

            }
        });

        if (list.get(position).getImg() != null) {
            ImageLoader.getInstance().displayImage(list.get(position).getImg(), cardHolder.img, options);
        }
        cardHolder.itemView.setTag(position);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}

class CardHolder extends RecyclerView.ViewHolder {
    View rootview;
    TextView tv_name, tv_price;
    ImageView img;
    CheckBox ck;
    ImageView delImg;
    public CardHolder(View itemView) {
        super(itemView);
        tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        tv_price = (TextView) itemView.findViewById(R.id.tv_price);
        img = (ImageView) itemView.findViewById(R.id.img);
        ck = (CheckBox) itemView.findViewById(R.id.img_checkbox);
        delImg= (ImageView) itemView.findViewById(R.id.deleteImag);
    }
}