package share.top.com.zhlw2.db;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import share.top.com.zhlw2.bean.ShopInfo;
import share.top.com.zhlw2.bean.User;


/**
 * Created by ZHOU on 2016/4/7.
 */
public class MyDBManager {

    private Context mContext;
    private static MyDBManager manager;
    private SQLiteDatabase db;
    private MyDbHelper helper;

    private MyDBManager(Context mContext) {
        this.mContext = mContext;
        helper = new MyDbHelper(mContext);
        db = helper.getWritableDatabase();
        db = helper.getReadableDatabase();
    }

    public static MyDBManager getInstance(Context mContext) {
        if (manager == null)
            manager = new MyDBManager(mContext);
        return manager;
    }

    //添加用户
    public void addUserToSql(User user) {
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.USERNAME, user.getUsername());
        values.put(MyDbHelper.ADDRESS, user.getAddress());
//        values.put(MyDbHelper.PHONENUMBER, user.getPhonenumber());
        values.put(MyDbHelper.VIP, user.getVip());
        db.insert(MyDbHelper.USER, null, values);
    }
    public  ArrayList<User> getUserInfo(){

        String name=null;
        String addrss=null;
        int vip=0;
        int id=0;
        ArrayList<User>list=new ArrayList<>();
        String sql = "select * from " + MyDbHelper.USER;
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("aa", "cursor" + cursor);
        while (cursor.moveToNext()) {
            id=cursor.getInt(cursor.getColumnIndex(MyDbHelper.ID));
            name= cursor.getString(cursor.getColumnIndex(MyDbHelper.USERNAME));
            addrss=cursor.getString(cursor.getColumnIndex(MyDbHelper.ADDRESS));
            vip=cursor.getInt(cursor.getColumnIndex(MyDbHelper.VIP));
            list.add(new User(id,name,addrss,vip));
        }
        cursor.close();
        return list;
    }
    public  ArrayList<String> lookIsHave() {
        String name=null;
        ArrayList<String> list = new ArrayList<>();
        String sql = "select * from " + MyDbHelper.TABLE_NBAME;
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("aa", "cursor" + cursor);
        while (cursor.moveToNext()) {
            name= cursor.getString(cursor.getColumnIndex(MyDbHelper.SHOP_NAME));
            list.add(name);
        }
        cursor.close();
        Log.e("aa","list="+list+"");
        return list;
    }
    //    //添加商品
    public void addShopToSql(ShopInfo info) {
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.SHOP_NAME, info.getName());
        values.put(MyDbHelper.SHOP_PRICE_NEWS, info.getPrice_new());
        values.put(MyDbHelper.SHOP_PRICE_OLD, info.getPrice_oid());
        values.put(MyDbHelper.SHOP_IMAGEURL, info.getImg());
        values.put(MyDbHelper.SHOP_URL, info.getUrl());
        db.insert(MyDbHelper.TABLE_NBAME, null, values);
    }

    private int id = 0;
    private String ShopName = null;
    private String newprice;
    private String oldprice;
    private int price = 0;
    private int count = 0;//交易量
    private int ping;//评价多少条
    private String imgUrl = null;
    private String describe = null;
    private ArrayList<ShopInfo> list;
    private String shopurl;

    public ArrayList<ShopInfo> getContent() {
        String sql = " select * from " + MyDbHelper.TABLE_NBAME;
        list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                id=cursor.getInt(cursor.getColumnIndex(MyDbHelper.ID));
                ShopName = cursor.getString(cursor.getColumnIndex(MyDbHelper.SHOP_NAME));
                newprice = cursor.getString(cursor.getColumnIndex(MyDbHelper.SHOP_PRICE_NEWS));
                oldprice = cursor.getString(cursor.getColumnIndex(MyDbHelper.SHOP_PRICE_OLD));
                imgUrl = cursor.getString(cursor.getColumnIndex(MyDbHelper.SHOP_IMAGEURL));
                list.add(new ShopInfo(id,ShopName, imgUrl, newprice, oldprice));
            }
            cursor.close();
            return list;
        } else
            return null;
    }
    public void delData(int position){
        db.execSQL("delete from " + MyDbHelper.TABLE_NBAME + " where _id=" + position);
    }
    public void delData(){
        Log.e("aa", "删除用户表执行");
        db.execSQL("delete from shop_user");
    }
    public void delshopinfoData(){
        Log.e("aa", "删除商品表执行");
        db.execSQL("delete from shop_fest");
    }



}
