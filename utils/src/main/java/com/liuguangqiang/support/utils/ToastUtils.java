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

import android.content.Context;
import android.widget.Toast;

/**
 * ToastUtils
 * <p>
 * Created by Eric on 2014-5-19
 */
public class ToastUtils {

    private ToastUtils() {
    }

    /**
     * Show a toast.
     *
     * @param context
     * @param resId
     */
    public static void show(Context context, int resId) {
        show(context, context.getApplicationContext().getString(resId));
    }

    /**
     * Show a toast.
     *
     * @param context
     * @param content
     */
    public static void show(Context context, String content) {
        if (context == null || content == null) {
            return;
        }
        Toast.makeText(context.getApplicationContext(), content, Toast.LENGTH_LONG).show();
    }

    private static final int SHOW_OFFSET = 2000;
    private static long lastShowTime = 0;

    /**
     * Show a toast and avoid to display too frequent.
     *
     * @param context
     * @param resId   the content will be displayed.
     * @param limit   whether limit the display moment.
     */
    public static void show(Context context, int resId, boolean limit) {
        show(context.getApplicationContext(), context.getApplicationContext().getString(resId), limit);
    }

    /**
     * Show a toast and avoid to display too frequent.
     *
     * @param context
     * @param content the content will be displayed.
     * @param limit   whether limit the display moment.
     */
    public static void show(Context context, String content, boolean limit) {
        long now = System.currentTimeMillis();
        long offset = now - lastShowTime;
        if (limit && offset > SHOW_OFFSET) {
            lastShowTime = now;
            show(context, content);
        } else if (!limit) {
            show(context, content);
        }
    }

}
