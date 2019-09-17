package com.jie.tbs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jie.tbs.activity.TextOneActivity;
import com.jie.tbs.activity.TextOnesActivity;
import com.jie.tbs.activity.TextThreeActivity;
import com.jie.tbs.activity.TextTwoActivity;
import com.jie.tbs.immersion.BarOneActivity;
import com.jie.tbs.immersion.BarPicActivity;
import com.jie.tbs.immersion.BarTwoActivity;
import com.jie.tbs.player.TextPlayerActivity;
import com.jie.tbs.wxapi.WXPay;
import net.sourceforge.simcpux.wxapi.WXPayEntryActivity;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = WXAPIFactory.createWXAPI(this, WXPayEntryActivity.APP_ID);
        findViewById(R.id.btnOne).setOnClickListener(this);
        findViewById(R.id.btnTwo).setOnClickListener(this);
        findViewById(R.id.btnThree).setOnClickListener(this);
        findViewById(R.id.btnFour).setOnClickListener(this);
        findViewById(R.id.btnFive).setOnClickListener(this);

        findViewById(R.id.btnBarOne).setOnClickListener(this);
        findViewById(R.id.btnBarTwo).setOnClickListener(this);
        findViewById(R.id.btnBarThree).setOnClickListener(this);

        findViewById(R.id.btnText).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOne:
                startActivity(new Intent(this, TextOneActivity.class));
                break;
            case R.id.btnTwo:
                startActivity(new Intent(this, TextTwoActivity.class));
                break;
            case R.id.btnThree:
                startActivity(new Intent(this, TextThreeActivity.class));
                break;
            case R.id.btnFour:
                startActivity(new Intent(this, TextOnesActivity.class));
                break;
            case R.id.btnFive:
                startActivity(new Intent(this, TextPlayerActivity.class));
                break;
            case R.id.btnBarOne:
                startActivity(new Intent(this, BarOneActivity.class));
                break;
            case R.id.btnBarTwo:
                startActivity(new Intent(this, BarTwoActivity.class));
                break;
            case R.id.btnBarThree:
                startActivity(new Intent(this, BarPicActivity.class));
                break;
            case R.id.btnText:
                // doWXPay(text);
                doPay();
                break;
        }
    }

    private IWXAPI api;
    private String ji = "{\"appid\":\"wxc61a028d110e11ad\",\"noncestr\":\"wfCMPLnyhtWo1fj5\",\"package\":\"Sign\\u003dWXPay\",\"partnerid\":\"1552499791\",\"prepayid\":\"wx1717272156437583ca728d991892077900\",\"sign\":\"5C26063F4ED3E4DE00DA540C7CAF5551\",\"timestamp\":\"1568712441\"}";
    private String te  = "{\"appid\":\"wxc61a028d110e11ad\",\"noncestr\":\"ukrnWA1rs8qCGzWg\",\"package\":\"Sign\\u003dWXPay\",\"partnerid\":\"1552499791\",\"prepayid\":\"wx17161755698299aeca8648f81773865100\",\"sign\":\"9670AE31BE25C418DA5D72224DE3CD14\",\"timestamp\":\"1568708275\"}";
    private String text = "{\"appid\":\"wxc61a028d110e11ad\",\"noncestr\":\"0029842353cb4aebb7a3fc6aeb13e91d\",\"package\":\"Sign\\u003dWXPay\",\"partnerid\":\"1552499791\",\"prepayid\":\"wx171410333546964ca94e5d891449511300\",\"sign\":\"D4F748AE9D6901F8D39553E4F36336BD\",\"timestamp\":\"1568700633\"}";
    //  private String text = "{\"appid\":\"wxb4ba3c02aa476ea1\",\"partnerid\":\"1900006771\",\"package\":\"Sign=WXPay\",\"noncestr\":\"fc9d4370204b3020a64ea7c4d9a0afae\",\"timestamp\":1568269134,\"prepayid\":\"wx121418542937064f43360ac21785097732\",\"sign\":\"B5829619AE4AEA58F0888D016493D854\"}";
    /**
     * 微信支付
     * AppID：wxc61a028d110e11ad
     * AppSecret：816905b84ae5684370075b4ce2dd4c3d
     */
    public static final String APP_ID = "wxc61a028d110e11ad";

    private void doPay() {
        try {
            JSONObject json = new JSONObject(ji);
            if (!json.has("retcode")) {
                PayReq req = new PayReq();
                //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                req.appId = json.getString("appid");
                req.partnerId = json.getString("partnerid");
                req.prepayId = json.getString("prepayid");
                req.nonceStr = json.getString("noncestr");
                req.timeStamp = json.getString("timestamp");
                req.packageValue = json.getString("package");
                req.sign = json.getString("sign");
                req.extData = "app data"; // optional
                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                Log.i("PAY_GET", "sing" + json.getString("sign"));
                api.sendReq(req);
            } else {
                Log.i("PAY_GET", "返回错误" + json.getString("retmsg"));
            }
        } catch (Exception e) {
            Log.i("PAY_GET", "异常：" + e.getMessage());
        }
    }

    private void doWXPay(String pay_param) {
        // String APP_ID = APP_I; // //替换为自己的appid
        WXPay.init(getApplicationContext(), APP_ID);
        WXPay.getInstance().doPay(pay_param, new WXPay.WXPayResultCallBack() {
            @Override
            public void onSuccess() {
                Toast.makeText(getApplication(), "支付成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case WXPay.NO_OR_LOW_WX:
                        Toast.makeText(getApplication(), "未安装微信或微信版本过低", Toast.LENGTH_SHORT).show();
                        break;
                    case WXPay.ERROR_PAY_PARAM:
                        Toast.makeText(getApplication(), "参数错误", Toast.LENGTH_SHORT).show();
                        break;
                    case WXPay.ERROR_PAY:
                        Toast.makeText(getApplication(), "支付失败", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplication(), "支付取消", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
