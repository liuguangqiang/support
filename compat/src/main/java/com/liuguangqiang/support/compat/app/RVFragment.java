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

package com.liuguangqiang.support.compat.app;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liuguangqiang.support.compat.R;
import com.liuguangqiang.support.compat.internal.HandlerHelper;
import com.liuguangqiang.support.compat.listener.RequestListener;
import com.liuguangqiang.support.widgets.FixedSwipeRefreshLayout;
import com.liuguangqiang.support.widgets.recyclerview.OnPageListener;
import com.liuguangqiang.support.widgets.recyclerview.SuperRecyclerView;
import com.liuguangqiang.support.widgets.recyclerview.adapter.AbsRVAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A recycler view fragment that implemented paging and pulling to refresh.
 * <p/>
 * Created by Eric on 15/9/19.
 */
public abstract class RvFragment<T> extends AbsFragment implements RequestListener<T>, SwipeRefreshLayout.OnRefreshListener, OnPageListener {

    private SuperRecyclerView recyclerView;
    private FixedSwipeRefreshLayout swipeLayout;
    private RelativeLayout layoutContainer;
    private TextView tvEmpty;

    private AbsRVAdapter adapter;
    public List<T> data = new ArrayList<>();

    private int pageIndex = 1;
    private int pageLimit = 20;

    private boolean isLoading = false;
    private boolean refreshable = false;
    private boolean pageable = false;
    private boolean autoClear = false;
    private boolean autoRefresh = false;
    private String emptyText;
    private int loadingFooter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onBuild();
        initViews();
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_recycler_vew_refreshable;
    }

    private void onBuild() {
        Builder builder = createBuilder(new Builder());
        if (builder != null) {
            refreshable = builder.refreshable;
            pageable = builder.pageable;
            autoClear = builder.autoClear;
            autoRefresh = builder.autoRefresh;
            pageLimit = builder.pageLimit;
            emptyText = builder.emptyText;
            loadingFooter = builder.loadingFooter;
        }
    }

    public abstract AbsRVAdapter createAdapter();

    public abstract Builder createBuilder(Builder builder);

    public AbsRVAdapter getAdapter() {
        return adapter;
    }

    private void initViews() {
        recyclerView = findById(R.id.recycler_view);
        swipeLayout = findById(R.id.swipe_layout);
        layoutContainer = findById(R.id.layout_container);
        tvEmpty = findById(R.id.tv_empty);

        adapter = createAdapter();
        if (recyclerView != null) {
            recyclerView.setPageFooter(loadingFooter);
            recyclerView.setOnPageListener(this);
            recyclerView.setAdapter(adapter);
        }

        setRefreshable(refreshable);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (isAdded() && autoRefresh && pageIndex == 1) {
                    swipeLayout.getViewTreeObserver().removeOnPreDrawListener(this);
                    swipeLayout.setRefreshing(true);
                }
                return true;
            }
        });

        if (enableEmpty()) {
            tvEmpty.setText(emptyText);
        }
    }

    private boolean enableEmpty() {
        return tvEmpty != null && !TextUtils.isEmpty(emptyText);
    }

    public void setColorSchemeResources(@ColorRes int... colorResIds) {
        swipeLayout.setColorSchemeResources(colorResIds);
    }

    public void setRefreshable(boolean refreshable) {
        this.refreshable = refreshable;
        if (swipeLayout != null) {
            swipeLayout.setEnabled(refreshable);
        }
    }

    public int getPageIndex() {
        return pageIndex;
    }

    @Override
    public void onRefresh() {
        if (!isLoading) {
            recyclerView.setPageEnable(true);
            pageIndex = 1;
            requestData();
        }
    }

    @Override
    public void onPage() {
        if (pageable && !isLoading) {
            pageIndex++;
            requestData();
        }
    }

    public void requestData() {
        isLoading = true;
        recyclerView.setIsLoading(true);
    }

    @Override
    public void onRequestSuccess(List<? extends T> list) {
        if (!isAdded()) return;

        if (pageable) {
            if (list.size() < pageLimit) {
                recyclerView.setPageEnable(false);
                recyclerView.removePageFooter();
            } else {
                recyclerView.setPageEnable(true);
                recyclerView.showLoadingFooter();
            }
        }

        if (pageIndex == 1 && autoClear) {
            data.clear();
        }
        data.addAll(list);
        recyclerView.notifyDataSetChanged();
        onRequestFinish();
    }

    @Override
    public void onRequestFailure() {
        recyclerView.removePageFooter();
        onRequestFinish();
    }

    @Override
    public void onRequestFinish() {
        HandlerHelper.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isAdded()) {
                    isLoading = false;
                    recyclerView.setIsLoading(false);
                    if (swipeLayout != null) {
                        swipeLayout.setRefreshing(false);
                    }
                }
            }
        }, 500);

        setEmptyVisible(data.isEmpty());
    }

    public void setEmptyVisible(boolean visible) {
        if (enableEmpty()) {
            if (visible) {
                tvEmpty.setVisibility(View.VISIBLE);
                data.clear();
                recyclerView.notifyDataSetChanged();
            } else {
                tvEmpty.setVisibility(View.GONE);
            }
        }
    }

    public static final class Builder {

        private boolean refreshable = true;
        private boolean pageable = true;
        private boolean autoClear = true;
        private boolean autoRefresh = true;
        private int pageLimit = 20;
        private String emptyText;
        private int loadingFooter = R.layout.layout_loading_footer;

        public Builder refreshable(boolean refreshable) {
            this.refreshable = refreshable;
            return this;
        }

        public Builder pageable(boolean pageable) {
            this.pageable = pageable;
            return this;
        }

        public Builder autoRefresh(boolean autoRefresh) {
            this.autoRefresh = autoRefresh;
            return this;
        }

        public Builder autoClear(boolean autoClear) {
            this.autoClear = autoClear;
            return this;
        }

        public Builder pageLimit(int pageLimit) {
            this.pageLimit = pageLimit;
            return this;
        }

        public Builder emptyText(String emptyText) {
            this.emptyText = emptyText;
            return this;
        }

        public Builder loadingFooter(int loadingFooter) {
            this.loadingFooter = loadingFooter;
            return this;
        }

        public Builder build() {
            return this;
        }
    }

}
