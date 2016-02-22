package com.bw.myapplication.ui.view;

import com.bw.myapplication.bean.BaseEntity;

import java.util.List;

/**
 * Created by yw on 16/2/4.
 */
public interface CommonContainerView {

    void initializePagerViews(List<BaseEntity> categoryList);
}
