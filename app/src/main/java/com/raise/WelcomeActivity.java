package com.raise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.example.mizhelibrary.model.Category;
import com.raise.mizhe.data.MizheDataManager;
import com.raise.mizhe.service.WebService;
import com.raise.mizhe.service.WebServiceCallBack;
import com.raise.wind.myshopping.R;

import java.util.ArrayList;

/**
 * Created by yu on 2015/9/2.
 */
public class WelcomeActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_welcome);
        go_home_page();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void go_home_page() {
        WebService.instance().getCategory(new WebServiceCallBack<ArrayList<Category>>() {
            @Override
            public void on_success(ArrayList<Category> ret) {
                if (ret != null && ret.size() > 0) {
                    MizheDataManager.getInstance().setCategories(ret);
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    App.show_toast("获取数据失败");
                }
            }

            @Override
            public void on_failed(String error) {

            }
        });
    }
}
