package com.bw.myapplication.interactor;

import com.github.obsessive.library.base.BaseLazyFragment;

import java.util.List;

/**
 * Created by yw on 16/2/4.
 */
public interface HomeInteractor {

    List<BaseLazyFragment> getPagerFragments();
}
