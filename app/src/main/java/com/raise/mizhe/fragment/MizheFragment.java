package com.raise.mizhe.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.mizhelibrary.model.Category;
import com.raise.fragment.BaseFragment;
import com.raise.mizhe.data.MizheDataManager;
import com.raise.wind.myshopping.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MizheFragment extends BaseFragment {

    private PagerSlidingTabStrip tabs;
    private ViewPager viewpager;
    private List<Fragment> m_fragments = new ArrayList<Fragment>();
    private ViewPagerAdapter m_adapter;
    private List<Category> m_categories;


    public MizheFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m_categories = MizheDataManager.getInstance().getCategories();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mizhe, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        if (m_fragments != null && m_fragments.size() == 0) {
            for (int i = 0; i < m_categories.size(); i++) {
                Fragment fragment = MizheGoodsFragment.newInstance(m_categories.get(i), "2");
                m_fragments.add(fragment);
            }
        }

        // Initialize the ViewPager and set an adapter
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        m_adapter = new ViewPagerAdapter(getChildFragmentManager());
        viewpager.setAdapter(m_adapter);

        // Bind the tabs to the ViewPager
        tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        tabs.setViewPager(viewpager);
//        tabs.setShouldExpand(true);
        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void lazyload() {

    }

    @Override
    protected void onInvisible() {

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {


        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            return ((MizheGoodsFragment)m_fragments.get(position)).getmParam2() == null ?"":
//            ((MizheGoodsFragment)m_fragments.get(position)).getmParam2();
            return m_categories.get(position).getName();
        }

        @Override
        public Fragment getItem(int position) {
            return m_fragments.get(position);
        }

        @Override
        public int getCount() {
            return m_fragments.size();
        }
    }

}
