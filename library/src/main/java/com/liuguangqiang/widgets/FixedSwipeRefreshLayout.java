/*
 *  Copyright 2015 GoIn Inc. All rights reserved.
 */

package com.liuguangqiang.widgets;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Fixed a bug that the animation will be blocked when refreshing too frequent.
 * <p/>
 * Created by Eric on 15/10/14.
 */
public class FixedSwipeRefreshLayout extends SwipeRefreshLayout {

    public FixedSwipeRefreshLayout(Context context) {
        super(context);
    }

    public FixedSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return !isRefreshing() && super.onStartNestedScroll(child, target, nestedScrollAxes);
    }

}
