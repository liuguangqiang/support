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

package com.liuguangqiang.support.utils;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;

/**
 * A helper for snackbar.
 * <p/>
 * Created by Eric on 16/6/22.
 */
public class SnackbarHelper {

    private static SnackbarHelper instance = new SnackbarHelper();
    private int bgColor = 0;

    private static class SnackbarHelperLoader {
        private static final SnackbarHelper INSTANCE = new SnackbarHelper();
    }

    public static SnackbarHelper getInstance() {
        return SnackbarHelperLoader.INSTANCE;
    }

    private SnackbarHelper() {

    }

    public void setBackgroundColor(int resId) {
        this.bgColor = resId;
    }

    private Snackbar build(View view, @NonNull String msg) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        if (bgColor != 0) {
            ViewGroup group = (ViewGroup) snackbar.getView();
            group.setBackgroundColor(ContextCompat.getColor(view.getContext(), bgColor));
        }

        return snackbar;
    }

    public void show(View view, @StringRes int resId) {
        show(view, view.getContext().getString(resId));
    }

    public void show(View view, String msg) {
        if (view == null) {
            ToastUtils.show(view.getContext(), msg);
        } else {
            build(view, msg).show();
        }
    }

}
