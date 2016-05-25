package share.top.com.zhlw2.bean;

/**
 * Created by luxinhua on 2016/4/27.
 */
public class LocationInfo {
    /**
     * errcode : 0
     * lat : 30.587381
     * lon : 104.051903
     * radius : 771
     * address : 四川省成都市武侯区石羊场街道锦晖西二街;锦晖西二街与成汉南路路口南54米
     */

    private int errcode;
    private String lat;
    private String lon;
    private String radius;
    private String address;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
