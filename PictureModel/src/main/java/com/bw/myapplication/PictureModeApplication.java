package com.bw.myapplication;

import com.bw.myapplication.api.ApiConstants;
import com.bw.myapplication.utils.ImageLoaderHelper;
import com.bw.myapplication.utils.VolleyHelper;
import com.github.obsessive.library.base.BaseAppManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;
import com.youku.player.YoukuPlayerBaseApplication;

/**
 * Created by yw on 16/2/22.
 */
public class PictureModeApplication extends YoukuPlayerBaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        MobclickAgent.setDebugMode(true);
        MobclickAgent.updateOnlineConfig(this);
        MobclickAgent.openActivityDurationTrack(false);
        UmengUpdateAgent.update(this);

        VolleyHelper.getInstance().init(this);
        ImageLoader.getInstance().init(ImageLoaderHelper.getInstance(this).getImageLoaderConfiguration(ApiConstants.Paths.IMAGE_LOADER_CACHE_PATH));
    }

    @Override
    public String configDownloadPath() {
        return null;
    }

    @Override
    public void onLowMemory() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onLowMemory();
    }

    public void exitApp() {
        BaseAppManager.getInstance().clear();
        System.gc();
        MobclickAgent.onKillProcess(this);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
