/*
 *  Copyright 2015 Eric Liu
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
 *
 */

package com.liuguangqiang.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Add a divider between all items for RecyclerView.
 * <p/>
 * Created by Eric on 16/3/15.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private int leftMargin = 0;
    private int rightMargin = 0;
    private boolean enableOffset = true;

    protected List<Integer> hideDivider = new ArrayList<>();

    private Drawable divider;

    public DividerItemDecoration(Context context, int dividerResId) {
        this.divider = context.getResources().getDrawable(dividerResId);
    }

    public void setLeftMargin(int leftMargin) {
        this.leftMargin = leftMargin;
    }

    public void setRightMargin(int rightMargin) {
        this.rightMargin = rightMargin;
    }

    public void setEnableOffset(boolean enableOffset) {
        this.enableOffset = enableOffset;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawDivider(c, parent);
    }

    public void drawDivider(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft() + leftMargin;
        final int right = parent.getWidth() - parent.getPaddingRight() - rightMargin;

        final int childCount = parent.getChildCount();
        int itemCount = parent.getAdapter().getItemCount();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(child);

            if (position < itemCount - 1 && displayDivider(position)) {
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + divider.getIntrinsicHeight();
                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
        }
    }

    public void hide(int position) {
        hideDivider.add(position);
    }

    private boolean displayDivider(int position) {
        if (hideDivider.isEmpty()) return true;

        if (hideDivider.contains(position)) return false;

        return true;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (enableOffset)
            outRect.set(0, 0, 0, divider.getIntrinsicHeight());
    }

}