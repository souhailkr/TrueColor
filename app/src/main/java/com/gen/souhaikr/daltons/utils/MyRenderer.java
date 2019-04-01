package com.gen.souhaikr.daltons.utils;

import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView;

import com.gen.souhaikr.daltons.FilterActivity;

/**
 * Created by SouhaiKr on 22/03/2019.
 */

import android.annotation.SuppressLint;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class MyRenderer implements Renderer {
    CameraStreaming cameraStreamLeft;
    CameraStreaming cameraStreamRight;
    final FilterActivity delegate;
    private Integer leftFilterIndex = Integer.valueOf(0);
    private Integer rightFilterIndex = Integer.valueOf(0);
    private SurfaceTexture surface;
    int texture;
    private int[] updatedVars = new int[5];

    public MyRenderer(FilterActivity _delegate) {
        this.delegate = _delegate;
    }

    public static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

    public static int createTexture() {
        int[] texture = new int[1];
        GLES20.glGenTextures(1, texture, 0);
        GLES20.glBindTexture(36197, texture[0]);
        GLES20.glTexParameterf(36197, 10241, 9729.0f);
        GLES20.glTexParameterf(36197, 10240, 9729.0f);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        return texture[0];
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        this.texture = createTexture();
        this.cameraStreamLeft = new CameraStreaming(this.texture, DimensionVault.triangleVerticesLeft, DimensionVault.textureVerticesLeft);
        this.cameraStreamRight = new CameraStreaming(this.texture, DimensionVault.triangleVerticesRight, DimensionVault.textureVerticesRight);
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        this.cameraStreamLeft.updateFilterSelection(this.leftFilterIndex.intValue());
        this.cameraStreamRight.updateFilterSelection(this.rightFilterIndex.intValue());
        this.delegate.startCamera(this.texture);
    }

    public void onDrawFrame(GL10 unused) {
        float[] mtx = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
        GLES20.glClear(16640);
        this.surface.updateTexImage();
        Matrix.rotateM(mtx, 0, (float) this.updatedVars[1], 1.0f, 0.0f, 0.0f);
        Matrix.rotateM(mtx, 0, (float) this.updatedVars[2], 0.0f, 1.0f, 0.0f);
        Matrix.rotateM(mtx, 0, (float) this.updatedVars[3], 0.0f, 0.0f, 1.0f);
        this.cameraStreamLeft.draw(mtx);
        this.cameraStreamRight.draw(mtx);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    public void setCameraSurface(SurfaceTexture surface) {
        this.surface = surface;
    }

    public void setSurface(SurfaceTexture _surface) {
        this.surface = _surface;
    }

    public void setFilters(int[] filterIndices) {
        this.leftFilterIndex = Integer.valueOf(filterIndices[0]);
        this.rightFilterIndex = Integer.valueOf(filterIndices[1]);
    }

    public void updateFilters(int[] filterIndices) {
        this.leftFilterIndex = Integer.valueOf(filterIndices[0]);
        this.rightFilterIndex = Integer.valueOf(filterIndices[1]);
        this.cameraStreamLeft.updateFilterSelection(filterIndices[0]);
        this.cameraStreamRight.updateFilterSelection(filterIndices[1]);
    }

    public void updateFilterVariables(int[] settingsOptions) {
        this.updatedVars = settingsOptions;
    }
}
