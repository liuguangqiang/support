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

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;

/**
 * Created by Eric on 15/4/1.
 */
public class MetaDataUtils {

    private MetaDataUtils() {
    }

    public static String getMetaData(Context context, String metaDataName) {
        String result = "";
        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            result = appInfo.metaData.getString(metaDataName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static String getMetaData(Activity context, String metaDataName) {
        String result = "";
        try {
            ActivityInfo activityInfo = context.getPackageManager().getActivityInfo(context.getComponentName(),
                    PackageManager.GET_META_DATA);
            result = activityInfo.metaData.getString(metaDataName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static String getMetaData(Service context, String metaDataName) {
        String result = "";
        try {
            ComponentName componentName = new ComponentName(context, context.getClass());
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(componentName,
                    PackageManager.GET_META_DATA);
            result = serviceInfo.metaData.getString(metaDataName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
