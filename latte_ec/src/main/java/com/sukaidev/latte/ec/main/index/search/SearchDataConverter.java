package com.sukaidev.latte.ec.main.index.search;

import com.alibaba.fastjson.JSONArray;
import com.sukaidev.latte_core.ui.recycler.DataConverter;
import com.sukaidev.latte_core.ui.recycler.MultipleFields;
import com.sukaidev.latte_core.ui.recycler.MultipleItemEntity;
import com.sukaidev.latte_core.util.storage.LattePreference;

import java.util.ArrayList;

/**
 * Created by sukaidev on 2019/05/14.
 * 搜索页面数据转换.
 */
public class SearchDataConverter extends DataConverter {

    static final String TAG_SEARCH_HISTORY = "search_history";

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final String jsonStr = LattePreference.getCustomAppProfile(TAG_SEARCH_HISTORY);
        if (!jsonStr.equals("")) {
            final JSONArray array = JSONArray.parseArray(jsonStr);
            final int size = array.size();
            for (int i = 0; i < size; i++) {
                final String historyItemText = array.getString(i);
                final MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setItemType(SearchItemType.ITEM_SEARCH)
                        .setField(MultipleFields.TEXT, historyItemText)
                        .build();
                ENTITIES.add(entity);
            }
        }
        return ENTITIES;
    }
}
