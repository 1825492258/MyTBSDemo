package com.jie.tbs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                startActivity(new Intent(this,TextOneActivity.class));
                break;
            case R.id.btnTwo:
                startActivity(new Intent(this,TextTwoActivity.class));
                break;
            case R.id.btnThree:
                startActivity(new Intent(this,TextThreeActivity.class));
                break;
            case R.id.btnFour:
                startActivity(new Intent(this, TextOnesActivity.class));
                break;
            case R.id.btnFive:
                startActivity(new Intent(this,TextPlayerActivity.class));
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
                doWXPay(text);
                break;
        }
    }
    private String text = "{\"appid\":\"wxb4ba3c02aa476ea1\",\"partnerid\":\"1900006771\",\"package\":\"Sign=WXPay\",\"noncestr\":\"fc9d4370204b3020a64ea7c4d9a0afae\",\"timestamp\":1568269134,\"prepayid\":\"wx121418542937064f43360ac21785097732\",\"sign\":\"B5829619AE4AEA58F0888D016493D854\"}";
    /**
     * 微信支付
     * AppID：wxc61a028d110e11ad
     * AppSecret：816905b84ae5684370075b4ce2dd4c3d
     */
    private void doWXPay(String pay_param){
        String APP_ID = "wxc61a028d110e11ad"; // //替换为自己的appid
        WXPay.init(getApplicationContext(),APP_ID);
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
