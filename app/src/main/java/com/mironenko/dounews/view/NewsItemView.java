package com.mironenko.dounews.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class NewsItemView extends ConstraintLayout {
    private Context context;
    private AttributeSet attrs;
    private int defStyleItem;
    private int defStyleRes;

//    private String

    public NewsItemView(@NonNull Context context) {
        super(context);
    }

    public NewsItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
