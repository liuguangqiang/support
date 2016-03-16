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
import android.provider.Settings;
import android.text.TextUtils;

/**
 * Created by Eric on 15/4/2.
 */
public class DeviceId {

    private static final String PRE_KEY = "com.liuguangqiang.deviceid";
    private static final String PRE_NAME = "com.liuguangqiang";
    private static final int LENGTH = 16;

    private volatile static DeviceId instance;

    private String deviceId;

    private boolean hasGenerate = false;

    public static DeviceId getInstance(Context context) {
        if (instance == null) {
            synchronized (DeviceId.class) {
                if (instance == null) {
                    instance = new DeviceId(context);
                }
            }
        }
        return instance;
    }

    private DeviceId(Context context) {
        if (!hasGenerate)
            init(context);
    }

    private void init(Context context) {
        deviceId = PreferencesUtils.getString(context, PRE_NAME, PRE_KEY);

        if (TextUtils.isEmpty(deviceId)) {
            generate(context);
        }

        hasGenerate = true;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void generate(Context context) {
        deviceId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        if (deviceId == null || deviceId.equals("9774d56d682e549c") || deviceId.length() < LENGTH) {
            deviceId = RandomUtils.randomAlphabetAndNumber(LENGTH);
        }
    }

}
