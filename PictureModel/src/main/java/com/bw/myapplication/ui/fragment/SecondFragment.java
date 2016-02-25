package com.bw.myapplication.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.myapplication.R;
import com.bw.myapplication.api.ApiConstants;
import com.bw.myapplication.bean.ResponseVideosListEntity;
import com.bw.myapplication.bean.VideosListEntity;
import com.bw.myapplication.common.Constants;
import com.bw.myapplication.common.OnCommonPageSelectedListener;
import com.bw.myapplication.presenter.VideosListPresenter;
import com.bw.myapplication.presenter.impl.VideosListPresenterImpl;
import com.bw.myapplication.ui.activity.PlayerActivity;
import com.bw.myapplication.ui.activity.base.BaseFragment;
import com.bw.myapplication.ui.view.VideosListView;
import com.bw.myapplication.ui.widgets.LoadMoreListView;
import com.bw.myapplication.utils.UriHelper;
import com.github.obsessive.library.adapter.ListViewDataAdapter;
import com.github.obsessive.library.adapter.MultiItemRowListAdapter;
import com.github.obsessive.library.adapter.ViewHolderBase;
import com.github.obsessive.library.adapter.ViewHolderCreator;
import com.github.obsessive.library.eventbus.EventCenter;
import com.github.obsessive.library.netstatus.NetUtils;
import com.github.obsessive.library.utils.CommonUtils;
import com.github.obsessive.library.widgets.XSwipeRefreshLayout;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 16-2-3.
 */
public class SecondFragment extends BaseFragment implements VideosListView,OnCommonPageSelectedListener,LoadMoreListView.OnLoadMoreListener,SwipeRefreshLayout.OnRefreshListener{

    @InjectView(R.id.fragment_videos_list_swipe_layout)
    XSwipeRefreshLayout mSwipeRefreshLayout;

    @InjectView(R.id.fragment_videos_list_list_view)
    LoadMoreListView mListView;

    /**
     * this variable must be initialized.
     */
    private static String mCurrentVideosCategory = null;
    /**
     * the page number
     */
    private int mCurrentPage = 1;

    private VideosListPresenter mVideosListPresenter = null;

