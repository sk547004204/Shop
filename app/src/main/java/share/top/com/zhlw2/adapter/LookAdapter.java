package share.top.com.zhlw2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import share.top.com.zhlw2.R;

/**
 * Created by ZHOU on 2016/4/14.
 */
public class LookAdapter extends PagerAdapter {

    private int index;
    private Context mContext;
    private ArrayList<SimpleDraweeView> list;//控件的个数
    private ArrayList<String> listUrl;//图片地址的个数

    public void getIndex(int index) {
        this.index = index;
    }

    public LookAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
        listUrl = new ArrayList<>();
        Fresco.initialize(mContext);
    }

    public void UpDataList(ArrayList<SimpleDraweeView> list) {
        if (list != null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }


    public void UpDataUrl(ArrayList<String> list) {
        if (list != null) {
            listUrl = list;
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
    public Object instantiateItem(ViewGroup container, final int position) {
        container.addView(list.get(position));
        if (index == 2) {
            if (list.get(position) != null) {
                Uri uri = Uri.parse(listUrl.get(position));
                list.get(position).setImageURI(uri);
            } else {
                list.get(position).setImageResource(R.mipmap.logo2);
            }
        } else if (index == 1) {
            if (list.get(position) != null) {
                Log.i("msg", "adapter中的list的集合的大小为:" + list.size() + "");
                Uri uri = Uri.parse(listUrl.get(position));
                list.get(position).setImageURI(uri);
            } else {
                list.get(position).setImageResource(R.mipmap.logo2);
            }
        } else {
            if (list.get(position) != null) {
                Uri uri = Uri.parse(listUrl.get(position));
                list.get(position).setImageURI(uri);
            } else {
                list.get(position).setImageResource(R.mipmap.logo2);
            }
        }
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }
}
