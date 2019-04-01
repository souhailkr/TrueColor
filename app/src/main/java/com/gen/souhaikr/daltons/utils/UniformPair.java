package com.gen.souhaikr.daltons.utils;

/**
 * Created by SouhaiKr on 22/03/2019.
 */

public class UniformPair {
    private final String key;
    private final float value;

    public UniformPair(String key, float value) {
        this.key = key;
        this.value = value;
    }

    public float getValue() {
        return this.value;
    }

    public String getKey() {
        return this.key;
    }
}

