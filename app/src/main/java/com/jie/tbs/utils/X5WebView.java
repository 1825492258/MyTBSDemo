package com.jie.tbs.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class X5WebView extends WebView {
	TextView title;
	private WebViewClient client = new WebViewClient() {
		/**
		 * 防止加载网页时调起系统浏览器
		 */
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageFinished(WebView webView, String s) {
			super.onPageFinished(webView, s);
			imgReset(webView);
		}
	};
	private void imgReset(WebView webView) {
		webView.loadUrl("javascript:(function(){"
				+ "var objs = document.getElementsByTagName('img'); "
				+ "for(var i=0;i<objs.length;i++)  " + "{"
				+ "var img = objs[i];   "
				+ "    img.style.width = '100%';   "
				+ "    img.style.height = 'auto';   "
				+ "}" + "})()");
	}
	@SuppressLint("SetJavaScriptEnabled")
	public X5WebView(Context arg0, AttributeSet arg1) {
		super(arg0, arg1);
		this.setWebViewClient(client);
		// this.setWebChromeClient(chromeClient);
		// WebStorage webStorage = WebStorage.getInstance();
		initWebViewSettings();
		this.getView().setClickable(true);
	}

	private void initWebViewSettings() {
		WebSettings webSetting = this.getSettings();
		webSetting.setJavaScriptEnabled(true);
		webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
		webSetting.setAllowFileAccess(true);
		webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
		webSetting.setSupportZoom(true);
		webSetting.setBuiltInZoomControls(true);
		// 设置字体大小，默认代销16
//		webSetting.setDefaultFontSize(66);
//		webSetting.setMinimumFontSize(20);
		//设置字体
//		webSetting.setTagFontFamily("p");
//		webSetting.setTagFontFamily("span");
//		//设置字体大小
//		webSetting.setFontSize(6);
		// 排版适应屏幕
		webSetting.setLayoutAlgorithm(com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
		//将图片调整到适合webview的大小
		webSetting.setUseWideViewPort(true); // 设置屏幕自适应
		// 缩放至屏幕的大小
		webSetting.setLoadWithOverviewMode(true);
		webSetting.setSupportMultipleWindows(true);
		// webSetting.setLoadWithOverviewMode(true);
		webSetting.setAppCacheEnabled(true);
		// webSetting.setDatabaseEnabled(true);
		webSetting.setDomStorageEnabled(true); // 使用localStorage
		webSetting.setGeolocationEnabled(true);
		webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
		// webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
		webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
		// webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
		webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
	}

/*	@Override
	protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
		boolean ret = super.drawChild(canvas, child, drawingTime);
		canvas.save();
		Paint paint = new Paint();
		paint.setColor(0x7fff0000);
		paint.setTextSize(24.f);
		paint.setAntiAlias(true);
		if (getX5WebViewExtension() != null) {
			canvas.drawText(this.getContext().getPackageName() + "-pid:"
					+ android.os.Process.myPid(), 10, 50, paint);
			canvas.drawText(
					"X5  Core:" + QbSdk.getTbsVersion(this.getContext()), 10,
					100, paint);
		} else {
			canvas.drawText(this.getContext().getPackageName() + "-pid:"
					+ android.os.Process.myPid(), 10, 50, paint);
			canvas.drawText("Sys Core", 10, 100, paint);
		}
		canvas.drawText(Build.MANUFACTURER, 10, 150, paint);
		canvas.drawText(Build.MODEL, 10, 200, paint);
		canvas.restore();
		return ret;
	}*/

	public X5WebView(Context arg0) {
		super(arg0);
		setBackgroundColor(85621);
	}

}
