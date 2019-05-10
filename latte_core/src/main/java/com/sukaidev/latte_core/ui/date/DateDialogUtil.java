package com.sukaidev.latte_core.ui.date;

import android.content.Context;
import android.content.DialogInterface;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.LinearLayoutCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by sukaidev on 2019/05/10.
 */
public class DateDialogUtil {

    public interface IDateListener {
        void onDateChange(String date);
    }

    private IDateListener mDateListener = null;

    public void setDateListener(IDateListener listener) {
        this.mDateListener = listener;
    }

    public void showDialog(Context context) {
        final LinearLayoutCompat ll = new LinearLayoutCompat(context);
        final DatePicker datePicker = new DatePicker(context);
        final LinearLayoutCompat.LayoutParams lp = new LinearLayoutCompat.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        datePicker.setLayoutParams(lp);

        datePicker.init(1990, 0, 1, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker picker, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
                String date = format.format(calendar.getTime());
                if (mDateListener != null) {
                    mDateListener.onDateChange(date);
                }
            }
        });

        ll.addView(datePicker);

        new AlertDialog.Builder(context)
                .setTitle("选择日期")
                .setView(ll)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}

