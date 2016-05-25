package share.top.com.zhlw2.bean;

import java.io.Serializable;

/**
 * Created by ZHOU on 2016/4/7.
 */
public class ShopInfo implements Serializable {
    private int _id;
    private String name;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    private String img;
    private String price_new;
    private String price_oid;
    private String nextpage;
    private String url;
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ShopInfo(int _id, String name, String img, String price_new, String price_oid) {
        this._id = _id;
        this.name = name;
        this.img = img;
        this.price_new = price_new;
        this.price_oid = price_oid;
    }


    public ShopInfo(String name, String img, String price_new, String price_oid, String url, String nextpage) {
        this.name = name;
        this.img = img;
        this.price_new = price_new;
        this.price_oid = price_oid;
        this.url = url;
        this.nextpage = nextpage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice_new() {
        return price_new;
    }

    public void setPrice_new(String price_new) {
        this.price_new = price_new;
    }

    public String getPrice_oid() {
        return price_oid;
    }

    public void setPrice_oid(String price_oid) {
        this.price_oid = price_oid;
    }

    public String getNextpage() {
        return nextpage;
    }

    public void setNextpage(String nextpage) {
        this.nextpage = nextpage;
    }

    @Override
    public String toString() {
        return "ShopInfo{" +
                "nextpage='" + nextpage + '\'' +
                ", price_oid='" + price_oid + '\'' +
                ", price_new='" + price_new + '\'' +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
