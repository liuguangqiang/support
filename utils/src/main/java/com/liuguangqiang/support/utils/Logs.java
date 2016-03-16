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

import android.util.Log;

/**
 * Created by Eric on 15/3/11.
 */
public class Logs {

    private static String TAG = "AFramework";

    private static boolean isDebug = true;

    private Logs() {
    }

    public static void setTAG(String tag) {
        TAG = tag;
    }

    public static void setIsDebug(boolean debug) {
        isDebug = debug;
    }

    public static void i(Object obj) {
        Logs.i(TAG, obj.toString());
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

}
