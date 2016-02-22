package com.bw.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.myapplication.bean.BaseEntity;
import com.bw.myapplication.ui.activity.base.BaseFragment;
import com.bw.myapplication.ui.view.CommonContainerView;
import com.github.obsessive.library.eventbus.EventCenter;

import java.util.List;

/**
 * Created by Administrator on 16-2-3.
 */
public class SecondFragment extends BaseFragment implements CommonContainerView{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main,container,false);
        return view;
    }

    @Override
    protected void onFirstUserVisible() {

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
        return 0;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }


    @Override
    public void initializePagerViews(List<BaseEntity> categoryList) {

    }
}
