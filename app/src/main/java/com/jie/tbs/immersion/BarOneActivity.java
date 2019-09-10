package com.jie.tbs.immersion;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.immersionbar.ImmersionBar;
import com.jie.tbs.R;

import java.util.Random;

/**
 * 结合在CoordinatorLayout使用
 */
public class BarOneActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_one);
        toolbar = findViewById(R.id.detail_toolbar);
        mIv = findViewById(R.id.mIv);
        ImmersionBar.with(this).titleBar(toolbar).init();
        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Glide.with(this).asBitmap().load(getPic())
                .apply(new RequestOptions().placeholder(R.drawable.icon_weibo))
                .into(mIv);
    }

    public static String getPic() {
        Random random = new Random();
        return "http://106.14.135.179/ImmersionBar/" + random.nextInt(40) + ".jpg";
    }
}
