package com.jie.tbs.immersion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.immersionbar.ImmersionBar;
import com.jie.tbs.R;

/**
 * 图片状态栏和普通状态栏
 */
public class BarPicActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    private Toolbar toolbar;
    private SeekBar seekBar;
    private ImageView mIv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_pic);
        mIv = findViewById(R.id.mIv);
        toolbar = findViewById(R.id.toolbar);
        seekBar = findViewById(R.id.seek_bar);
        findViewById(R.id.btn_status_color).setOnClickListener(this);
        findViewById(R.id.btn_navigation_color).setOnClickListener(this);
        findViewById(R.id.btn_color).setOnClickListener(this);
        initView();
    }
    private void initView(){

        ImmersionBar.with(this).statusBarView(R.id.top_view)
               // .navigationBarColor(R.color.colorPrimary)
              //  .fullScreen(true)
                .addTag("PicAndColor")
                .init();

        Glide.with(this).asBitmap().load("http://106.14.135.179/ImmersionBar/38.jpg")
                .apply(new RequestOptions().placeholder(R.drawable.icon_weibo)).into(mIv);
        seekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float alpha = (float) progress / 100;
        ImmersionBar.with(this)
                .statusBarColorTransform(R.color.orange)
              //  .navigationBarColorTransform(R.color.tran)
                .addViewSupportTransformColor(toolbar)
                .barAlpha(alpha)
                .init();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_status_color:
                ImmersionBar.with(this).statusBarColor(R.color.colorAccent).init();
                break;
            case R.id.btn_navigation_color:
                if (ImmersionBar.hasNavigationBar(this)) {
                    ImmersionBar.with(this).navigationBarColor(R.color.colorAccent).init();
                } else {
                    Toast.makeText(this, "当前设备没有导航栏", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_color:
                ImmersionBar.with(this).getTag("PicAndColor").init();
                break;
        }
    }
}
