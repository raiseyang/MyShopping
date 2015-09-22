package com.raise.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.raise.wind.myshopping.R;

/**
 * Created by yu on 2015/9/15.
 */
public class MainTabView extends LinearLayout {

    public static final int COUNT_TAB_ITEM = 2;
    public static final int INDEX_MIZHE = 0;
    public static final int INDEX_FANLI = 1;

    private int m_current_index;

    private TextView[] m_item_tv = new TextView[COUNT_TAB_ITEM];

    private OnMainTabListener m_listener;

    public MainTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View root = LayoutInflater.from(context).inflate(R.layout.view_main_tab, this);
        m_item_tv[0] = (TextView) root.findViewById(R.id.textview_mizhe);
        m_item_tv[1] = (TextView) root.findViewById(R.id.textview_fanli);

        for (int i = 0; i < COUNT_TAB_ITEM; i++) {
            final int finalI = i;
            m_item_tv[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelector(finalI);
                    if (m_listener != null)
                        m_listener.on_click_item(finalI);
                }
            });
        }

    }

    public void setSelector(int index) {
        for (int i = 0; i < COUNT_TAB_ITEM; i++) {
            m_item_tv[i].setTextColor(Color.GRAY);
        }
        m_item_tv[index].setTextColor(Color.BLUE);
        m_current_index = index;
    }

    public void setListener(OnMainTabListener listener) {
        m_listener = listener;
    }

    public void initPosition() {
        setSelector(INDEX_MIZHE);
        if (m_listener != null)
            m_listener.on_click_item(INDEX_MIZHE);
    }

    public interface OnMainTabListener {
        void on_click_item(int index);
    }
}
