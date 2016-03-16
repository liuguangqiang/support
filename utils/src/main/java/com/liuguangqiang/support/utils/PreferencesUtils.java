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
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * PreferencesUtils
 * <p>
 * Created by Eric on 2014-5-19.
 */
public class PreferencesUtils {

    private PreferencesUtils() {
    }

    private static SharedPreferences getSharedPreferences(Context context, String name) {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    private static Editor getEditor(Context context, String name) {
        SharedPreferences pre = getSharedPreferences(context, name);
        return pre.edit();
    }

    //Int

    public static void putInt(Context context, String name, String key, int value) {
        if (context == null) return;

        Editor editor = getEditor(context, name);
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getInt(Context context, String name, String key) {
        return getInt(context, name, key, -1);
    }

    public static int getInt(Context context, String name, String key, int defaultValue) {
        if (context == null) return -1;

        SharedPreferences pre = getSharedPreferences(context, name);
        return pre.getInt(key, defaultValue);
    }

    public static void putString(Context context, String name, String key, String value) {
        if (context == null) return;

        Editor editor = getEditor(context, name);
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(Context context, String name, String key) {
        if (context == null) return "";

        SharedPreferences pre = getSharedPreferences(context, name);
        return pre.getString(key, "");
    }

    public static void putBoolean(Context context, String name, String key, boolean value) {
        if (context == null) return;

        Editor editor = getEditor(context, name);
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBoolean(Context context, String name, String key) {
        return getBoolean(context, name, key, false);
    }

    public static boolean getBoolean(Context context, String name, String key, boolean defaultValue) {
        if (context == null) return false;

        return getSharedPreferences(context, name).getBoolean(key, defaultValue);
    }

    public static void putFloat(Context context, String name, String key, float value) {
        if (context == null) return;

        Editor editor = getEditor(context, name);
        editor.putFloat(key, value);
        editor.commit();
    }

    public static float getFloat(Context context, String name, String key) {
        return getFloat(context, name, key, -1);
    }

    public static float getFloat(Context context, String name, String key, float defaultValue) {
        if (context == null) return -1;

        return getSharedPreferences(context, name).getFloat(key, defaultValue);
    }

}
