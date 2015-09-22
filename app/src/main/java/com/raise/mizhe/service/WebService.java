package com.raise.mizhe.service;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.mizhe.model.Category;
import com.mizhe.model.Goods;
import com.mizhe.service.MiZheManager;

import java.util.List;


/**
 * Created by yu on 2015/9/2.
 */
public class WebService {

    private MiZheManager m_miZheManager = MiZheManager.instence();
    private static WebService instance;
    private static Handler m_handler;
    private static Context m_ctx;

    private WebService() {
        m_handler = new Handler();
    }

    public static WebService instance() {
        return instance;
    }

    public static void initWebService(Context context) {
        instance = new WebService();
        m_ctx = context;
    }

    public void getImage() {

    }


    public void getCategory(final WebServiceCallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Category> teMaiCategory = m_miZheManager.getTeMaiCategory();
                    notify_success(callBack, teMaiCategory);
                } catch (Exception e) {
                    String error = "getCategory failed:" + e.getMessage();
                    notify_failed(callBack, error);
                }
            }
        }).start();

    }

    public void getGoodsByCategory(final Category category, final int page, final WebServiceCallBack callBack) {
        if (category == null || category.getUrl().length()<0 ||page<0) {
            notify_failed(callBack, "argument exception about " + category.getName());
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("WebService", "getGoodsByCategory page = " + page);
                    List<Goods> goodsList = m_miZheManager.get_goods_by_category(category, page);
                    if (goodsList != null && goodsList.size() > 0) {
                        notify_success(callBack, goodsList);
                    } else {
                        notify_failed(callBack, "no more goods about " + category.getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    notify_failed(callBack, "exception: get goods in mizhewebservice about " + category.getName());
                }
            }
        }).start();
    }


    private void notify_success(final WebServiceCallBack callBack, final Object obj) {
        m_handler.post(new Runnable() {
            @Override
            public void run() {
                callBack.on_success(obj);
            }
        });
    }

    private void notify_failed(final WebServiceCallBack callBack, final String message) {
        m_handler.post(new Runnable() {
            @Override
            public void run() {
                callBack.on_failed(message);
            }
        });
    }

}
