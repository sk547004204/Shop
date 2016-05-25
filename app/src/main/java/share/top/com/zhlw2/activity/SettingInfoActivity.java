package share.top.com.zhlw2.activity;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gc.materialdesign.widgets.Dialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import share.top.com.zhlw2.R;
import share.top.com.zhlw2.app.MyApp;
import share.top.com.zhlw2.bean.LocationInfo;

/**
 * Created by luxinhua on 2016/4/25.
 */
public class SettingInfoActivity extends BaseActivity {
    @InjectView(R.id.set_location)
    LinearLayout setLocation;
    @InjectView(R.id.location)
    TextView location;
    @InjectView(R.id.title_back)
    LinearLayout titleBack;
    @InjectView(R.id.title_set)
    TextView titleSet;
    @InjectView(R.id.title_more)
    LinearLayout titleMore;
    @InjectView(R.id.set_gerenziliao)
    LinearLayout setGerenziliao;
    @InjectView(R.id.user_pwd)
    LinearLayout userPwd;
    @InjectView(R.id.tv_sex)
    TextView tvSex;
    @InjectView(R.id.user_sex)
    LinearLayout userSex;
    @InjectView(R.id.set_kefu)
    LinearLayout setKefu;
    @InjectView(R.id.user_address)
    LinearLayout userAddress;

    @Override
    public void setContentView() {
        setContentView(R.layout.setting_personinfo);
    }

    @Override
    public void beforeView() {

    }

    @Override
    public void initView() {
    }

    @Override
    public void afterView() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
    }

    public void request(String httpUrl, String httpArg) {
        httpUrl = httpUrl + "?" + httpArg;
        StringRequest request = new StringRequest(httpUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("msg", "位置" + s);
                LocationInfo info = JSON.parseObject(s, LocationInfo.class);
                Log.e("msg", info.getAddress());
                String address = info.getAddress();
                String[] add = address.split("市");
                String address1 = add[0];
                location.setText(address1);
                Dialog dialog = new Dialog(SettingInfoActivity.this, "您当前的位置", address);
                dialog.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("apikey", "bf9110aac9607e997212b5500e20e86b");
                return headers;
            }
        };
        request.setTag("location");
        MyApp.getHttpQueues().add(request);
    }

    @OnClick({R.id.location, R.id.tv_sex})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.location:
                TelephonyManager mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                // 返回值MCC + MNC
                String operator = mTelephonyManager.getNetworkOperator();
                int mcc = Integer.parseInt(operator.substring(0, 3));
                int mnc = Integer.parseInt(operator.substring(3));
                // 中国移动和中国联通获取LAC、CID的方式
                GsmCellLocation location = (GsmCellLocation) mTelephonyManager.getCellLocation();
                int lac = location.getLac();
                int cellId = location.getCid();
                Log.e("msg", " MCC = " + mcc + "\t MNC = " + mnc + "\t LAC = " + lac + "\t CID = " + cellId);
                final String httpUrl = "http://apis.baidu.com/lbs_repository/cell/query";
                final String httpArg = "mcc=" + mcc + "&mnc=" + mnc + "&lac=" + lac + "&ci=" + cellId + "&coord=wgs84";
                Log.e("msg", "参数 " + httpArg);
                request(httpUrl, httpArg);
                break;
            case R.id.tv_sex:
                break;
        }
    }
}
