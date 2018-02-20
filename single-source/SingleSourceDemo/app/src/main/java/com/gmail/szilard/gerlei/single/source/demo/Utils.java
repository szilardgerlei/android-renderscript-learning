package com.gmail.szilard.gerlei.single.source.demo;

import android.util.Log;

import java.util.Random;

/**
 * Created by Gerlei on 2018. 02. 19..
 */

public final class Utils {

    public static final int ONE_THOUSAND = 1000;
    public static final int HUNDRED_THOUSAND = 100000;
    public static final int TEN_MILLION = 10000000;
    public static final int ONE_MILLION = 1000000;

    private Utils() {

    }

    public static float[] generateRandomFloatArray(int min, int max, int length) {
        Random r = new Random();
        float[] array = new float[length];
        for (int i = 0; i < length; i++) {
            array[i] = r.nextInt(max - min) + min;
        }
        return array;
    }

    public static void printArray(Class clazz, String title, float[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append(title).append('\n');
        for (float arrayElement : array) {
            sb.append(String.valueOf(arrayElement)).append('\n');
        }
        Log.i(clazz.getName(), sb.toString());
    }
}
