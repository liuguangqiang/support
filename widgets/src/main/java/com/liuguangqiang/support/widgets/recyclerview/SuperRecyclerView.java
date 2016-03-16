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

package com.liuguangqiang.support.widgets.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.liuguangqiang.support.widgets.recyclerview.adapter.AbsRVAdapter;
import com.liuguangqiang.support.widgets.recyclerview.adapter.Bookends;

/**
 * A super recycler view that has implemented paging at bottom, paging at top, adding header and adding footer.
 * <p/>
 * Created by Eric on 16/3/15.
 */
public class SuperRecyclerView extends LinearRecyclerView implements LinearRecyclerView.OnScrollPositionListener {

    private View loadingFooter;

    private boolean hasAttachedFooter = false;
    private boolean pageEnable = true;
    private boolean isLoading = false;
    private Bookends bookends;
    private OnPageListener onPageListener;
    private OnTopPageListener onTopPageListener;

    public SuperRecyclerView(Context context) {
        this(context, null);
    }

    public SuperRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setPageEnable(boolean pageEnable) {
        this.pageEnable = pageEnable;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean b) {
        isLoading = b;
    }

    private void init() {
        setOnScrollPositionListener(this);
    }

    public void setOnPageListener(OnPageListener pageListener) {
        this.onPageListener = pageListener;
    }

    public void setOnTopPageListener(OnTopPageListener onTopPageListener) {
        this.onTopPageListener = onTopPageListener;
    }

    @Override
    public void onScrollToTop() {
        if (pageEnable && onTopPageListener != null && !isLoading) {
            isLoading = true;
            onTopPageListener.onTopPage();
        }
    }

    @Override
    public void onScrollToBottom() {
        if (pageEnable && onPageListener != null && !isLoading) {
            isLoading = true;
            onPageListener.onPage();
        }
    }

    public void notifyDataSetChanged() {
        bookends.notifyDataSetChanged();
    }

    public void notifyItemInserted(int position) {
        bookends.notifyItemInserted(position);
    }

    public void notifyItemRemoved(int position) {
        bookends.notifyItemRemoved(position);
    }

    public void setAdapter(AbsRVAdapter adapter) {
        bookends = new Bookends(adapter);
        super.setAdapter(bookends);
    }

    public void setAdapter(Bookends adapter) {
        super.setAdapter(adapter);
        this.bookends = adapter;
    }

    public Bookends<?> getBookendsAdapter() {
        return bookends;
    }

    /**
     * Add a header view.
     *
     * @param view
     */
    public void addHeader(View view) {
        bookends.addHeader(view);
    }

    /**
     * Add a footer view.
     *
     * @param view
     */
    public void addFooter(View view) {
        bookends.addFooter(view);
    }

    public void setPageFooter(View view) {
        loadingFooter = view;
    }

    public void setPageFooter(int resId) {
        loadingFooter = LayoutInflater.from(getContext()).inflate(resId, null);
    }

    private void attachPageFooter() {
        if (loadingFooter != null && !hasAttachedFooter) {
            hasAttachedFooter = true;
            bookends.addFooter(loadingFooter);
            bookends.setFooterVisibility(loadingFooter, false);
        }
    }

    /**
     * Remove the footer after load finished.
     */
    public void removePageFooter() {
        if (loadingFooter != null && hasAttachedFooter) {
            hasAttachedFooter = false;
            bookends.removeFooter(loadingFooter);
        }
    }

    public void showLoadingFooter() {
        if (!hasAttachedFooter) {
            attachPageFooter();
        }

        if (loadingFooter != null)
            bookends.setFooterVisibility(loadingFooter, true);
    }

}