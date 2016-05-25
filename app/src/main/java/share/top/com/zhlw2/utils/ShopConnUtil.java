package share.top.com.zhlw2.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import share.top.com.zhlw2.R;

/**
 * Created by ZHOU on 2016/4/12.
 */
public class ShopConnUtil {
    private static ShopConnUtil connUtil;

    private ShopConnUtil() {
    }

    public static ShopConnUtil getInstance() {
        if (connUtil == null) {
            connUtil = new ShopConnUtil();
        }
        return connUtil;
    }

    public void imageLoader(Context context, final ImageView imageView, String url, int width, int height) {
        /**
         1. 创建一个RequestQueue对象。
         2. 创建一个ImageLoader对象。
         3. 获取一个ImageListener对象。
         4. 调用ImageLoader的get()方法加载网络上的图片。
         下面我们就来按照这个步骤，学习一下ImageLoader的用法吧。
         首先第一步的创建RequestQueue对象我们已经写过很多遍了，
         相信已经不用再重复介绍了，那么就从第二步开始学习吧，
         新建一个ImageLoader对象，代码如下所示：
         * */
        RequestQueue mQueue = Volley.newRequestQueue(context);
        /**
         * 可以看到，ImageLoader的构造函数接收两个参数，
         * 第一个参数就是RequestQueue对象，
         * 第二个参数是一个ImageCache对象，
         * 这里我们先new出一个空的ImageCache的实现即可。
         * */
        ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache() {
            @Override
            public void putBitmap(String url, Bitmap bitmap) {
            }

            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }
        });
        /**
         * 我们通过调用ImageLoader的getImageListener()
         * 方法能够获取到一个ImageListener对象，
         * getImageListener()方法接收三个参数，
         * 第一个参数指定用于显示图片的ImageView控件，
         * 第二个参数指定加载图片的过程中显示的图片，
         * 第三个参数指定加载图片失败的情况下显示的图片。
         * */
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView,
                R.mipmap.icon, R.mipmap.icon);
        /**
         * get()方法接收两个参数，第一个参数就是图片的URL地址，
         * 第二个参数则是刚刚获取到的ImageListener对象。
         * 当然，如果你想对图片的大小进行限制，
         * 也可以使用get()方法的重载，指定图片允许的最大宽度和高度，如下所示：
         * */
        imageLoader.get(url, listener, width, height);
    }

    /**
     * 缓存
     */
    public class BitmapCache implements ImageLoader.ImageCache {
        private LruCache<String, Bitmap> mCache;

        public BitmapCache() {
            int maxSize = 10 * 1024 * 1024;
            mCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            if (mCache.get(url) != null) {
                return mCache.get(url);
            } else {
                return null;
            }
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            if (mCache.get(url) != bitmap) {
                mCache.put(url, bitmap);
            }
        }
    }

}
