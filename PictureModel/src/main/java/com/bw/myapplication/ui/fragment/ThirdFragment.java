package com.bw.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.myapplication.bean.MusicsListEntity;
import com.bw.myapplication.bean.ResponseMusicsListentity;
import com.bw.myapplication.ui.activity.base.BaseFragment;
import com.bw.myapplication.ui.view.MusicsView;
import com.github.obsessive.library.eventbus.EventCenter;

/**
 * Created by Administrator on 16-2-3.
 */
public class ThirdFragment extends BaseFragment implements MusicsView{

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
    public void refreshMusicsList(ResponseMusicsListentity data) {

    }

    @Override
    public void addMoreMusicsList(ResponseMusicsListentity data) {

    }

    @Override
    public void rePlayMusic() {

    }

    @Override
    public void startPlayMusic() {

    }

    @Override
    public void stopPlayMusic() {

    }

    @Override
    public void pausePlayMusic() {

    }

    @Override
    public void playNextMusic() {

    }

    @Override
    public void playPrevMusic() {

    }

    @Override
    public void seekToPosition(int position) {

    }

    @Override
    public void refreshPageInfo(MusicsListEntity entity, int totalDuration) {

    }

    @Override
    public void refreshPlayProgress(int progress) {

    }

    @Override
    public void refreshPlaySecondProgress(int progress) {

    }
}
