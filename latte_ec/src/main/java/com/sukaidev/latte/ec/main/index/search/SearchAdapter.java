package com.sukaidev.latte.ec.main.index.search;

import androidx.appcompat.widget.AppCompatTextView;

import com.sukaidev.latte.ec.R;
import com.sukaidev.latte_core.ui.recycler.MultipleFields;
import com.sukaidev.latte_core.ui.recycler.MultipleItemEntity;
import com.sukaidev.latte_core.ui.recycler.MultipleRecyclerAdapter;
import com.sukaidev.latte_core.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * Created by sukaidev on 2019/05/14.
 */
public class SearchAdapter extends MultipleRecyclerAdapter {

    protected SearchAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(SearchItemType.ITEM_SEARCH, R.layout.item_search);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (entity.getItemType()) {
            case SearchItemType.ITEM_SEARCH:
                final AppCompatTextView tvSearch = holder.getView(R.id.tv_search_item);
                final String history = entity.getField(MultipleFields.TEXT);
                tvSearch.setText(history);
                break;
            default:
                break;
        }
    }
}
