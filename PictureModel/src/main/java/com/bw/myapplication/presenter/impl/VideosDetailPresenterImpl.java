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


import com.bw.myapplication.bean.VideosListUserEntity;
import com.bw.myapplication.interactor.GetVideoUserInteractor;
import com.bw.myapplication.interactor.impl.GetVideoUserInteractorImpl;
import com.bw.myapplication.listeners.BaseSingleLoadedListener;
import com.bw.myapplication.presenter.VideosDetailPresenter;
import com.bw.myapplication.ui.view.VideosDetailView;

/**
 * Author:  Tau.Chen
 * Email:   1076559197@qq.com | tauchen1990@gmail.com
 * Date:    2015/3/31.
 * Description:
 */
public class VideosDetailPresenterImpl implements VideosDetailPresenter, BaseSingleLoadedListener<VideosListUserEntity> {

    private VideosDetailView mVideosDetailView = null;
    private GetVideoUserInteractor mGetVideoUserInteractor = null;

    public VideosDetailPresenterImpl(VideosDetailView videosDetailView) {
        mGetVideoUserInteractor = new GetVideoUserInteractorImpl(this);
        mVideosDetailView = videosDetailView;
    }

    @Override
    public void onSuccess(VideosListUserEntity data) {
        mVideosDetailView.hideLoading();
        if (null != data) {
            mVideosDetailView.loadUser(data);
        }
    }

    @Override
    public void onError(String msg) {
        mVideosDetailView.hideLoading();
        mVideosDetailView.showError(msg);
    }

    @Override
    public void onException(String msg) {
        mVideosDetailView.hideLoading();
        mVideosDetailView.showError(msg);
    }

    @Override
    public void loadVideoUser(String requestTag, int userId) {
        mVideosDetailView.showLoading(null);
        mGetVideoUserInteractor.getVideoUser(requestTag, userId);
    }
}
