package com.jie.tbs.activity;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jie.tbs.R;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class TextOnesActivity extends AppCompatActivity {

    /**
     * 确保注销配置能够被释放
     */
    @Override
    protected void onDestroy() {
        if (this.webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if(this.webView !=null) {
            webView.onPause();
        }
        super.onPause();
    }
    private String textOne = "<p>一、**置业有限公司简介：</p><p>**置业有限公司成立于2017年，注册资本5000万元。营业范围涵盖房地产经纪及信息咨询；房地产开发、经营；展览展示会服务（不含住宿及餐饮）；企业管理营销策划；不动产租赁；物业管理；房屋修缮，家政服务，室内装饰装修工程、园林绿化工程施工，楼宇机电设备维护，停车场服务等。</p><p>二、标的情况描述：</p><p>随着汽车数量的逐年增加，车流量增大，全国停车位的供应不足，难以满足市场需求，供需矛盾突出。车位的投资及升值潜力日益突出。基于上述原因，**置业有限公司与青岛万科物业服务有限公司进行车位销售战略合作。青岛万科物业服务有限公司向**置业有限公司转让车位使用权，转让车位的范围为青岛万科地产开发的福州路、山东路万科中心繁华街区地段及万科城国际公园地段。</p><p>本标的为车位资产包142号，位于：山东路万科中心地下三层车位（车位号：2、3、7)。</p><p>三、标的权益转让的具体情况：</p><p>**置业有限公司与青岛万科物业服务有限公司签署《车位使用权转让合作协议》，并委托青岛万科物业服务有限公司代为管理，现将收购的资产包于“凤凰众筹”平台挂标转让，期满后由**置业有限公司回购。</p><p>四、特别约定：</p><p>此项目可能因**置业有限公司结算原因，该项目期限为融资期+退出期。</p><p>综合各方面情况，此笔权益转让标信用综合评级为AA级。</p><p>&nbsp;特别提示：凤凰置业有限公司与本平台系一定关联关系，特此披露。</p>";
    private String textjie = "<h2 class=\"ql-align-center\"><span style=\"color: rgb(153, 51, 255);\">“世界历史上有两部好法律，一部是《罗马法》，另外一部就是《贞观律》。《罗马法》将人的权利落实到法律之中，而《贞观律》的长处在于其将仁道写进了法律。”李大华教授如此评价唐代的律法。他列举生动的历史事迹及《唐律疏议》等材料中的条文，向在场师生展示唐代律法所体现的仁爱精神。李大华教授进一步论证唐王朝推行仁政的两大体现：一是君民关系，主张“以百姓之心为心”，是一种舟水关系；二是君臣关系，主张君臣同心，是鱼与水的关系。唐朝鼓励直谏，而律法无“讪谤”一条，不兴文字狱，即是宽容精神在国家治理层面上的体现。最后，李大华教授对唐朝致广大的文化心态给予了很高的评价。他认为，现今的我们发扬文化自信也需要此般致广大的文化心态，应当坚持一种兼容并包的精神。</span></h2>";
    private WebView webView;
    private String text = " \"<iframe class=\"ql-video\" frameborder=\"0\" allowfullscreen=\"true\" src=\"https://finance-item-test.oss-cn-hangzhou.aliyuncs.com/phoenix/2019/08/28/7b5bc8160ca94ad1899757eb32c92fd32.mp4\"></iframe><p><br></p><p><br></p><p><img src=\"https://finance-item-test.oss-cn-hangzhou.aliyuncs.com/phoenix/2019/08/28/acf9b2732fc649128f8469e1834c562c145320199485.png\"></p><p><br></p><p><br></p><p><img src=\"https://finance-item-test.oss-cn-hangzhou.aliyuncs.com/phoenix/2019/08/28/9fdbec7f5eb347d1b837286f85aef206145825475987.png\"></p><p><br></p><p><br></p><p><img src=\"https://finance-item-test.oss-cn-hangzhou.aliyuncs.com/phoenix/2019/08/28/e0e99e6fba5247fa92a1c08ee8c98019145839476966.png\"></p>\"";
    private String texts = "<p><img src=\"https://finance-item-test.oss-cn-hangzhou.aliyuncs.com/phoenix/2019/08/20/757604f80cd0405b94bcf2b8a9f343eajyk_01.jpg\"></p><p><img src=\"https://finance-item-test.oss-cn-hangzhou.aliyuncs.com/phoenix/2019/08/20/a53b9dcb07e246a5bc2aa25c8507420ejyk_02.jpg\"></p><p><img src=\"https://finance-item-test.oss-cn-hangzhou.aliyuncs.com/phoenix/2019/08/20/edff4c87878e47e9bb08bf48a4bf34b3jyk_03.jpg\"></p><p><img src=\"https://finance-item-test.oss-cn-hangzhou.aliyuncs.com/phoenix/2019/08/20/0dfcae763d124c32a08e9dab1efdb378jyk_04.jpg\"></p>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_ones);
        webView = findViewById(R.id.webView);
        initWebViewSettings();


        webView.loadDataWithBaseURL(null, textOne, "text/html", "utf-8", null);
      //  webView.loadUrl("http://res.ky-express.com/h5/video/72.html");
       // getWindow().setFormat(PixelFormat.TRANSLUCENT);

        webView.getView().setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        webView.setWebViewClient(client);

        webView.getSettings().setUseWideViewPort(true); //自适应屏幕
    }
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
    @SuppressLint("SetJavaScriptEnabled")
    private void initWebViewSettings() {
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        // 设置字体大小，默认大小为16
		webSetting.setDefaultFontSize(40);
		// 设置支持的最小字体大小，默认为8
		webSetting.setMinimumFontSize(20);
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

        // this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
        // settings 的设计
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        try {
            super.onConfigurationChanged(newConfig);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void imgReset(WebView webViews) {
        webViews.loadUrl("javascript:(function(){"
                + "var objs = document.getElementsByTagName('img'); "
                + "for(var i=0;i<objs.length;i++)  " + "{"
                + "var img = objs[i];   "
                + "    img.style.width = '100%';   "
                + "    img.style.height = 'auto';   "
                + "}" + "})()");
    }
}
