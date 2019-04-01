package com.gen.souhaikr.daltons.utils;

/**
 * Created by SouhaiKr on 22/03/2019.
 */

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.gen.souhaikr.daltons.FilterActivity;

public class MyGLSurfaceView extends GLSurfaceView {
    final MyRenderer renderer;

    public MyGLSurfaceView(Context context) {
        super(context);
        setEGLContextClientVersion(2);
        this.renderer = new MyRenderer((FilterActivity) context);
        setRenderer(this.renderer);
        setRenderMode(0);
    }

    public MyRenderer getRenderer() {
        return this.renderer;
    }

    public boolean performClick() {
        System.out.println("Hardware " + isHardwareAccelerated());
        return super.performClick();
    }
}