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
import android.util.Log;

/**
 * Log helper.
 * <p/>
 * Created by Eric on 15/3/11.
 */
public class Logger {

    /**
     * Global tag for all logs.
     */
    private static String TAG = "Logger";

    private static boolean enable = true;

    /**
     * Whether print logs.
     *
     * @param enable
     */
    public static void setEnable(boolean enable) {
        Logger.enable = enable;
    }

    private Logger() {
    }

    /**
     * Set a global tag for all logs.
     *
     * @param tag
     */
    public static void setTag(@NonNull String tag) {
        TAG = tag;
    }

    public static void i(@NonNull String message, Object... args) {
        log(Log.INFO, message, args);
    }

    public static void v(@NonNull String message, Object... args) {
        log(Log.VERBOSE, message, args);
    }

    public static void e(@NonNull String message, Object... args) {
        log(Log.ERROR, message, args);
    }

    public static void d(@NonNull String message, Object... args) {
        log(Log.DEBUG, message, args);
    }

    public static void w(@NonNull String message, Object... args) {
        log(Log.WARN, message, args);
    }

    private static void log(int priority, String message, Object... args) {
        if (enable) {
            Log.println(priority, TAG, getMessage(message, args));
        }
    }

    private static String getMessage(String message, Object... args) {
        if (message == null || args == null) {
            throw new NullPointerException("message and args must not be null.");
        }

        return args.length > 0 ? format(message, args) : message;
    }

    private static String format(String message, Object... args) {
        if (message.contains("%")) {
            return String.format(message, args);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(message);
            for (Object arg : args) {
                sb.append(arg.toString());
            }
            return sb.toString();
        }
    }

}
