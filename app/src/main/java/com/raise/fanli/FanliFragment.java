package com.raise.fanli;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raise.fragment.BaseFragment;
import com.raise.wind.myshopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FanliFragment extends BaseFragment {


    public FanliFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fanli, container, false);
    }

    @Override
    protected void lazyload() {
        
    }

    @Override
    protected void onInvisible() {

    }


}
