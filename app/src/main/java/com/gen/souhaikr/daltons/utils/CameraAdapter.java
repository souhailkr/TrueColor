package com.gen.souhaikr.daltons.utils;

/**
 * Created by SouhaiKr on 22/03/2019.
 */

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import java.io.IOException;

public class CameraAdapter {
    private Camera mCamera = null;

    public void destroyCamera() {
        if (this.mCamera != null) {
            this.mCamera.stopPreview();
            this.mCamera.release();
            this.mCamera = null;
        }
    }

    public void setupCamera(SurfaceTexture surface) {
        destroyCamera();
        this.mCamera = Camera.open();
        Parameters parameters = this.mCamera.getParameters();
        //parameters.setFocusMode("continuous-picture");
        this.mCamera.setParameters(parameters);
        try {
            this.mCamera.setPreviewTexture(surface);
            this.mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
