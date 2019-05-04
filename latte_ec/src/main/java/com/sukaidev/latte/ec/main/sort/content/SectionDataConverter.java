package com.sukaidev.latte.ec.main.sort.content;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sukaidev on 2019/05/04.
 */
public class SectionDataConverter {

    final List<SectionBean> convert(String json) {

        final List<SectionBean> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(json).getJSONArray("data");

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String title = data.getString("section");

            // 添加title,注意这里必须为true，表示该部分数据为分组的标题部分
            final SectionBean sectionBean = new SectionBean(true, title);
            sectionBean.setId(id);
            sectionBean.setIsMore(true);
            dataList.add(sectionBean);

            final JSONArray goods = data.getJSONArray("goods");
            // 商品内容循环
            final int goodSize = goods.size();
            for (int j = 0; j < goodSize; j++) {
                final JSONObject contentItem = goods.getJSONObject(j);
                final int goodsId = contentItem.getInteger("goods_id");
                final String goodsName = contentItem.getString("goods_name");
                final String goodsThumb = contentItem.getString("goods_thumb");
                // 获取内容
                final SectionContentItemEntity itemEntity = new SectionContentItemEntity();
                itemEntity.setGoodsId(goodsId);
                itemEntity.setGoddsName(goodsName);
                itemEntity.setGoodsThumb(goodsThumb);
                // 添加内容
                dataList.add(new SectionBean(itemEntity));
            }
        }

        return dataList;
    }
}
