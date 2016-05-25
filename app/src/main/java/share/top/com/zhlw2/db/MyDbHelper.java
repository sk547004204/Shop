package share.top.com.zhlw2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ZHOU on 2016/4/7.
 */
public class MyDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "shop.db";
    public static final String TABLE_NBAME = "shop_fest";//商品信息表名字
    public static final String USER = "shop_user";//用户信息表
    //用户信息表
    public static final String ID = "_id";
    public static final String USERNAME = "_username";
    public static final String ADDRESS = "_address";
    public static final String VIP = "_vip";//积分
    public static final String USERKEY = "_key";

    public static final String CREATE_USER_TABLE = " create table " + USER + " ( " + ID + " integer primary key autoincrement,"
            + USERNAME + " text,"  + ADDRESS + " text," + VIP + " integer," + USERKEY + " integer" + " )";
    //商品信息表
    public static final String SHOP_ID = "_id";
    public static final String SHOP_NAME = "_shopname";//商品的名字
    public static final String SHOP_PRICE_NEWS = "_newprice";//打折后价格
    public static final String SHOP_PRICE_OLD = "_oldprice";//原价格
    public static final String SHOP_IMAGEURL = "_imageurl";//图片地址
    public static final String SHOP_URL = "_shopurl";//链接地址
    public static final String SHOP_KEY = "_key";//外键

    //创建商品信息表
    private static final String CRATE_SHOP_INFO_TABLE = " create table " + TABLE_NBAME + " ( " + SHOP_ID + " integer primary key autoincrement,"
            + SHOP_NAME + " text," +
            SHOP_PRICE_NEWS + " text," +
            SHOP_PRICE_OLD + " text," +
            SHOP_IMAGEURL + " text,"  +
            SHOP_URL + " text,"  +
            SHOP_KEY + " integer)";

    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CRATE_SHOP_INFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
