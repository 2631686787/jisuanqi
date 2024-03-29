package com.example.calculator;

import java.math.BigDecimal;

public class Arith {

    private static final int DEF_DIV_SCALE = 10;

    public static double add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(Double.parseDouble(v1)));
        BigDecimal b2 = new BigDecimal(Double.toString(Double.parseDouble(v2)));
        return b1.add(b2).doubleValue();
    }

    public static double sub(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(Double.parseDouble(v1)));
        BigDecimal b2 = new BigDecimal(Double.toString(Double.parseDouble(v2)));
        return b1.subtract(b2).doubleValue();
    }

    public static double mul(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(Double.parseDouble(v1)));
        BigDecimal b2 = new BigDecimal(Double.toString(Double.parseDouble(v2)));
        return b1.multiply(b2).doubleValue();
    }

    public static double div(String v1, String v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    public static double div(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(Double.parseDouble(v1)));
        BigDecimal b2 = new BigDecimal(Double.toString(Double.parseDouble(v2)));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

//    public static double round(double v, int scale) {
//        if (scale < 0) {
//            throw new IllegalArgumentException(
//                    "The scale must be a positive integer or zero");
//        }
//        BigDecimal b = new BigDecimal(Double.toString(v));
//        BigDecimal one = new BigDecimal("1");
//        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
//    }
}