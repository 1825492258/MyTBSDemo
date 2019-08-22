package com.jie.tbs.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;

/**
 * ================================================
 * 作    者：wuzongjie
 * 版    本：1.0.0
 * 创建日期：2019/8/21
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class TextFileView extends FrameLayout implements TbsReaderView.ReaderCallback {
    private TbsReaderView mTbsReaderView;
    private Context context;

    public TextFileView(Context context) {
        this(context, null);
    }

    public TextFileView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextFileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTbsReaderView = new TbsReaderView(context, this);
        this.addView(mTbsReaderView, new LinearLayout.LayoutParams(-1, -1));
        this.context = context;
    }

    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {

    }

    private TbsReaderView getTbsReaderView(Context context) {
        return new TbsReaderView(context, this);
    }

    public void displayFile(File file) {
        if (mTbsReaderView == null) mTbsReaderView = getTbsReaderView(context);
        Bundle bundle = new Bundle();
        bundle.putString("filePath", file.getAbsolutePath());
       // bundle.putString("tempPath", file.getCanonicalPath());
        bundle.putString("tempPath", Environment.getExternalStorageDirectory().getPath());
        boolean bool = mTbsReaderView.preOpen(file.toString(), false);
        Log.i("jiejie", bool + "  " + file.getAbsolutePath());


            mTbsReaderView.openFile(bundle);
    }
    private OnGetFilePathListener mOnGetFilePathListener;


    public void setOnGetFilePathListener(OnGetFilePathListener mOnGetFilePathListener) {
        this.mOnGetFilePathListener = mOnGetFilePathListener;
    }
    public void show() {
        if(mOnGetFilePathListener!=null){
            mOnGetFilePathListener.onGetFilePath(this);
        }
    }

    /***
     * 将获取File路径的工作，“外包”出去
     */
    public interface OnGetFilePathListener {
        void onGetFilePath(TextFileView mSuperFileView2);
    }

    public void onStopDisplay() {
        if (mTbsReaderView != null) {
            mTbsReaderView.onStop();
        }
    }
}
