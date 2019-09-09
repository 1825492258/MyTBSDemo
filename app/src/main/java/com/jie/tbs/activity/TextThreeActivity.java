package com.jie.tbs.activity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jie.tbs.R;
import com.jie.tbs.utils.DownloadUtil;
import com.tencent.smtt.sdk.TbsReaderView;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.DownloadProgressCallBack;
import com.zhouyou.http.exception.ApiException;

import java.io.File;
import java.util.regex.Pattern;

/**
 * 先下载文件再浏览
 */
public class TextThreeActivity extends AppCompatActivity implements TbsReaderView.ReaderCallback {

    //private String mFileUrl = "https://finance-item-test.oss-cn-hangzhou.aliyuncs.com/phoenix/2019/08/21/acd02910703543d4b29f50c7d22abaa02019%E5%8A%B3%E5%8A%A8%E5%90%88%E5%90%8C%E8%8C%83%E6%96%87.docx";
    //private String mFileUrl = "https://finance-item-test.oss-cn-hangzhou.aliyuncs.com/phoenix/2019/08/21/ed6e8b6b4bdb416d96c08d2b535184eb2019_PDF.pdf";
    //private String mFileUrl = "http://www.gov.cn/zhengce/pdfFile/1994_PDF.pdf";
    private String mFileUrl = "https://finance-item-test.oss-cn-hangzhou.aliyuncs.com/phoenix/2019/08/21/acd02910703543d4b29f50c7d22abaa02019劳动合同范文.docx";

    private TbsReaderView mTbsReaderView;
    private String mFileName;
    private Button mDownloadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_three);
        mDownloadBtn = findViewById(R.id.downLoad);
        RelativeLayout rootRl = findViewById(R.id.rl_root);
        mTbsReaderView = new TbsReaderView(this, this);
        rootRl.addView(mTbsReaderView, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        mFileName = parseName(mFileUrl);
        if (isLocalExist()) {
            mDownloadBtn.setText("打开文件");
        }else {
            mDownloadBtn.setText("下载文件");
        }
        mDownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLocalExist()) {
                    displayFile();
                    //downLoad(mFileUrl);
                } else {
                    downLoad(mFileUrl);
                }
            }
        });

        if (isLocalExist()) {
            //displayFile();
            downLoad(mFileUrl);
        } else {
            downLoad(mFileUrl);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTbsReaderView !=null)  mTbsReaderView.onStop();
    }

    private void displayFile() {
        Bundle bundle = new Bundle();
        Log.i("jiejie" ,"打开====" + getLocalFile().getPath() + "   " + Environment.getExternalStorageDirectory().getPath());
        bundle.putString("filePath", getLocalFile().getPath());
        bundle.putString("tempPath", Environment.getExternalStorageDirectory().getPath());
        boolean result = mTbsReaderView.preOpen(parseFormat(mFileName), false);
        if (result) {
            mTbsReaderView.openFile(bundle);
        }
//        localBundle.putString("filePath", mFile.toString());
//        localBundle.putString("tempPath", Environment.getExternalStorageDirectory() + "/" + "TbsReaderTemp");
//        if (this.mTbsReaderView == null)
//            this.mTbsReaderView = getTbsReaderView(context);
//        boolean bool = this.mTbsReaderView.preOpen(getFileType(mFile.toString()), false);
//        if (bool) {
//            this.mTbsReaderView.openFile(localBundle);
//        }
    }

    private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";// 中文正则

    private String parseFormat(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private String parseName(String url) {
        String fileName = null;
        try {
            fileName = url.substring(url.lastIndexOf("/") + 1);
            //fileName = Pattern.compile(REGEX_CHINESE).matcher(fileName).replaceAll("");
        } finally {
            if (TextUtils.isEmpty(fileName)) {
                fileName = String.valueOf(System.currentTimeMillis());
            }
        }
        return fileName;
    }

    private boolean isLocalExist() {
        return getLocalFile().exists();
    }

    private File getLocalFile() {
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), mFileName);
    }

    private void downLoad(String url) {
        Log.i("jiejie", getLocalFile().toString() + "    " + parseName(url));
        DownloadUtil.getInstance().download(url, Environment.getExternalStorageDirectory().getPath(), new DownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(String path) {
                Log.i("jiejie","---" + path);
                Bundle bundle = new Bundle();
                        bundle.putString("filePath", new File(path).getAbsolutePath());
                        bundle.putString("tempPath", Environment.getExternalStorageDirectory().getPath());
                        boolean result = mTbsReaderView.preOpen(parseFormat(mFileName), false);
                        if (result) {
                            mTbsReaderView.openFile(bundle);
                        }else {
                            Log.i("jiejie" ,"失败");
                        }
            }

            @Override
            public void onDownloading(int progress) {

            }

            @Override
            public void onDownloadFailed() {
                Log.i("jiejie","--失败");
            }
        });
//        EasyHttp.downLoad(url)
//               // .savePath( new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"").toString())
//                .saveName(parseName(url))
//                .execute(new DownloadProgressCallBack<String>() {
//                    @Override
//                    public void onStart() {
//
//                    }
//
//                    @Override
//                    public void onError(ApiException e) {
//                        Log.i("jiejie", "下失败" + e.getMessage());
//                    }
//
//                    @Override
//                    public void update(long bytesRead, long contentLength, boolean done) {
//
//                    }
//
//                    @Override
//                    public void onComplete(String path) {
//                        Log.i("jiejie", "下完成路径" + path  +"   " );
//                        Bundle bundle = new Bundle();
//                        bundle.putString("filePath", new File(path).getAbsolutePath());
//                        bundle.putString("tempPath", Environment.getExternalStorageDirectory().getPath());
//                        boolean result = mTbsReaderView.preOpen(parseFormat(mFileName), false);
//                        if (result) {
//                            mTbsReaderView.openFile(bundle);
//                        }else {
//                            Log.i("jiejie" ,"失败");
//                        }
//                    }
//                });
    }

    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {

    }
}
