/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bw.myapplication.presenter.impl;

import android.content.Context;

import com.bw.myapplication.R;
import com.bw.myapplication.bean.ResponseVideosListEntity;
import com.bw.myapplication.bean.VideosListEntity;
import com.bw.myapplication.common.Constants;
import com.bw.myapplication.interactor.CommonListInteractor;
import com.bw.myapplication.interactor.impl.VideosListInteractorImpl;
import com.bw.myapplication.listeners.BaseMultiLoadedListener;
import com.bw.myapplication.presenter.VideosListPresenter;
import com.bw.myapplication.ui.view.VideosListView;


/**
 * Author:  Tau.Chen
 * Email:   1076559197@qq.com | tauchen1990@gmail.com
 * Date:    2015/4/9.
 * Description:
 */
public class VideosListPresenterImpl implements VideosListPresenter, BaseMultiLoadedListener<ResponseVideosListEntity> {

    private Context mContext = null;
    private VideosListView mVideosListView = null;
    private CommonListInteractor mCommonListInteractor = null;

    public VideosListPresenterImpl(Context context, VideosListView videosListView) {
        mContext = context;
        mVideosListView = videosListView;
        mCommonListInteractor = new VideosListInteractorImpl(this);
    }

    @Override
    public void onSuccess(int event_tag, ResponseVideosListEntity data) {
        mVideosListView.hideLoading();
        if (event_tag == Constants.EVENT_REFRESH_DATA) {
            mVideosListView.refreshListData(data);
        } else if (event_tag == Constants.EVENT_LOAD_MORE_DATA) {
            mVideosListView.addMoreListData(data);
        }
    }

    @Override
    public void onError(String msg) {
        mVideosListView.hideLoading();
        mVideosListView.showError(msg);
    }

    @Override
    public void onException(String msg) {
        mVideosListView.hideLoading();
        mVideosListView.showError(msg);
    }

    @Override
    public void loadListData(String requestTag, int event_tag, String keywords, int page, boolean isSwipeRefresh) {
        mVideosListView.hideLoading();
        if (!isSwipeRefresh) {
            mVideosListView.showLoading(mContext.getString(R.string.common_loading_message));
        }
        mCommonListInteractor.getCommonListData(requestTag, event_tag, keywords, page);
    }

    @Override
    public void onItemClickListener(int position, VideosListEntity entity) {
        mVideosListView.navigateToNewsDetail(position, entity);
    }
}