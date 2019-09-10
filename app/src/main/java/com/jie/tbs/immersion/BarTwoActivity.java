package com.jie.tbs.immersion;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.immersionbar.ImmersionBar;
import com.jie.tbs.R;
import com.jie.tbs.utils.DeviceUtils;

public class BarTwoActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView mIv;
    private AppBarLayout appBarLayout;
    private TextView barTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_two);

        toolbar = findViewById(R.id.detail_toolbar);
        appBarLayout = findViewById(R.id.app_bar);
        mIv = findViewById(R.id.mIv);
        barTitle = findViewById(R.id.bar_title);
        ImmersionBar.with(this).titleBar(toolbar).init();
        initView();
    }

    private void initView() {
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
        Glide.with(this).asBitmap().load(BarOneActivity.getPic())
                .apply(new RequestOptions().placeholder(R.drawable.icon_weibo))
                .into(mIv);
        int myHeight = DeviceUtils.dp2px(250);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (mCurVerticalOffset == verticalOffset) return; // // 添加方法去重，不然会导致导致onOffsetChanges方法循环调用 是因为状态栏导致的
                mCurVerticalOffset = verticalOffset;
                Log.i("jiejie","=====" + verticalOffset + "   " + myHeight + "   " + DeviceUtils.px2dp(250));
                if (verticalOffset <= - 300){
                    //toolbar.setAlpha(1f);
                    barTitle.setText("你好");
                    //toolbar.setBackgroundColor(getResources().getColor(R.color.white));
                }else {
                   // toolbar.setAlpha(0f);
                    barTitle.setText("");
                    //toolbar.setBackgroundColor(getResources().getColor(R.color.tran));
                }
            }
        });
    }
    private int mCurVerticalOffset = -1;
}
