package com.raise.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yu on 2015/9/7.
 */
public abstract class BaseFragment extends Fragment {

    protected boolean m_is_visible;
    protected boolean m_is_prepared;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            m_is_visible = true;
            onVisible();
        } else {
            m_is_visible = false;
            onInvisible();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        Log.d(getClass().getSimpleName(), "onAttach" );
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(getClass().getSimpleName(), "onCreate" );
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(getClass().getSimpleName(), "onCreateView" );
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d(getClass().getSimpleName(), "onViewCreated" );
        m_is_prepared = true;
        lazyload();
    }


    protected abstract void lazyload();

    protected abstract void onInvisible();

    protected void onVisible() {
            lazyload();
    }
}
