package share.top.com.zhlw2.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import share.top.com.zhlw2.R;
import share.top.com.zhlw2.bean.ShopInfo;
import share.top.com.zhlw2.utils.ShopConnUtil;

/**
 * Created by ZHOU on 2016/4/8.
 */
public class HomeFragmentRecyclerAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<View> list;
    public final int ITEM_TYPE_1 = 0,
            ITEM_TYPE_2 = 1, ITEM_TYPE_3 = 2;
    private LookAdapter adapter;
    private ArrayList<ShopInfo> listInfo;
    private Map<String, ArrayList<ShopInfo>> map;
    private String[] urls = {"http://i1.ygimg.cn/pics/hstyle/2016/100409738/100409738_01_m.jpg?6", "http://i1.ygimg.cn/pics/hstyle/2016/100409745/100409745_01_m.jpg?3",
            "http://i1.ygimg.cn/pics/hstyle/2016/100409752/100409752_01_m.jpg?5", "http://i1.ygimg.cn/pics/hstyle/2016/100409734/100409734_01_m.jpg?3"};
    private Random random = new Random();

    public HomeFragmentRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
        adapter = new LookAdapter(mContext);
        listInfo = new ArrayList<>();
        map = new HashMap<>();
//        Fresco.initialize(mContext);
    }

    public void Update(ArrayList<View> list) {
        if (list != null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    public void UpdateMap(Map<String, ArrayList<ShopInfo>> map) {
        if (!map.isEmpty()) {
            for (String s : map.keySet()) {
                this.map.put(s, map.get(s));
            }
        }
        notifyDataSetChanged();
    }

    public void UpdateShopInfo(ArrayList<ShopInfo> listInfo) {
        if (listInfo != null) {
            this.listInfo = listInfo;
        }
        notifyDataSetChanged();
    }

    public interface ItemLayoutCilck {
        //item2 item3 调用的接口
        void itemLayoutListener(View view);

        //普通的item调用的接口
        void itemListener(View view, int position);
    }

    public interface EatCallBack {
        void itemEatListener(View view);
    }

    public EatCallBack back;

    public EatCallBack getBack() {
        return back;
    }

    public void setBack(EatCallBack back) {
        this.back = back;
    }

    private ItemLayoutCilck cilck;

    public ItemLayoutCilck getCilck() {
        return cilck;
    }

    public void setCilck(ItemLayoutCilck cilck) {
        this.cilck = cilck;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE_1;
        } else if (position == 1) {
            return ITEM_TYPE_2;
        } else if (position == 2) {
            return ITEM_TYPE_3;
        }
        return ITEM_TYPE_3;
    }

    View[] points = new View[3];

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        ViewPager HomeFragmentViewPager_1;

        public ViewHolder1(View itemView) {
            super(itemView);
            HomeFragmentViewPager_1 = (ViewPager) itemView.findViewById(R.id.HomeFragmentViewPager_1);
            HomeFragmentViewPager_1.setAdapter(adapter);
            points[0] = itemView.findViewById(R.id.onepagerView);
            points[1] = itemView.findViewById(R.id.twopagerView);
            points[2] = itemView.findViewById(R.id.threepagerView);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        LinearLayout homeFragmentlayout;
        ImageView kuziImage, yifuImage, waziImage, yifuText;
        TextView moreText;

        public ViewHolder2(View itemView) {
            super(itemView);
            homeFragmentlayout = (LinearLayout) itemView.findViewById(R.id.homeFragmentlayout);
            kuziImage = (ImageView) itemView.findViewById(R.id.kuziImage);
            yifuImage = (ImageView) itemView.findViewById(R.id.yifuImage);
            waziImage = (ImageView) itemView.findViewById(R.id.waziImage);
            yifuText = (ImageView) itemView.findViewById(R.id.yifuText);
            moreText = (TextView) itemView.findViewById(R.id.moreText);
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder {
        ImageView item3Image5, item3Image6, item3Imageone, item3Image2, item3Image3;
        TextView item3Text4;

        public ViewHolder3(View itemView) {
            super(itemView);
            item3Image5 = (ImageView) itemView.findViewById(R.id.item3Image5);
            item3Image6 = (ImageView) itemView.findViewById(R.id.item3Image6);
            item3Imageone = (ImageView) itemView.findViewById(R.id.item3Imageone);
            item3Image2 = (ImageView) itemView.findViewById(R.id.item3Image2);
            item3Image3 = (ImageView) itemView.findViewById(R.id.item3Image3);
            item3Text4 = (TextView) itemView.findViewById(R.id.item3Text4);
        }
    }

    private View view1, view2, view3;
    private LinearLayout[] layouts = new LinearLayout[4];
    private LinearLayout[] eatlayouts = new LinearLayout[6];
    private ImageView[] images = new ImageView[3];
    private ImageView imageView;
    private ArrayList<String> imagelist = new ArrayList<>();
    private ArrayList<SimpleDraweeView> SimpleList = new ArrayList<>();
    private SimpleDraweeView[] imags;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view1 = LayoutInflater.from(mContext).inflate(R.layout.home_fragment_adapter_item_layout, null);
        view2 = LayoutInflater.from(mContext).inflate(R.layout.home_fragment_adapter_item_layout_2, null);
        view3 = LayoutInflater.from(mContext).inflate(R.layout.home_fragment_adapter_item_layout_3, null);
        if (viewType == 0) {
            for (int i = 0; i < images.length; i++) {
                imageView = new ImageView(mContext);
                imagelist.add(urls[i]);
                images[i] = imageView;
                images[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                images[i].setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
            //给viewpager添加数据
            imags = new SimpleDraweeView[imagelist.size()];
            if (imags.length != 0 && imags != null) {
                for (int i = 0; i < imags.length; i++) {
                    imags[i] = new SimpleDraweeView(mContext);
                    imags[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    imags[i].setScaleType(ImageView.ScaleType.FIT_CENTER);
                    SimpleList.add(imags[i]);
                    adapter.UpDataList(SimpleList);
                }
            }
            adapter.UpDataUrl(imagelist);
            return new ViewHolder1(view1);
        } else if (viewType == 1) {
            layouts[0] = (LinearLayout) view2.findViewById(R.id.item2layout1);
            layouts[1] = (LinearLayout) view2.findViewById(R.id.item2layout2);
            layouts[2] = (LinearLayout) view2.findViewById(R.id.item2layout3);
            layouts[3] = (LinearLayout) view2.findViewById(R.id.item2layout4);
            for (final LinearLayout layout : layouts) {
                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cilck != null) {
                            cilck.itemLayoutListener(layout);
                        }
                    }
                });
            }
            return new ViewHolder2(view2);
        } else if (viewType == 2) {
            eatlayouts[0] = (LinearLayout) view3.findViewById(R.id.eatlayout1);
            eatlayouts[1] = (LinearLayout) view3.findViewById(R.id.eatlayout2);
            eatlayouts[2] = (LinearLayout) view3.findViewById(R.id.eatlayout3);
            eatlayouts[3] = (LinearLayout) view3.findViewById(R.id.eatlayout4);
            eatlayouts[4] = (LinearLayout) view3.findViewById(R.id.eatlayout5);
            eatlayouts[5] = (LinearLayout) view3.findViewById(R.id.eatlayout6);
            for (final LinearLayout layout : eatlayouts) {
                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (back != null) {
                            back.itemEatListener(layout);
                        }
                    }
                });
            }
            return new ViewHolder3(view3);
        }
        return null;
    }

    int viewpager_index = 0;
    private ShopConnUtil util;

    public void change_PointColor(int lastIndex, int newIndex) {
        //老的坐标变成黑色,adware_style_default
        points[lastIndex].setBackgroundResource(R.mipmap.adware_style_default);
        points[newIndex].setBackgroundResource(R.mipmap.adware_style_selected);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        util = ShopConnUtil.getInstance();
        if (holder instanceof ViewHolder1) {
            ((ViewHolder1) holder).HomeFragmentViewPager_1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    change_PointColor(viewpager_index, position % points.length);
                    viewpager_index = position % points.length;
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } else if (holder instanceof ViewHolder2) {
            if (listInfo.size() != 0) {
                int ss = random.nextInt(3) + 1;

                //Uri uri = Uri.parse(listUrl.get(position));
//                list.get(position).setImageURI(uri);
//                ((ViewHolder2) holder).kuziImage.setImageURI(Uri.parse(urls[ss]));
//                ((ViewHolder2) holder).yifuText.setImageURI(Uri.parse("http://i1.ygimg.cn/pics/huaimei/2016/100414017/100414017_01_m.jpg?1"));
//                ((ViewHolder2) holder).yifuImage.setImageURI(Uri.parse("http://i1.ygimg.cn/pics/huaimei/2016/100414017/100414017_01_m.jpg?1"));
//                ((ViewHolder2) holder).waziImage.setImageURI(Uri.parse(urls[ss]));
                util.imageLoader(mContext, ((ViewHolder2) holder).kuziImage, urls[ss], 0, 0);
                util.imageLoader(mContext, ((ViewHolder2) holder).yifuText, "http://i1.ygimg.cn/pics/huaimei/2016/100414017/100414017_01_m.jpg?1", 0, 0);
                util.imageLoader(mContext, ((ViewHolder2) holder).yifuImage, "http://i1.ygimg.cn/pics/huaimei/2016/100414017/100414017_01_m.jpg?1", 0, 0);
                util.imageLoader(mContext, ((ViewHolder2) holder).waziImage, listInfo.get(3).getImg(), 0, 0);
                ((ViewHolder2) holder).moreText.setText("更多");
            }
        } else if (holder instanceof ViewHolder3) {
            util.imageLoader(mContext, ((ViewHolder3) holder).item3Image5, "http://d2.lashouimg.com/cms/M00/E8/19/CqgBVFXas1iAYESwAACpYORfZXk550-440x280.jpg", 0, 0);
            util.imageLoader(mContext, ((ViewHolder3) holder).item3Image2, "http://f2.lashouimg.com/cms/M00/B4/FD/CqgBJlTZ2ReAMkPPAADWZtV_cl8854-440x280.jpg", 0, 0);
            util.imageLoader(mContext, ((ViewHolder3) holder).item3Imageone, "http://d1.lashouimg.com/cms/M00/1F/E4/CqgBJlNgVmCAA8U-AAEnLz_wW0Q265-440x280.jpg", 0, 0);
            util.imageLoader(mContext, ((ViewHolder3) holder).item3Image3, "http://d2.lashouimg.com/cms/M00/04/2C/CqgBJlbg0NCAQU9jAADq6Ux2zfk560-440x280.jpg", 0, 0);
            util.imageLoader(mContext, ((ViewHolder3) holder).item3Image6, "http://f3.lashouimg.com/cms/M00/3D/9F/CqgBHlOzwXGAMoofAADr3Y9Wwk0679-440x280.jpg", 0, 0);
            ((ViewHolder3) holder).item3Text4.setText("更多");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
