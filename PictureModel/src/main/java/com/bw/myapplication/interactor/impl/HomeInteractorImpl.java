package com.bw.myapplication.interactor.impl;


import com.bw.myapplication.interactor.HomeInteractor;
import com.bw.myapplication.ui.fragment.HomeFragment;
import com.bw.myapplication.ui.fragment.MusicsFragment;
import com.bw.myapplication.ui.fragment.VideosContainerFragment;
import com.github.obsessive.library.base.BaseLazyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yw on 16/2/4.
 */
public class HomeInteractorImpl implements HomeInteractor {

    @Override
    public List<BaseLazyFragment> getPagerFragments() {
        List<BaseLazyFragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new VideosContainerFragment());
        fragments.add(new MusicsFragment());
        return fragments;
    }

}
