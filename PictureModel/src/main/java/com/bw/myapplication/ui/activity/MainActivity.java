package com.bw.myapplication.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RadioGroup;

import com.bw.myapplication.ForthFragment;
import com.bw.myapplication.R;
import com.bw.myapplication.SecondFragment;
import com.bw.myapplication.ThirdFragment;
import com.bw.myapplication.bean.NavigationEntity;
import com.bw.myapplication.presenter.Presenter;
import com.bw.myapplication.presenter.impl.HomePresenterImpl;
import com.bw.myapplication.ui.activity.base.BaseActivity;
import com.bw.myapplication.ui.fragment.HomeFragment;
import com.bw.myapplication.ui.view.HomeView;
import com.github.obsessive.library.base.BaseLazyFragment;
import com.github.obsessive.library.eventbus.EventCenter;
import com.github.obsessive.library.netstatus.NetUtils;

import java.util.List;

public class MainActivity extends BaseActivity implements HomeView {

    private RadioGroup tab_bottom;
    public static Fragment[] mFragments;
    private HomeFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;
    private ForthFragment forthFragment;

    private Presenter mHomePresenter = null;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return true;
    }

    public void initView() {
        mFragments = new Fragment[4];

        firstFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, firstFragment, "tag1").commit();

        secondFragment = new SecondFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, secondFragment, "tag2").commit();

        thirdFragment = new ThirdFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, thirdFragment, "tag3").commit();

        forthFragment = new ForthFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, forthFragment, "tag4").commit();

        mFragments[0] = firstFragment;
        mFragments[1] = secondFragment;
        mFragments[2] = thirdFragment;
        mFragments[3] = forthFragment;

        setFragmentIndicator(0);
        firstFragment.setUserVisibleHint(true);

        tab_bottom = (RadioGroup) findViewById(R.id.tab_menu);
        tab_bottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbHome: //首页

                        setFragmentIndicator(0);

                        break;
                    case R.id.rbSubject: //专题

                        setFragmentIndicator(1);

                        break;

                    case R.id.rbRamble: //漫谈

                        setFragmentIndicator(2);

                        break;
                    case R.id.rbMy: //我的

                        setFragmentIndicator(3);

                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void setFragmentIndicator(int whichIsDefault) {
        getSupportFragmentManager().beginTransaction().hide(mFragments[0])
                .hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3])
                .addToBackStack("home").show(mFragments[whichIsDefault])
                .commit();
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {

        mHomePresenter = new HomePresenterImpl(this, this);
        mHomePresenter.initialized();
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }

    @Override
    public void initializeViews(List<BaseLazyFragment> fragments, List<NavigationEntity> navigationList) {



    }
}
