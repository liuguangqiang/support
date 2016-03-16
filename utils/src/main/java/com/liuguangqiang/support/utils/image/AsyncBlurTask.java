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

package com.liuguangqiang.support.utils.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * A asynchronous task for blurring a bitmap.
 */
public class AsyncBlurTask extends AsyncTask<Bitmap, Integer, Bitmap> {

    private OnBlurListener onBlurListener;

    private Context context;

    private int radius = 10;

    public AsyncBlurTask(Context context, OnBlurListener listener) {
        onBlurListener = listener;
        this.context = context;
    }

    public AsyncBlurTask(Context context, OnBlurListener listener, int radius) {
        onBlurListener = listener;
        this.context = context;
        this.radius = radius;
    }

    @Override
    protected Bitmap doInBackground(Bitmap... params) {
        if (params == null) throw new IllegalArgumentException("Can't blur a null bitmap");

        return BlurUtils.blur(context, params[0], radius);
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        if (onBlurListener != null) onBlurListener.onSuccess(result);
    }

    public interface OnBlurListener {
        void onSuccess(Bitmap bm);
    }

}
