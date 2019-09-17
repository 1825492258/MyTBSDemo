package com.jie.tbs.wxapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.jie.tbs.MainActivity;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class AppRegister extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		final IWXAPI msgApi = WXAPIFactory.createWXAPI(context, null);
		Log.e("jiejie","---------- 将该app注册到微信");
		// 将该app注册到微信
		msgApi.registerApp(MainActivity.APP_ID);
	}
}
