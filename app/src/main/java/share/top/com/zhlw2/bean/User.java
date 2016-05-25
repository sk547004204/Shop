package share.top.com.zhlw2.bean;

import java.io.Serializable;

/**
 * Created by ZHOU on 2016/4/7.
 */
public class User implements Serializable {
    private int id;
    private String username;
    private String address;
    private int vip;

    public User() {
    }

    public User(int id, String username, String address, int vip) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.vip = vip;
    }
    public User(String username, String address, int vip) {

        this.username = username;
        this.address = address;
        this.vip = vip;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }
}
