package com.sukaidev.latte.ec.detail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sukaidev.latte.ec.R;
import com.sukaidev.latte.ec.R2;
import com.sukaidev.latte_core.delegates.LatteDelegate;
import com.sukaidev.latte_core.ui.recycler.ItemType;
import com.sukaidev.latte_core.ui.recycler.MultipleFields;
import com.sukaidev.latte_core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by sukaidev on 2019/05/15.
 */
public class ImageDelegate extends LatteDelegate {

    private static final String ARG_PICTURES = "ARG_PICTURES";

    @BindView(R2.id.rv_image_container)
    RecyclerView mRecyclerView = null;

    public static ImageDelegate create(ArrayList<String> pictures) {
        final Bundle args = new Bundle();
        args.putStringArrayList(ARG_PICTURES, pictures);
        final ImageDelegate detailDelegate = new ImageDelegate();
        detailDelegate.setArguments(args);
        return detailDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_image;
    }

    @SuppressWarnings("ConstantConditions")
    private void initImages() {
        final ArrayList<String> pictures = getArguments().getStringArrayList(ARG_PICTURES);
        final ArrayList<MultipleItemEntity> entities = new ArrayList<>();
        final int size;
        if (pictures != null) {
            size = pictures.size();
            for (int i = 0; i < size; i++) {
                final String imageUrl = pictures.get(i);
                final MultipleItemEntity entity = MultipleItemEntity
                        .builder()
                        .setItemType(ItemType.SINGLE_BIG_IMAGE)
                        .setField(MultipleFields.IMAGE_URL, imageUrl)
                        .build();
                entities.add(entity);
            }
            final RecyclerImageAdapter adapter = new RecyclerImageAdapter(entities);
            mRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        initImages();
    }
}
