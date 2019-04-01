package com.gen.souhaikr.daltons.utils;

/**
 * Created by SouhaiKr on 22/03/2019.
 */

public class DimensionVault {
    public static final float[] BLUE = new float[]{0.0f, 0.0f, 1.0f};
    public static final float[] GREEN = new float[]{0.0f, 1.0f, 0.0f};
    public static final float[] RED = new float[]{1.0f, 0.0f, 0.0f};
    /* renamed from: b */
    private static final float f0b = 1.0f;
    /* renamed from: d */
    private static final float f1d = 0.0f;
    private static final float d2 = 0.0f;
    public static final short[] drawOrder;
    public static float[] fullTextureVertices = new float[]{0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f};
    /* renamed from: l */
    private static final float f2l = 0.25f;
    private static final float lb = 1.0f;
    private static final float ll = 0.25f;
    private static final float lr = 0.75f;
    private static final float lt = 0.0f;
    /* renamed from: r */
    private static final float f3r = 0.75f;
    private static final float rb = 1.0f;
    private static final float rl = 0.25f;
    private static final float rr = 0.75f;
    private static final float rt = 0.0f;
    /* renamed from: t */
    private static final float f4t = 0.0f;
    public static float[] textureVertices = new float[]{0.25f, 0.0f, 0.75f, 0.0f, 0.75f, 1.0f, 0.25f, 0.0f, 0.75f, 1.0f, 0.25f, 1.0f};
    public static final float[] textureVerticesLeft = new float[]{0.25f, 0.0f, 0.75f, 0.0f, 0.75f, 1.0f, 0.25f, 0.0f, 0.75f, 1.0f, 0.25f, 1.0f};
    public static final float[] textureVerticesRight = new float[]{0.25f, 0.0f, 0.75f, 0.0f, 0.75f, 1.0f, 0.25f, 0.0f, 0.75f, 1.0f, 0.25f, 1.0f};
    public static final float[] triangleVerticesLeft = new float[]{-1.0f, 0.75f, 0.0f, 0.75f, 0.0f, -0.75f, -1.0f, 0.75f, 0.0f, -0.75f, -1.0f, -0.75f};
    public static final float[] triangleVerticesRight = new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f, 0.0f, 1.0f, 1.0f, -1.0f, 0.0f, -1.0f};
    public static float[] wholeTriangleVertices = new float[]{-1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f, -1.0f};

    static {
        short[] sArr = new short[6];
        sArr[1] = (short) 1;
        sArr[2] = (short) 2;
        sArr[3] = (short) 3;
        sArr[4] = (short) 4;
        sArr[5] = (short) 5;
        drawOrder = sArr;
    }
}

