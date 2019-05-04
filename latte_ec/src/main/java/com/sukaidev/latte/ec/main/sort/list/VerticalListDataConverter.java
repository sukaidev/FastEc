package com.sukaidev.latte.ec.main.sort.list;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sukaidev.latte_core.ui.recycler.DataConverter;
import com.sukaidev.latte_core.ui.recycler.ItemType;
import com.sukaidev.latte_core.ui.recycler.MultipleFields;
import com.sukaidev.latte_core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by sukaidev on 2019/05/04.
 */
public class VerticalListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSONObject
                .parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id =  data.getInteger("id");
            final String name = data.getString("name");

//            LatteLogger.d(data.toString());

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.NAME,name)
                    .setField(MultipleFields.TAG,false)
                    .build();

            ENTITIES.add(entity);
            // 设置第一个被选中
            ENTITIES.get(0).setField(MultipleFields.TAG,true);
        }
        return ENTITIES;
    }
}
