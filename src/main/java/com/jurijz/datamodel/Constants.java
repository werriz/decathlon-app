package com.jurijz.datamodel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jurijz on 2/28/2017.
 */
public enum Constants {

    RUN_100_M(0, 25.4347, 18, 1.81),
    LONG_JUMP(1, 0.14354, 220, 1.4),
    SHOT_PUT(2, 51.39, 1.5, 1.05),
    HIGH_JUMP(3, 0.8465, 75, 1.42),
    RUN_400_M(4, 1.53775, 82, 1.81),
    RUN_110_M_HURDLES(5, 5.74352, 28.5, 1.92),
    DISCUS_THROW(6, 12.91, 4, 1.1),
    POLE_VAULT(7, 0.2797, 100, 1.35),
    JAVELIN_THROW(8, 10.14, 7, 1.08),
    RUN_1500_M(9, 0.03768, 480, 1.85);

    Constants(int index, double a, double b, double c) {
        this.index = index;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private double a;
    private double b;
    private double c;
    private int index;

    //Create map of all enum values for easy access per index
    private static final Map<Integer, Constants> constantsMap = new HashMap<>();
    static {
        for (Constants constant : Constants.values()) {
            if (constantsMap.put(constant.getIndex(), constant) != null) {
                throw new IllegalArgumentException("duplicate index: " + constant.getIndex());
            }
        }
    }

    public static Constants getByIndex(int index) {
        return constantsMap.get(index);
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    private int getIndex() {
        return index;
    }


}
