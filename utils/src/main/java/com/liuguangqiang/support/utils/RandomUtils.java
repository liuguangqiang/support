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

/**
 * Created by Eric on 15/1/12.
 */
public class RandomUtils {

    private static final String ALPHABET = "abcdefghijklmnopqrskuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String NUMBER = "0123456789";

    private static final String ALPHABET_AND_NUMBER = ALPHABET + NUMBER;

    private RandomUtils() {
    }

    /**
     * Return a random int that less than or equal to max.
     *
     * @param max
     * @return randomInt <= max
     */
    public static int random(int max) {
        return random(0, max);
    }

    /**
     * Return a random int that less than or equal to max and great than or equal to min.
     *
     * @param min
     * @param max
     * @return min <= randomInt <= max
     */
    public static int random(int min, int max) {
        if (min > max || min == max) {
            throw new IllegalArgumentException("the min parameter must less than the max parameter.");
        }
        return (int) (Math.random() * (max - min + 1) + min);
    }

    /**
     * Return a random alphabet string.
     *
     * @param length the length of the random string.
     * @return
     */
    public static String randomAlphabet(int length) {
        StringBuilder sb = new StringBuilder();
        int size = ALPHABET.length() - 1;
        char[] chars = ALPHABET.toCharArray();
        for (int i = 0; i < length; i++) {
            sb.append(chars[random(size)]);
        }
        return sb.toString();
    }

    public static String randomAlphabetAndNumber(int length) {
        StringBuilder sb = new StringBuilder();
        int size = ALPHABET_AND_NUMBER.length() - 1;
        char[] chars = ALPHABET_AND_NUMBER.toCharArray();
        for (int i = 0; i < length; i++) {
            sb.append(chars[random(size)]);
        }
        return sb.toString();
    }

}
