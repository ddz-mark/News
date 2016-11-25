package com.dudaizhong.news.modules.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Markable on 2016/11/25.
 */

public class AboutActivity extends AppCompatActivity {

    @Bind(R.id.bannner)
    ImageView mBannner;
    @Bind(R.id.tv_version)
    TextView mTvVersion;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @Bind(R.id.app_bar)
    AppBarLayout mAppBar;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.bt_blog)
    Button mBtBlog;
    @Bind(R.id.bt_code)
    Button mBtCode;
    @Bind(R.id.bt_share)
    Button mBtShare;
    @Bind(R.id.bt_update)
    Button mBtUpdate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        setToolBar(mToolbar, "就读");
    }


    public void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.bt_blog, R.id.bt_code, R.id.bt_share, R.id.bt_update, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_blog:
                goToHtml("http://www.jianshu.com/users/5f453275d0d8/latest_articles");
                break;
            case R.id.bt_code:
                goToHtml("https://github.com/ddz-mark/News");
                break;
            case R.id.bt_share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_txt));
                startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_app)));
                break;
            case R.id.bt_update:
                ToastUtil.showToast(AboutActivity.this,"最新版本");
                break;
            case R.id.fab:
                ToastUtil.showToast(AboutActivity.this,"谢谢赞赏");
                break;
        }
    }

    private void goToHtml(String url) {
        Uri uri = Uri.parse(url);   //指定网址
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);           //指定Action
        intent.setData(uri);                            //设置Uri
        startActivity(intent);        //启动Activity
    }
}
