package share.top.com.zhlw2.bean;

import java.io.Serializable;

/**
 * Created by HP on 2016/4/11.
 */
public class EatInfo  implements Serializable{
    private String url;
    private String name;
    private String youhui;
    private String adress;
    private String price_new;
    private String price_old;
    private String buynumber;
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public EatInfo(String url, String name, String youhui, String adress, String price_new, String price_old, String buynumber, String img) {
        this.url = url;
        this.name = name;
        this.youhui = youhui;
        this.adress = adress;
        this.price_new = price_new;
        this.price_old = price_old;
        this.buynumber = buynumber;
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYouhui() {
        return youhui;
    }

    public void setYouhui(String youhui) {
        this.youhui = youhui;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPrice_new() {
        return price_new;
    }

    public void setPrice_new(String price_new) {
        this.price_new = price_new;
    }

    public String getPrice_old() {
        return price_old;
    }

    public void setPrice_old(String price_old) {
        this.price_old = price_old;
    }

    public String getBuynumber() {
        return buynumber;
    }

    public void setBuynumber(String buynumber) {
        this.buynumber = buynumber;
    }

    @Override
    public String toString() {
        return "EatInfo1{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", youhui='" + youhui + '\'' +
                ", adress='" + adress + '\'' +
                ", price_new='" + price_new + '\'' +
                ", price_old='" + price_old + '\'' +
                ", buynumber='" + buynumber + '\'' +
                '}';
    }
}
