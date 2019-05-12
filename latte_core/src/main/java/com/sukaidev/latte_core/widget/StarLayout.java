package com.sukaidev.latte_core.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.joanzapata.iconify.widget.IconTextView;
import com.sukaidev.latte_core.R;

import java.util.ArrayList;

/**
 * Created by sukaidev on 2019/05/12.
 * 自定义评分控件.
 */
public class StarLayout extends LinearLayoutCompat implements View.OnClickListener {

    private static final CharSequence ICON_UN_SELECT = "{fa-star-o}";
    private static final CharSequence ICON_SELECTED = "{fa-star}";
    private static final int STAR_TOTAL_COUNT = 5;
    private static final ArrayList<IconTextView> STARS = new ArrayList<>();

    private static final String TEXT_VERY_GOOD = "非常好";
    private static final String TEXT_GOOD = "很好";
    private static final String TEXT_NORMAL = "一般";
    private static final String TEXT_NOT_BAD = "较差";
    private static final String TEXT_VERY_BAD = "很差";

    private AppCompatTextView mTextView;

    public StarLayout(Context context) {
        this(context, null);
    }

    public StarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStarIcon();
        initTextView();
    }

    /**
     * 初始化字体图标
     */
    private void initStarIcon() {
        for (int i = 0; i < STAR_TOTAL_COUNT; i++) {
            final IconTextView star = new IconTextView(getContext());
            star.setGravity(Gravity.CENTER);
            final LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            star.setLayoutParams(lp);
            star.setText(ICON_UN_SELECT);
            star.setTag(R.id.star_count, i);
            star.setTag(R.id.star_is_selected, false);
            star.setOnClickListener(this);
            STARS.add(star);
            addView(star);
        }
    }

    /**
     * 初始化评价
     */
    private void initTextView() {
        mTextView = new AppCompatTextView(getContext());
        mTextView.setGravity(Gravity.CENTER);
        final LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.rightMargin = 16;
        mTextView.setLayoutParams(lp);
        mTextView.setTextSize(18);
        mTextView.setTextColor(Color.GRAY);
        mTextView.setText("请打分");
        addView(mTextView);
    }

    private void selectStar(int count) {
        for (int i = 0; i <= count; i++) {
            final IconTextView star = STARS.get(i);
            star.setText(ICON_SELECTED);
            star.setTextColor(Color.RED);
            star.setTag(R.id.star_is_selected, true);
        }
    }

    private void unSelectStar(int count) {
        for (int i = 0; i < STAR_TOTAL_COUNT; i++) {
            if (i > count) {
                final IconTextView star = STARS.get(i);
                star.setText(ICON_UN_SELECT);
                star.setTextColor(Color.GRAY);
                star.setTag(R.id.star_is_selected, false);
            }
        }
    }

    /**
     * 改变评分显示的文字.
     *
     * @param count 点击的是第几个星星
     */
    private void changeEvaluation(int count) {
        switch (count) {
            case 0:
                mTextView.setText(TEXT_VERY_BAD);
                break;
            case 1:
                mTextView.setText(TEXT_NOT_BAD);
                break;
            case 2:
                mTextView.setText(TEXT_NORMAL);
                break;
            case 3:
                mTextView.setText(TEXT_GOOD);
                break;
            case 4:
                mTextView.setText(TEXT_VERY_GOOD);
                break;
            default:
                break;
        }
    }

    /**
     * 获取评价
     *
     * @return 评价
     */
    @SuppressWarnings("unused")
    public int getStarCount() {
        int count = 0;
        for (int i = 0; i < STAR_TOTAL_COUNT; i++) {
            final IconTextView star = STARS.get(i);
            final boolean isSelect = (boolean) star.getTag(R.id.star_is_selected);
            if (isSelect) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void onClick(View v) {
        final IconTextView star = (IconTextView) v;
        // 获取第几个星星
        final int count = (int) star.getTag(R.id.star_count);
        // 获取点击状态
        final boolean isSelect = (boolean) star.getTag(R.id.star_is_selected);
        if (!isSelect) {
            selectStar(count);
        } else {
            unSelectStar(count);
        }
        changeEvaluation(count);
    }
}
