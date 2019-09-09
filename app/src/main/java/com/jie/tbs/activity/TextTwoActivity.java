package com.jie.tbs.activity;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jie.tbs.R;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.ValueCallback;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.DownloadProgressCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

/**
 * 先下载文件 再浏览
 */
public class TextTwoActivity extends AppCompatActivity implements ValueCallback<String> {
    private String text = "/storage/emulated/0/Android/data/cn.phoenix.ibank/files/19_PDF.pdf";
    //private String mFileUrl = "http://www.gov.cn/zhengce/pdfFile/1994_PDF.pdf";
    private String mFileUrl = "https://finance-item-test.oss-cn-hangzhou.aliyuncs.com/phoenix/2019/08/21/acd02910703543d4b29f50c7d22abaa02019劳动合同范文.docx";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_two);
        //checkIsTestFile((new File(text)).getAbsolutePath());
        downLoad(mFileUrl);
    }

    private void checkIsTestFile(final String fName) {

        QbSdk.canOpenFile(this, fName, new ValueCallback<Boolean>() {
            @Override
            public void onReceiveValue(Boolean aBoolean) {
                Log.i("jiejie", "---" + aBoolean);
                if (aBoolean) {
                    openFileReader(TextTwoActivity.this, fName);
                }
            }
        });
    }

    private void openFileReader(Context context, String pathName) {
        HashMap<String, String> params = new HashMap<>();
        params.put("local", "true");
//        JSONObject Object = new JSONObject();
//        try {
//            Object.put("pkgName", context.getApplicationContext().getPackageName());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        params.put("menuData", Object.toString());
        Log.i("jiejie", "---" + pathName);
        QbSdk.getMiniQBVersion(context);
        //int ret = QbSdk.op
        QbSdk.openFileReader(context, pathName, null, this);
    }

    @Override
    public void onReceiveValue(String s) {
        Log.e("jiejie", "onReceiveValue,val =" + s);
    }

    private void downLoad(String url) {
        EasyHttp.downLoad(url)
                .saveName(url.substring(url.lastIndexOf("/") + 1))
                .execute(new DownloadProgressCallBack<String>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(ApiException e) {
                        Log.i("jiejie", "下失败" + e.getMessage());
                    }

                    @Override
                    public void update(long bytesRead, long contentLength, boolean done) {

                    }

                    @Override
                    public void onComplete(String path) {
                        Log.i("jiejie", "下完成路径" + path);
                        checkIsTestFile(path);
                    }
                });
    }
}
