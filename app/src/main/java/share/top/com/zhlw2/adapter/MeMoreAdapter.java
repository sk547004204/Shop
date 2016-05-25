package share.top.com.zhlw2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by hpp on 2016/3/16.
 */
public class MeMoreAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> list;
    private ArrayList<String> list_tag;

    public MeMoreAdapter(FragmentManager fm, ArrayList<Fragment> list, ArrayList<String> list_tag) {
        super(fm);
        this.list = list;
        this.list_tag = list_tag;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        switch (position) {
            // 我的券未消费
            case 0:
                fragment = list.get(0);
                break;
            // 我的券已消费
            case 1:
                fragment = list.get(1);
                break;
            case 2:
                fragment = list.get(2);
                break;
            case 3:
                fragment = list.get(3);
                break;
            case 4:
                fragment = list.get(4);
                break;
        }
        Log.e("aa", "点击了： " + position);
        return fragment;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position > list_tag.size()) {
            return "未加载";
        }

        CharSequence s = null;
        switch (position) {
            case 0:
                s = list_tag.get(0);
                Log.e("aa","tag="+list_tag.get(0));
                break;
            case 1:
                s = list_tag.get(1);
                Log.e("aa","tag="+list_tag.get(1));
                break;
            case 2:
                s = list_tag.get(2);
                Log.e("aa","tag="+list_tag.get(2));
                break;
            case 3:
                s = list_tag.get(3);
                Log.e("aa","tag="+list_tag.get(3));
                break;
            case 4:
                s = list_tag.get(4);
                Log.e("aa","tag="+list_tag.get(4));
                break;

        }


        return s;
    }
}
