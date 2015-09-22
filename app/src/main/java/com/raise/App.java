package com.raise;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.raise.mizhe.service.WebService;
import com.raise.wind.myshopping.R;

/**
 * Created by yu on 2015/9/2.
 */
public class App extends Application {
    private static Toast m_toast;

    @Override
    public void onCreate() {
        super.onCreate();
        WebService.initWebService(getApplicationContext());
        initImageLoader(this);

        m_toast = new Toast(this);


//        ImageLoaderConfiguration config = new ImageLoaderConfiguration
//        .Builder(this)
////        .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
//        .threadPoolSize(3)//线程池内加载的数量
//        .threadPriority(Thread.NORM_PRIORITY - 2)
////        .denyCacheImageMultipleSizesInMemory()
////        .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
//        .memoryCacheSize(2 * 1024 * 1024)
////        .discCacheSize(50 * 1024 * 1024)
////        .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
//        .tasksProcessingOrder(QueueProcessingType.LIFO)
////        .discCacheFileCount(100) //缓存的文件数量
//        .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
//        .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
//        .writeDebugLogs() // Remove for release app
//        .build();//开始构建
//        // Initialize ImageLoader with configuration.
//        ImageLoader.getInstance().init(config);
    }


    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        //默认的options
        DisplayImageOptions imageOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_launcher)
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .showImageOnFail(R.drawable.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
//                .displayer(new CircleBitmapDisplayer(Color.WHITE, 5))
                .build();
        config.defaultDisplayImageOptions(imageOptions);

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }



    public static void show_toast(String message) {
        m_toast.cancel();
        m_toast.setText(message);
        m_toast.setDuration(Toast.LENGTH_SHORT);
        m_toast.show();
    }
}
