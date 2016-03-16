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

import android.os.Environment;

import java.io.File;

/**
 * StorageUtils
 * <p>
 * Created by Eric on 2014-5-19.
 */
public class StorageUtils {

    private StorageUtils() {
    }

    private static final String SDCARD_ROOT = Environment
            .getExternalStorageDirectory().getAbsolutePath() + "/";

    /**
     * Create a folder in SDCard.
     *
     * @param path such as : parent_path/folder_name
     */
    public static void createFolder(String path) {
        File file = new File(SDCARD_ROOT, path);
        if (file != null && !file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * Return the root path of SDCard.
     *
     * @return
     */
    public static String getSDCardPath() {
        return SDCARD_ROOT;
    }

    public static boolean sdCardIsAvailable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * Return the size of a file.
     * <p>If the file is a directory,return the sum total of all files in this diretory.</p>
     *
     * @param file
     * @return size, unit:k
     * @throws Exception
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        File[] fileList = file.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isDirectory()) {
                size = size + getFileSize(fileList[i]);
            } else {
                size = size + fileList[i].length();
            }
        }
        return size;
    }

}
