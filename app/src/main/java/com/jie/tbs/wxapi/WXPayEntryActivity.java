package com.jie.tbs.wxapi;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    //private IWXAPI api;
    // APP_ID 替换为你的应用从官方网站申请到的合法appId
    public static final String APP_ID = "wxd930ea5d5a258f4f";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.pay_result);

        //api = WXAPIFactory.createWXAPI(this, APP_ID);
        // api.handleIntent(getIntent(), this);
        if (WXPay.getInstance() != null) {
            WXPay.getInstance().getWXApi().handleIntent(getIntent(), this);
        } else {
            finish();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        //api.handleIntent(intent, this);
        if (WXPay.getInstance() != null) {
            WXPay.getInstance().getWXApi().handleIntent(intent, this);
        }
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        //Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);
        Log.i("jiejie", "微信支付回调>>>>>" + resp.errCode);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (WXPay.getInstance() != null) {
                WXPay.getInstance().onResp(resp.errCode);
                finish();
            }
        }
    }
}