package com.raise.mizhe.data;

import com.mizhe.model.Category;

import java.util.List;

/**
 * Created by yu on 2015/9/16.
 */
public class MizheDataManager {

    private static MizheDataManager instance;
    private List<Category> m_categories;

    private MizheDataManager() {
    }

    public static MizheDataManager getInstance() {
        if (instance == null)
            instance = new MizheDataManager();
        return instance;
    }

    public List<Category> getCategories() {
        return m_categories;
    }

    public void setCategories(List<Category> categories) {
        m_categories = categories;
    }
}
