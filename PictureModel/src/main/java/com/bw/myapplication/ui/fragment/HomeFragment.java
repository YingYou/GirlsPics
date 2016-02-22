package com.bw.myapplication.ui.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.bw.myapplication.R;
import com.bw.myapplication.bean.BaseEntity;
import com.bw.myapplication.presenter.Presenter;
import com.bw.myapplication.presenter.impl.ImagesContainerPresenterImpl;
import com.bw.myapplication.ui.activity.base.BaseFragment;
import com.bw.myapplication.ui.adpter.ImagesContainerPagerAdapter;
import com.bw.myapplication.ui.view.CommonContainerView;
import com.github.obsessive.library.eventbus.EventCenter;
import com.github.obsessive.library.smartlayout.SmartTabLayout;
import com.github.obsessive.library.widgets.XViewPager;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by yw on 16-2-3.
 */
public class HomeFragment extends BaseFragment implements CommonContainerView{

    @InjectView(R.id.fragment_images_pager)
    XViewPager mViewPager;

    @InjectView(R.id.fragment_images_tab_smart)
    SmartTabLayout mSmartTabLayout;

    private Presenter mImagesContainerPresenter = null;

    @Override
    protected void onFirstUserVisible() {

        mImagesContainerPresenter = new ImagesContainerPresenterImpl(mContext, this);
        mImagesContainerPresenter.initialized();
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_images;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void initializePagerViews(final List<BaseEntity> categoryList) {


        if (null != categoryList && !categoryList.isEmpty()) {
            mViewPager.setOffscreenPageLimit(categoryList.size());
            mViewPager.setAdapter(new ImagesContainerPagerAdapter(getSupportFragmentManager(), categoryList));
            mSmartTabLayout.setViewPager(mViewPager);
            mSmartTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    ImagesListFragment fragment = (ImagesListFragment) mViewPager.getAdapter().instantiateItem(mViewPager, position);
                    fragment.onPageSelected(position, categoryList.get(position).getId());
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }
}
