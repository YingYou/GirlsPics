package com.bw.myapplication.interactor.impl;

import com.bw.myapplication.SecondFragment;
import com.bw.myapplication.ThirdFragment;
import com.bw.myapplication.interactor.HomeInteractor;
import com.bw.myapplication.ui.fragment.HomeFragment;
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
        fragments.add(new SecondFragment());
        fragments.add(new ThirdFragment());
        return fragments;
    }

}
