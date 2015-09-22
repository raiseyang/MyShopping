package com.raise;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.raise.fanli.FanliFragment;
import com.raise.fragment.BaseFragment;
import com.raise.mizhe.fragment.MizheFragment;
import com.raise.view.MainTabView;
import com.raise.wind.myshopping.R;


public class MainActivity extends FragmentActivity {

    private final static String TAG = "MainActivity";

    private MainTabView m_mainTabView;

    private MizheFragment m_mizheFragment;
    private FanliFragment m_fanliFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_mainTabView = (MainTabView) findViewById(R.id.view_main_tab_view);
        m_mainTabView.setListener(new MainTabView.OnMainTabListener() {
            @Override
            public void on_click_item(int index) {
                if (MainTabView.INDEX_MIZHE == index) {
                    show_fragment_by_tab(m_mizheFragment);
                } else if (MainTabView.INDEX_FANLI == index) {
                    show_fragment_by_tab(m_fanliFragment);
                }
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        m_mizheFragment = (MizheFragment) fm.findFragmentByTag(MizheFragment.class.getSimpleName());
        if (m_mizheFragment == null) {
            m_mizheFragment = new MizheFragment();
        }
        m_fanliFragment = (FanliFragment) fm.findFragmentByTag(FanliFragment.class.getSimpleName());
        if (m_fanliFragment == null) {
            m_fanliFragment = new FanliFragment();
        }
        m_mainTabView.initPosition();
    }

    private void show_fragment_by_tab(BaseFragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, MizheFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


}
