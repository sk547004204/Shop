package share.top.com.zhlw2.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import share.top.com.zhlw2.R;
import share.top.com.zhlw2.view.ProgressManager;

public class SettingActivity extends BaseActivity {
    @InjectView(R.id.title_back)
    LinearLayout titleBack;
    @InjectView(R.id.title_set)
    TextView titleSet;
    @InjectView(R.id.title_more)
    LinearLayout titleMore;
    @InjectView(R.id.set_gerenziliao)
    LinearLayout setGerenziliao;
    @InjectView(R.id.set_myself)
    LinearLayout setMyself;
    @InjectView(R.id.set_message)
    LinearLayout setMessage;
    @InjectView(R.id.set_yinsi)
    LinearLayout setYinsi;
    @InjectView(R.id.set_tongyong)
    LinearLayout setTongyong;
    @InjectView(R.id.set_kefu)
    LinearLayout setKefu;
    @InjectView(R.id.set_us)
    LinearLayout setUs;
    @InjectView(R.id.set_btnback)
    Button setBtnback;
    private ProgressManager manager;
    private ProgressDialog bar;

    @Override
    public void setContentView() {
        setContentView(R.layout.set_activity);
    }

    @Override
    public void beforeView() {

    }

    @Override
    public void initView() {

    }

    Activity activity = this;

    @Override
    public void afterView() {
        bar = new ProgressDialog(SettingActivity.this);
        bar.setCancelable(true);
        bar.setMessage("加载中....");
        bar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        bar.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        while (activity.getParent() != null) {
//            activity = activity.getParent();
//            manager = ProgressManager.getInstance(activity);
//            manager.show();
//        }
//        if (!isFinishing()) {
//            manager = ProgressManager.getInstance(SettingActivity.this);
//            manager.show();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        manager.dismiss();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @OnClick({R.id.title_back, R.id.title_set, R.id.title_more, R.id.set_gerenziliao, R.id.set_myself, R.id.set_message, R.id.set_yinsi, R.id.set_tongyong, R.id.set_kefu, R.id.set_us, R.id.set_btnback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                break;
            case R.id.title_set:
                break;
            case R.id.title_more:
                break;
            case R.id.set_gerenziliao:
                startActivity(new Intent(SettingActivity.this, SettingInfoActivity.class));
                break;
            case R.id.set_myself:
                startActivity(new Intent(SettingActivity.this, SettingUserSafeActiviy.class));
                break;
            case R.id.set_message:
                break;
            case R.id.set_yinsi:
                break;
            case R.id.set_tongyong:
                break;
            case R.id.set_kefu:
                break;
            case R.id.set_us:
                startActivity(new Intent(SettingActivity.this, SettingAboutUsActivity.class));
                break;
            case R.id.set_btnback:
                break;
        }
    }
}
