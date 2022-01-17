package com.ds.nofication.Adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class CustomMedicineInfoExpList extends LinearLayout {

    private TextView title;
    private LinearLayout innerLayout;
    private ScrollView scroll;
    private boolean expanded;

    public CustomMedicineInfoExpList(Context context) {
        super(context);
        init(null, 0);
        createChildren(context);
    }

    public CustomMedicineInfoExpList(Context context, AttributeSet attrs) {
        super(context);
        init(attrs, 0);
        createChildren(context);
    }

    public CustomMedicineInfoExpList(Context context, AttributeSet attrs, int defstyle) {
        super(context);
        init(attrs, defstyle);

        createChildren(context);
    }

    private void init(AttributeSet attrs, int defstyle) {
    }

    private void createChildren(Context context) {

        title = new TextView(context);
        title.setText("Hello");
        title.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(title);

        scroll = new ScrollView(context);
        scroll.setVisibility(INVISIBLE);
        scroll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(scroll);

        innerLayout = new LinearLayout(context);
        innerLayout.setOrientation(VERTICAL);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        scroll.addView(innerLayout);

        setOnClickListener(onClickLayout());
    }

    @Override
    public void addView(View child) {
        if (getChildCount() < 2)
            super.addView(child);
        else
            innerLayout.addView(child);
    }

    @Override
    public void addView(View child, int width, int height) {
        if (getChildCount() < 2)
            super.addView(child, width, height);
        else
            innerLayout.addView(child, width, height);
    }

    @Override
    public void addView(View child, int index) {
        if (getChildCount() < 2)
            super.addView(child, index);
        else
            innerLayout.addView(child, index);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (getChildCount() < 2)
            super.addView(child, index, params);
        else
            innerLayout.addView(child, index, params);

    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        if (getChildCount() < 2)
            super.addView(child, params);
        else
            innerLayout.addView(child, params);
    }

    private View.OnClickListener onClickLayout() {
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                invertVisible();
            }
        };
    }

    private void invertVisible() {
        expanded = !expanded;

        if (expanded)
            scroll.setVisibility(VISIBLE);
        else
            scroll.setVisibility(INVISIBLE);
    }

    public void setTitle(TextView text) {
        removeView(title);
        title = text;
        addView(title, 0);
    }
}