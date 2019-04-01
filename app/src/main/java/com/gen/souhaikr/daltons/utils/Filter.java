package com.gen.souhaikr.daltons.utils;

import java.util.ArrayList;

/**
 * Created by SouhaiKr on 22/03/2019.
 */

public class Filter {
    private final String filterShader;
    private final String id;
    private int myProgram;
    private final ArrayList<UniformPair> myUnifromPairs = new ArrayList();

    public Filter(String id, String filterShader) {
        this.id = id;
        this.filterShader = filterShader;
    }

    public String getFilterShader() {
        return this.filterShader;
    }

    public String toString() {
        return this.id;
    }

    public int getProgram() {
        return this.myProgram;
    }

    public void setProgram(int myProgram) {
        this.myProgram = myProgram;
    }

    public void addUniformPairs(ArrayList<UniformPair> uniformPair) {
        this.myUnifromPairs.addAll(uniformPair);
    }

    public ArrayList<UniformPair> getUniformPairs() {
        return this.myUnifromPairs;
    }
}

