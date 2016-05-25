package share.top.com.zhlw2.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ZHOU on 2016/4/7.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        beforeView();
        initView();
        afterView();
    }

    public abstract void setContentView();

    public abstract void beforeView();

    public abstract void initView();

    public abstract void afterView();

    public void ShowToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void ShowTast(int i) {
        Toast.makeText(this, i + "", Toast.LENGTH_SHORT).show();
    }

    //log参数一 代表 log 标签 参数二 代表log内容
    public void showLog(String tag, String msg) {
        Log.i(tag, msg);
    }

    public void showLog(String tag, int msg) {
        Log.i(tag, msg + "");
    }

    public void ShowSnakBar(View view, String str) {
        Snackbar.make(view, str, Snackbar.LENGTH_SHORT).show();
    }
}
