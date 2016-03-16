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
import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 * IntentUtils
 * <p>
 * Created by Eric on 2014-5-19
 */
public class IntentUtils {

    private IntentUtils() {
    }

    /**
     * Send a Broadcast.
     *
     * @param context
     * @param action
     */
    public static void sendBroadcast(Context context, String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        context.sendBroadcast(intent);
    }

    /**
     * Skip to a App Market.
     * <p>
     * Open a dialog that has some app markets,and you can mark the app.
     *
     * @param context
     */
    public static final void skipToMarket(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("market://details?id="
                + context.getPackageName()));
        context.startActivity(intent);
    }

    /**
     * Send a message
     *
     * @param context
     * @param content
     */
    public static void sendSms(Context context, String content) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"));
        intent.putExtra("sms_body", content);
        context.startActivity(intent);
    }

    /**
     * Send a email.
     *
     * @param context
     * @param to      a array with email account
     * @param content the content of email
     * @param subject the subject of email
     */
    public static void sendEmail(Context context, String[] to, String content,
                                 String subject) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        context.startActivity(Intent.createChooser(intent,
                "Choose Email Client"));
    }

    /**
     * Open Share.
     *
     * @param context
     * @param content
     */
    public static void openShare(Context context, String content, String title) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, title));
    }

    /**
     * Skip to a browser with URL.
     *
     * @param context
     * @param url
     */
    public static void skipToBrowser(Context context, String url) {
        if (url != null) {
            try {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Call someone.
     * <p>
     * Required permisson android.permisson.CALL_PHONE
     *
     * @param context
     * @param mobileNumber
     */
    public static void call(Context context, String mobileNumber) {
        try {
            Uri uri = Uri.parse("tel:" + mobileNumber);
            Intent intent = new Intent(Intent.ACTION_CALL, uri);
            context.startActivity(intent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Open the dialer.
     *
     * @param context
     * @param mobileNumber
     */
    public static void openDialer(Context context, String mobileNumber) {
        Uri uri = Uri.parse("tel:" + mobileNumber);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        context.startActivity(intent);
    }

    /**
     * Start a new Application.
     *
     * @param context
     * @param packageName package name of the Application.
     */
    public static void startApp(Context context, String packageName) {
        try {
            Intent intent = context.getPackageManager()
                    .getLaunchIntentForPackage(packageName);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Install a application.
     *
     * @param context
     * @param file
     */
    public static void installApk(Context context, File file) {
        if (file == null) return;
        if (!file.exists()) return;
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

}
