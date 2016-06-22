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

package com.liuguangqiang.support.compat.internal;

import android.os.Handler;

/**
 * Created by Eric on 15/10/11.
 */
public class HandlerHelper {

    private static final Handler handler = new Handler();

    private HandlerHelper() {
    }

    public static void postDelayed(final Runnable runnable, final int delay) {
        handler.postDelayed(runnable, delay);
    }

    public static void removeCallbacks(Runnable runnable) {
        handler.removeCallbacks(runnable);
    }

}
