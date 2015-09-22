package com.raise.mizhe.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mizhe.model.Category;
import com.mizhe.model.Goods;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.raise.App;
import com.raise.fragment.BaseFragment;
import com.raise.mizhe.adapter.CustomAdapter;
import com.raise.mizhe.service.WebService;
import com.raise.mizhe.service.WebServiceCallBack;
import com.raise.paginglistview.PagingListView;
import com.raise.wind.myshopping.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MizheGoodsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MizheGoodsFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String TAG = "MizheGoodsFragment";

    // TODO: Rename and change types of parameters
//    private String mParam1;
    private String mParam2;

    private Category m_category;
    private WebService m_webService;
    private ArrayList<Goods> m_goodsList;
    private TextView name;
    private PagingListView m_listView;
    private GoodsAdapter m_adapter;
    private int m_data_cur_page = 0;
    private ProgressBar m_loading_view;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param category Parameter 1.
     * @param param2   Parameter 2.
     * @return A new instance of fragment MizheGoodsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MizheGoodsFragment newInstance(Category category, String param2) {
        MizheGoodsFragment fragment = new MizheGoodsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, category);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MizheGoodsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            m_category = (Category) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        init_tag();
        m_webService = WebService.instance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_mizhe_goods, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
//        name = (TextView) view.findViewById(R.id.textview_name);
//        name.setText(mParam2);
        m_loading_view = (ProgressBar) view.findViewById(R.id.loading_progress);

        m_adapter = new GoodsAdapter(getActivity(), R.layout.list_item_mizhe_goods);


        m_listView = (PagingListView) view.findViewById(R.id.listview_fragment);
//        m_listView.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), true, true));
        m_listView.setAdapter(m_adapter);
        m_listView.setHasMoreItems(false);
        m_listView.setPagingableListener(new PagingListView.Pagingable() {
            @Override
            public void onLoadMoreItems() {
                Log.d(TAG, "m_listView  onLoadMoreItems");
                refresh_net_data();
            }
        });

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "setUserVisibleHint :" + getUserVisibleHint());
    }

    @Override
    protected void lazyload() {
        Log.d(TAG, "lazyload visible" + m_is_visible + "  prepared" + m_is_prepared);
        if (!m_is_visible || !m_is_prepared) {
            return;
        }
        if (m_goodsList == null)
            refresh_net_data();
        else
            refresh_adapter_data();
    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);

    }

    public void refresh_adapter_data() {
        m_adapter.setDates(m_goodsList);

    }

    private void refresh_net_data() {
        Log.d(TAG, "refresh_net_data");
        m_webService.getGoodsByCategory(m_category, m_data_cur_page + 1, new WebServiceCallBack<ArrayList<Goods>>() {
            @Override
            public void on_success(ArrayList<Goods> ret) {
                if (ret == null || ret.size() == 0) {
                    m_listView.onFinishLoading(false, null);
                } else {
                    if (m_goodsList == null || m_goodsList.size() == 0) {
                        m_goodsList = ret;
                        m_listView.setHasMoreItems(true);
                        m_loading_view.setVisibility(View.GONE);
                    } else {
                        m_goodsList.addAll(ret);
                    }
                    refresh_adapter_data();
                    m_data_cur_page += 1;
                    m_listView.onFinishLoading(true, null);
                }
            }

            @Override
            public void on_failed(String error) {
                Log.d(TAG, error);
                App.show_toast("get goods failed,page :" + m_data_cur_page);
                m_listView.onFinishLoading(false, null);
            }
        });
    }


    private void init_tag() {
        if (m_category != null) {
            TAG += m_category.getName();
        }
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        if (m_goodsList != null) {
            m_loading_view.setVisibility(View.GONE);
            refresh_adapter_data();
        }
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }


    public String getmParam2() {
        return mParam2;
    }

    public void setmParam2(String mParam2) {
        this.mParam2 = mParam2;
    }


    class GoodsAdapter extends CustomAdapter<Goods> {

        public GoodsAdapter(Context context, int layout_id) {
            super(context, layout_id);
        }

        @Override
        public void showView(Goods item, View view, int position) {
            ViewHolder holder;
            if (view.getTag() == null) {
                holder = new ViewHolder();
                holder.goods_image = (ImageView) view.findViewById(R.id.imageview_goods);
                holder.price = (TextView) view.findViewById(R.id.textview_price);
                holder.name = (TextView) view.findViewById(R.id.textview_name);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.price.setText(item.getPrice());
            holder.name.setText(item.getDescribe());
            ImageLoader.getInstance().displayImage(item.getUrl_big_iamge(), holder.goods_image
//                    , new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_launcher).build()
            );


        }

        class ViewHolder {
            ImageView goods_image;
            TextView price;
            TextView name;
        }
    }
}
