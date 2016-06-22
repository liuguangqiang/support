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

import android.test.AndroidTestCase;

/**
 * Created by Eric on 16/3/31.
 */
public class LoggerTest extends AndroidTestCase {

    public void testLog() {
        Logger.setTag("LoggerTest");

        Logger.i("test");

        Logger.i("test log.i");
        Logger.e("test log.e");
        Logger.d("test log.d");
        Logger.v("test log.v");
        Logger.w("test log.w");
    }

    public void testLogWithArgs() {
        Logger.setTag("LoggerTest");

        Logger.i("test log.i with args: %s", "hello logger");
        Logger.e("test log.e with args: ", "hello logger");
    }


}
