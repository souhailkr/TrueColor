package com.gen.souhaikr.daltons.utils;

/**
 * Created by SouhaiKr on 22/03/2019.
 */

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class CameraStreaming {
    static final int COORDS_PER_VERTEX = 2;
    private Filter activeFilter;
    private final ArrayList<Filter> allFilters;
    private int counter;
    private final int counterMod;
    private final ShortBuffer drawListBuffer;
    private final short[] drawOrder;
    private final Random generator;
    private final float[] ijkRand;
    private int mColorHandle;
    private int mPositionHandle;
    private final int mProgram1;
    private final int mProgram2;
    private int mTextureCoordHandle;
    private final int texture;
    private final FloatBuffer textureVerticesBuffer;
    private ArrayList<UniformPair> uniformPairs;
    private final FloatBuffer vertexBuffer;
    private final String vertexShaderCode = com.gen.souhaikr.daltons.models.FilterVault.vertexMatrixShaderCode;
    private final int vertexStride = 8;

    public CameraStreaming(int texture, float[] triangleVertices, float[] textureVertices) {
        int fragmentShader;
        short[] sArr = new short[6];
        sArr[1] = (short) 1;
        sArr[2] = (short) 2;
        sArr[3] = (short) 3;
        sArr[4] = (short) 4;
        sArr[5] = (short) 5;
        this.drawOrder = sArr;
        this.generator = new Random();
        this.ijkRand = new float[]{0.0f, 0.0f, 0.0f};
        this.counterMod = 10;
        this.counter = 0;
        this.texture = texture;
        ByteBuffer bb = ByteBuffer.allocateDirect(triangleVertices.length * 4);
        bb.order(ByteOrder.nativeOrder());
        this.vertexBuffer = bb.asFloatBuffer();
        this.vertexBuffer.put(triangleVertices);
        this.vertexBuffer.position(0);
        ByteBuffer dlb = ByteBuffer.allocateDirect(this.drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        this.drawListBuffer = dlb.asShortBuffer();
        this.drawListBuffer.put(this.drawOrder);
        this.drawListBuffer.position(0);
        ByteBuffer bb2 = ByteBuffer.allocateDirect(textureVertices.length * 4);
        bb2.order(ByteOrder.nativeOrder());
        this.textureVerticesBuffer = bb2.asFloatBuffer();
        this.textureVerticesBuffer.put(textureVertices);
        this.textureVerticesBuffer.position(0);
        int vertexShader = MyRenderer.loadShader(35633, com.gen.souhaikr.daltons.models.FilterVault.vertexMatrixShaderCode);
        this.allFilters = FilterVault.getAllFilters();
        Iterator it = this.allFilters.iterator();
        while (it.hasNext()) {
            Filter filter = (Filter) it.next();
            fragmentShader = MyRenderer.loadShader(35632, filter.getFilterShader());
            int newMProgram = GLES20.glCreateProgram();
            GLES20.glAttachShader(newMProgram, vertexShader);
            GLES20.glAttachShader(newMProgram, fragmentShader);
            GLES20.glLinkProgram(newMProgram);
            filter.setProgram(newMProgram);
            GLES20.glEnable(2929);
        }
        fragmentShader = MyRenderer.loadShader(35632, FilterVault.inverted);
        this.mProgram1 = GLES20.glCreateProgram();
        GLES20.glAttachShader(this.mProgram1, vertexShader);
        GLES20.glAttachShader(this.mProgram1, fragmentShader);
        GLES20.glLinkProgram(this.mProgram1);
        GLES20.glEnable(2929);
        fragmentShader = MyRenderer.loadShader(35632, FilterVault.fs_GrayCCIR);
        this.mProgram2 = GLES20.glCreateProgram();
        GLES20.glAttachShader(this.mProgram2, vertexShader);
        GLES20.glAttachShader(this.mProgram2, fragmentShader);
        GLES20.glLinkProgram(this.mProgram2);
        GLES20.glEnable(2929);
        this.uniformPairs = FilterVault.getAllUniformPairs();
    }

    public void updateFilterSelection(int index) {
        this.activeFilter = (Filter) this.allFilters.get(index);
        updateVariables();
    }

    public void updateVariables() {
        this.uniformPairs = FilterVault.getAllUniformPairs();
    }

    public void draw(float[] mtx) {
        int mProgram = this.activeFilter.getProgram();
        GLES20.glUseProgram(mProgram);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, this.texture);
        Iterator it = this.uniformPairs.iterator();
        while (it.hasNext()) {
            UniformPair pair = (UniformPair) it.next();
            GLES20.glUniform1f(GLES20.glGetUniformLocation(mProgram, pair.getKey()), pair.getValue());
        }
        GLES20.glUniform1f(GLES20.glGetUniformLocation(mProgram, "time"), this.generator.nextFloat());
        this.counter++;
        if (this.counter % 10 == 0) {
            this.ijkRand[0] = this.generator.nextFloat();
            this.ijkRand[1] = this.generator.nextFloat();
            this.ijkRand[2] = this.generator.nextFloat();
        }
        GLES20.glUniform1f(GLES20.glGetUniformLocation(mProgram, "irand"), this.ijkRand[0]);
        GLES20.glUniform1f(GLES20.glGetUniformLocation(mProgram, "jrand"), this.ijkRand[1]);
        GLES20.glUniform1f(GLES20.glGetUniformLocation(mProgram, "krand"), this.ijkRand[2]);
        GLES20.glUniformMatrix4fv(GLES20.glGetUniformLocation(mProgram, "uMVPMatrix"), 1, false, mtx, 0);
        this.mPositionHandle = GLES20.glGetAttribLocation(mProgram, "position");
        GLES20.glEnableVertexAttribArray(this.mPositionHandle);
        GLES20.glVertexAttribPointer(this.mPositionHandle, 2, 5126, false, 8, this.vertexBuffer);
        this.mTextureCoordHandle = GLES20.glGetAttribLocation(mProgram, "inputTextureCoordinate");
        GLES20.glEnableVertexAttribArray(this.mTextureCoordHandle);
        GLES20.glVertexAttribPointer(this.mTextureCoordHandle, 2, 5126, false, 8, this.textureVerticesBuffer);
        this.mColorHandle = GLES20.glGetAttribLocation(mProgram, "s_texture");
        GLES20.glDrawElements(4, this.drawOrder.length, 5123, this.drawListBuffer);
        GLES20.glDisableVertexAttribArray(this.mPositionHandle);
        GLES20.glDisableVertexAttribArray(this.mTextureCoordHandle);
    }
}
