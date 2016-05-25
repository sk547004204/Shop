package share.top.com.zhlw2.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hpp on 2016/4/13.
 */
public class ShopCellInfo implements Serializable {
    private String name;
    private ArrayList<String> imglist;
    private ArrayList<String> colourlist;
    private ArrayList<String> sizelist;
    private String num;//bianhao

    public ShopCellInfo(String name, ArrayList<String> imglist, ArrayList<String> colourlist, ArrayList<String> sizelist, String num) {
        this.name = name;
        this.imglist = imglist;
        this.colourlist = colourlist;
        this.sizelist = sizelist;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getImglist() {
        return imglist;
    }

    public void setImglist(ArrayList<String> imglist) {
        this.imglist = imglist;
    }


    public ArrayList<String> getColourlist() {
        return colourlist;
    }

    public void setColourlist(ArrayList<String> colourlist) {
        this.colourlist = colourlist;
    }

    public ArrayList<String> getSizelist() {
        return sizelist;
    }

    public void setSizelist(ArrayList<String> sizelist) {
        this.sizelist = sizelist;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "ShopCellInfo{" +
                "name='" + name + '\'' +
                ", imglist=" + imglist +
                ", colourlist=" + colourlist +
                ", sizelist=" + sizelist +
                ", num='" + num + '\'' +
                '}';
    }
}
