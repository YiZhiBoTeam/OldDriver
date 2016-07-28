package com.example.rp.ezhibo.view;

import android.app.Application;
import android.os.Environment;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * Created by rp on 2016/7/26.
 */
public class MyApp extends Application {
    private static RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        queue= Volley.newRequestQueue(getApplicationContext());
        initUIL();
    }
    public static  RequestQueue getQueue(){
        return  queue;
    }

 private void initUIL()
{
    File file=new File(Environment.getExternalStorageDirectory(),"imgcache");
    ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)
            .threadPoolSize(3)
            .memoryCacheSize(10*1024*1024)
            .diskCacheSize(100*1024*1024)
            .diskCacheFileCount(100)
            .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
            .writeDebugLogs()
            .diskCache(new UnlimitedDiskCache(file))
            .build();
    ImageLoader.getInstance().init(configuration);
}
}
