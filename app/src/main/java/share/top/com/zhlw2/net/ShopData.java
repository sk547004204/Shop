package share.top.com.zhlw2.net;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import share.top.com.zhlw2.bean.EatInfo;
import share.top.com.zhlw2.bean.ShopCellInfo;
import share.top.com.zhlw2.bean.ShopInfo;
import share.top.com.zhlw2.utils.NetState;
import share.top.com.zhlw2.utils.PinYin;

/**
 * Created by ZHOU on 2016/4/12.
 */
public class ShopData {
    private static ShopData getData;
    private NetState state;
    private Context mContext;
    private ShopCellInfo cell;

    private ShopData(Context mContext) {
        this.mContext = mContext;
        state = NetState.getInstance();
    }

    public static ShopData getInstance(Context mContext) {
        if (getData == null) {
            getData = new ShopData(mContext);
        }
        return getData;
    }

    public final static String YUNDONG = "http://www.yougou.com/f-0-PTK-0-1.html";//运动
    public final static String HUWAI = "http://www.yougou.com/f-0-KDT-0-0.html";//户外
    public final static String NVXIE = "http://www.yougou.com/f-0-MXZ-0-0.html";//女鞋
    public final static String NANXIE = "http://www.yougou.com/f-0-Y0A-04Y004-0.html";//男鞋
    public final static String NANZHUANG = "http://www.yougou.com/f-0-TFA-0-0.html";//男装
    public final static String NVZHUANG = "http://www.yougou.com/f-0-63L-0-0.html";//女装
    public final static String TONGZHUANG = "http://www.yougou.com/f-0-9XB_A0E-0-1.html";//童装
    public final static String JIAJU = "http://www.yougou.com/f-0-FS8-0-0.html";//家具
    public final static String NEIYI = "http://www.yougou.com/f-0-4GM-0-0.html";//内衣
    public final static String XIANGBAO = "http://www.yougou.com/f-0-6LJ-0-0.html";//箱包
    private ArrayList<ShopInfo> list;
    private CallBack callBack;
    String nextpage = null;//下一页地址
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {//获取网页数据
                callBack.linkdata(list);
            } else if (msg.what == 3) {
                if (cell != null)
                    infoCallBack.sellInfoData(cell);
            } else if (msg.what == 4) {
                eatcallback.getEatData(eatlist);
            }
        }
    };
    ArrayList<String> imglist;
    ArrayList<String> colourlist;
    ArrayList<String> sizelist;

    public void getcellDate(String url) {
        list = new ArrayList<>();
        imglist = new ArrayList<>();
        colourlist = new ArrayList<>();
        sizelist = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).timeout(5000).get();
            Elements links1 = document.getElementsByAttributeValue("class", "goodsImg");
            Elements links2 = document.getElementsByAttributeValue("class", "goodsCon fl");
            String num = links1.select("span").get(0).text();
            String name = links2.select("h1").text();
            Elements links3 = document.getElementsByAttributeValue("class", "fl prodSpec");
            Log.e("aaa", "11wq=" + links3);
            Elements ww = links3.select("img");
            for (int i = 0; i < ww.size(); i++) {
                colourlist.add(ww.get(i).attr("src"));
            }
            Log.e("aaa", "colourlist==" + colourlist.size() + "neir=" + colourlist);
            Elements links4 = document.getElementsByAttributeValue("class", "fl prodSpec size");
            Log.e("aaa", "!!!link4=   " + links4);
            Elements rr = links4.select("a");
            for (int j = 0; j < rr.size(); j++) {
                sizelist.add(rr.get(j).text());
            }
            Log.e("aaa", "  sizelist=" + sizelist.size() + "   " + sizelist);
            Elements links44 = document.getElementsByAttributeValue("class", "list-h");
            Elements ss = links44.select("img");
            for (int a = 0; a < ss.size(); a++) {
                imglist.add(ss.get(a).attr("picLargeUrl"));
            }
            cell = new ShopCellInfo(name, imglist, colourlist, sizelist, num);
            Log.e("aaa", "cell   " + cell.toString());
            Message ms = new Message();
            ms.obj = cell;
            ms.what = 3;
            handler.sendMessage(ms);
        } catch (Exception e) {
            Log.e("aaa", "连接失败" + e.toString());
        }
    }

    public void getSellInfoData(final String url, SellInfoCallBack back) {
        this.infoCallBack = back;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getcellDate(url);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

//    public void getEatInfoData(final String url,){}

    public void getLinkData(final String url) {
        list = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).timeout(5000).get();
            Elements links = document.getElementsByAttributeValue("class", "srchlst-wrap");
            Elements linkss = document.getElementsByAttributeValue("class", "page");
            for (Element link : linkss) {
                nextpage = link.select("a").attr("href");
                Message ms = new Message();
                ms.obj = nextpage;
                ms.what = 2;
                handler.sendMessage(ms);
            }
            for (Element link : links) {
                String imgs = link.select("img").attr("original");
                String name = link.select("a").attr("title");
                String price_new = link.select("i").get(0).text();
                String price_oid = link.select("del").get(0).text();
                String urls = link.select("a").attr("href");
                list.add(new ShopInfo(name, imgs, price_new, price_oid, urls, nextpage));
            }
            Message ms = new Message();
            ms.obj = list;
            ms.what = 1;
            handler.sendMessage(ms);
        } catch (Exception e) {
            Log.e("aa", "连接失败" + e.toString());
        }
    }

    /**
     * 接口回掉
     */
    public interface CallBack {
        void linkdata(ArrayList<ShopInfo> list);
    }

    private SellInfoCallBack infoCallBack;

    public SellInfoCallBack getInfoCallBack() {
        return infoCallBack;
    }

    public void setInfoCallBack(SellInfoCallBack infoCallBack) {
        this.infoCallBack = infoCallBack;
    }

    public interface SellInfoCallBack {
        void sellInfoData(ShopCellInfo info);
    }

    /**
     * 获取数据方法
     * 第一个参数 url 链接
     * 第二个参数 callback 接口
     */
    public void getData(final String url, CallBack callBack) {
        this.callBack = callBack;
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                if (state.isNetworkAvailable(mContext))
                    getLinkData(url);
            }
        });
        th.setDaemon(true);
        th.start();
    }

    /*******
     * ---------------------------------------------
     ********/
    ArrayList<EatInfo> eatlist = new ArrayList<>();

    public void getEatInfo(String names) {
        try {
            String urls = PinYin.cn2Spell(names);
            Document document = Jsoup.connect("http://" + urls + ".lashou.com/cate/meishi").timeout(5000).get();
            Elements links = document.getElementsByAttributeValue("class", "goods");
            for (Element link : links) {
                String url = link.select("a").first().attr("href");
                String name = link.getElementsByAttributeValue("class", "goods-name").text();
                String youhui = link.getElementsByAttributeValue("class", "goods-text").text();
                String adress = link.select("span").first().text();
                String img = link.select("img").attr("imgsrc");
                String price_new = link.getElementsByAttributeValue("class", "price").text();
                String price_old = link.getElementsByAttributeValue("class", "money").text();
                String buynumber = link.getElementsByAttributeValue("class", "number").text();
                eatlist.add(new EatInfo(url, name, youhui, adress, price_new, price_old, buynumber, img));
            }
            Message ms = new Message();
            ms.obj = eatlist;
            ms.what = 4;
            handler.sendMessage(ms);
        } catch (Exception e) {
            Log.e("aaa", "连接失败" + e.toString());
        }
    }

    public interface EatCallBack {
        void getEatData(ArrayList<EatInfo> list);
    }

    private EatCallBack eatcallback;

    public void getEating(final String name, EatCallBack eatcallback) {
        this.eatcallback = eatcallback;
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                getEatInfo(name);
            }
        });
        th.setDaemon(true);
        th.start();
    }
}
