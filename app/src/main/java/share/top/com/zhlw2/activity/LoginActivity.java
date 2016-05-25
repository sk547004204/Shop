package share.top.com.zhlw2.activity;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.sharesdk.framework.ShareSDK;
import share.top.com.zhlw2.R;
import share.top.com.zhlw2.utils.MyShareSDK;

/**
 * Created by hpp on 2016/4/1.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    View wb;
    View xl;
    View qq;
    TextView back;
    TextView regisiter;
    Button btn;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.login_btn:
                Snackbar.make(wb, "没有联接到该服务器！请稍后在试", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.login_qq:
                Snackbar.make(btn, "正在获取资源", Snackbar.LENGTH_SHORT).show();
                MyShareSDK share=new MyShareSDK();
                share.qqLogin(LoginActivity.this);
                break;
            case R.id.login_wx:
                Snackbar.make(btn, "没有联接到该服务器！请稍后在试", Snackbar.LENGTH_SHORT).show();

                break;
            case R.id.login_xl:
                Snackbar.make(btn, "没有联接到该服务器！请稍后在试", Snackbar.LENGTH_SHORT).show();

                break;
            case R.id.login_regisiter:
                Snackbar.make(btn, "没有联接到该服务器！请稍后在试", Snackbar.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.login_layout);
        ShareSDK.initSDK(this);
    }

    @Override
    public void beforeView() {

    }

    @Override
    public void initView() {
        wb = findViewById(R.id.login_wx);
        xl = findViewById(R.id.login_xl);
        qq = findViewById(R.id.login_qq);
        back = (TextView) findViewById(R.id.login_back);
        regisiter = (TextView) findViewById(R.id.login_regisiter);
        btn = (Button) findViewById(R.id.login_btn);
        btn.setOnClickListener(this);
        wb.setOnClickListener(this);
        xl.setOnClickListener(this);
        qq.setOnClickListener(this);
        back.setOnClickListener(this);
        regisiter.setOnClickListener(this);
    }

    @Override
    public void afterView() {

    }


}