    private MultiItemRowListAdapter mMultiItemRowListAdapter = null;
    private ListViewDataAdapter<VideosListEntity> mListViewAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentVideosCategory = getResources().getStringArray(R.array.videos_category_list)[0];
    }

    @Override
    protected void onFirstUserVisible() {

        mCurrentPage = 1;
        mVideosListPresenter = new VideosListPresenterImpl(mContext, this);

        if (NetUtils.isNetworkConnected(mContext)) {
            if (null != mSwipeRefreshLayout) {
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mVideosListPresenter.loadListData(TAG_LOG, Constants.EVENT_REFRESH_DATA, mCurrentVideosCategory,
                                mCurrentPage, false);
                    }
                }, ApiConstants.Integers.PAGE_LAZY_LOAD_DELAY_TIME_MS);
            }
        } else {
            toggleNetworkError(true, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mVideosListPresenter.loadListData(TAG_LOG, Constants.EVENT_REFRESH_DATA, mCurrentVideosCategory,
                            mCurrentPage, false);
                }
            });
        }
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected View getLoadingTargetView() {
        return mSwipeRefreshLayout;
    }

    @Override
    protected void initViewsAndEvents() {

        mListViewAdapter = new ListViewDataAdapter<VideosListEntity>(new ViewHolderCreator<VideosListEntity>() {
            @Override
            public ViewHolderBase<VideosListEntity> createViewHolder(int position) {
                return new ViewHolderBase<VideosListEntity>() {

                    TextView mItemTitle;
                    ImageView mItemImage;
                    ImageButton mItemPlay;

                    @Override
                    public View createView(LayoutInflater layoutInflater) {
                        View convertView = layoutInflater.inflate(R.layout.list_item_videos_card, null);

                        mItemTitle = ButterKnife.findById(convertView, R.id.list_item_videos_card_title);
                        mItemImage = ButterKnife.findById(convertView, R.id.list_item_videos_card_image);
                        mItemPlay = ButterKnife.findById(convertView, R.id.list_item_videos_card_play);

                        return convertView;
                    }

                    @Override
                    public void showData(final int position, VideosListEntity itemData) {
                        if (null != itemData) {
                            if (!CommonUtils.isEmpty(itemData.getTitle())) {
                                mItemTitle.setText(CommonUtils.decodeUnicodeStr(itemData.getTitle()));
                            }

                            if (!CommonUtils.isEmpty(itemData.getThumbnail_v2())) {
                                ImageLoader.getInstance().displayImage(itemData.getThumbnail_v2(), mItemImage);
                            }

                            mItemPlay.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (null != mListViewAdapter) {
                                        if (position >= 0 && position < mListViewAdapter.getDataList().size()) {
                                            mVideosListPresenter.onItemClickListener(position, mListViewAdapter.getDataList().get(position));
                                        }
                                    }
                                }
                            });
                        }
                    }
                };
            }
        });

        mMultiItemRowListAdapter = new MultiItemRowListAdapter(mContext, mListViewAdapter, 1, 0);

        mListView.setAdapter(mMultiItemRowListAdapter);
        mListView.setOnLoadMoreListener(this);

        mSwipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.gplus_color_1),
                getResources().getColor(R.color.gplus_color_2),
                getResources().getColor(R.color.gplus_color_3),
                getResources().getColor(R.color.gplus_color_4));
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_videos_list;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }


    @Override
    public void onPageSelected(int position, String keywords) {


            mCurrentVideosCategory = keywords;
    }

    @Override
    public void onLoadMore() {

        mCurrentPage++;
        mVideosListPresenter.loadListData(TAG_LOG, Constants.EVENT_LOAD_MORE_DATA, mCurrentVideosCategory, mCurrentPage, true);
    }

    @Override
    public void onRefresh() {

        mCurrentPage = 1;
        mVideosListPresenter.loadListData(TAG_LOG, Constants.EVENT_REFRESH_DATA, mCurrentVideosCategory, mCurrentPage,
                true);
    }

    @Override
    public void refreshListData(ResponseVideosListEntity responseVideosListEntity) {

        if (null != mSwipeRefreshLayout) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        if (null != responseVideosListEntity && null != responseVideosListEntity.getVideos() && !responseVideosListEntity.getVideos().isEmpty()) {
            if (null != mListViewAdapter) {
                mListViewAdapter.getDataList().clear();
                mListViewAdapter.getDataList().addAll(responseVideosListEntity.getVideos());
                mListViewAdapter.notifyDataSetChanged();
            }

            if (UriHelper.getInstance().calculateTotalPages(responseVideosListEntity.getTotal()) > mCurrentPage) {
                mListView.setCanLoadMore(true);
            } else {
                mListView.setCanLoadMore(false);
            }
        }
    }

    @Override
    public void addMoreListData(ResponseVideosListEntity responseVideosListEntity) {

        if (null != mListView) {
            mListView.onLoadMoreComplete();
        }

        if (null != responseVideosListEntity && null != responseVideosListEntity.getVideos() && !responseVideosListEntity.getVideos().isEmpty()) {
            if (null != mListViewAdapter) {
                mListViewAdapter.getDataList().addAll(responseVideosListEntity.getVideos());
                mListViewAdapter.notifyDataSetChanged();
            }

            if (UriHelper.getInstance().calculateTotalPages(responseVideosListEntity.getTotal()) > mCurrentPage) {
                mListView.setCanLoadMore(true);
            } else {
                mListView.setCanLoadMore(false);
            }
        }
    }

    @Override
    public void navigateToNewsDetail(int position, VideosListEntity entity) {

        Bundle extras = new Bundle();
        extras.putParcelable(PlayerActivity.INTENT_VIDEO_EXTRAS, entity);
        readyGo(PlayerActivity.class, extras);
    }

    @Override
    public void showError(String msg) {
        if (null != mSwipeRefreshLayout) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        toggleShowError(true, msg, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideosListPresenter.loadListData(TAG_LOG, Constants.EVENT_REFRESH_DATA, mCurrentVideosCategory,
                        mCurrentPage, false);
            }
        });
    }
}
