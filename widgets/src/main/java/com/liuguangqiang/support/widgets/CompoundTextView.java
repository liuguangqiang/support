/*
 *  Copyright 2016 Eric Liu
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.liuguangqiang.support.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * A TextView that add compound drawables easier.
 * <p/>
 * Created by Eric on 16/3/15.
 */
public class CompoundTextView extends TextView {

    public CompoundTextView(Context context) {
        super(context);
    }

    public CompoundTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CompoundTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void drawableLeft(int resId) {
        setCompoundDrawables(resId, 0, 0, 0);
    }

    public void drawableTop(int resId) {
        setCompoundDrawables(0, resId, 0, 0);
    }

    public void drawableRight(int resId) {
        setCompoundDrawables(0, 0, resId, 0);
    }

    public void drawableBottom(int resId) {
        setCompoundDrawables(0, 0, 0, resId);
    }

    public void setCompoundDrawables(int leftResId, int topResId, int rightResId, int bottomResId) {
        Drawable left = getCompoundDrawable(leftResId);
        Drawable top = getCompoundDrawable(topResId);
        Drawable right = getCompoundDrawable(rightResId);
        Drawable bottom = getCompoundDrawable(bottomResId);

        setCompoundDrawables(left, top, right, bottom);
    }

    private Drawable getCompoundDrawable(int resId) {
        if (resId == 0) return null;

        Drawable drawable = getContext().getResources().getDrawable(resId);
        if (drawable != null)
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

        return drawable;
    }

}
