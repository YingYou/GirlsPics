package com.bw.myapplication.ui.activity.base;

import android.os.Bundle;

import com.bw.myapplication.PictureModeApplication;
import com.bw.myapplication.R;
import com.bw.myapplication.ui.view.base.BaseView;
import com.github.obsessive.library.base.BaseAppCompatActivity;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**
 * Created by yw on 16/2/4.
 */
public abstract class BaseActivity extends BaseAppCompatActivity implements BaseView{

    protected android.support.v7.widget.Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isApplyKitKatTranslucency()) {
            setSystemBarTintDrawable(getResources().getDrawable(R.drawable.sr_primary));
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        mToolbar = ButterKnife.findById(this,R.id.common_toolbar);
        if (null != mToolbar){
            setSupportActionBar(mToolbar);
//            getSupportActionBar().setHomeButtonEnabled(true);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);

    }

    @Override
    public void showLoading(String msg) {

        toggleShowLoading(true, null);
    }

    @Override
    public void hideLoading() {

        toggleShowLoading(false, null);
    }

    @Override
    public void showError(String msg) {
        toggleShowError(true, msg, null);
    }

    @Override
    public void showException(String msg) {
        toggleShowError(true, msg, null);
    }

    @Override
    public void showNetError() {

        toggleNetworkError(true, null);
    }

    protected abstract boolean isApplyKitKatTranslucency();

    protected PictureModeApplication getBaseApplication() {
        return (PictureModeApplication) getApplication();
    }
}


