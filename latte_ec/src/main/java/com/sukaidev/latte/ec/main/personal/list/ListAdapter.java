package com.sukaidev.latte.ec.main.personal.list;

import androidx.appcompat.widget.SwitchCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sukaidev.latte.ec.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sukaidev on 2019/05/09.
 */
public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListItemType.ITEM_NORMAL, R.layout.arrow_item_layout);
        addItemType(ListItemType.ITEM_AVATAR, R.layout.arrow_item_avatar);
        addItemType(ListItemType.ITEM_SWITCH, R.layout.arrow_switch_layout);
    }

    @Override
    protected void convert(BaseViewHolder holder, ListBean item) {
        switch (holder.getItemViewType()) {
            case ListItemType.ITEM_NORMAL:
                holder.setText(R.id.tv_arrow_text, item.getText());
                holder.setText(R.id.tv_arrow_value, item.getValue());
                break;
            case ListItemType.ITEM_AVATAR:
                String imgUrl = item.getImageUrl();
                CircleImageView imageView = holder.getView(R.id.img_arrow_avatar);

                Glide.with(mContext)
                        .load(imgUrl)
                        .apply(OPTIONS)
                        .into(imageView);
                break;
            case ListItemType.ITEM_SWITCH:
                holder.setText(R.id.tv_arrow_switch_text,item.getText());
                final SwitchCompat switchCompat = holder.getView(R.id.list_item_switch);
                switchCompat.setChecked(true);
                switchCompat.setOnCheckedChangeListener(item.getOnCheckedChangeListener());
                break;
            default:
                break;
        }

    }
}
