package com.klu.dat.util;

public class MathUtil {

    public static double populationVariance(double[] arr, double mean) {
        double sum = 0;
        for (double v : arr)
            sum += (v - mean) * (v - mean);
        return sum / arr.length;
    }
}