package com.gen.souhaikr.daltons;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.support.v4.internal.view.SupportMenu;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.gen.souhaikr.daltons.utils.C0065R;
import com.gen.souhaikr.daltons.utils.CameraAdapter;
import com.gen.souhaikr.daltons.utils.FilterVault;
import com.gen.souhaikr.daltons.utils.MyGLSurfaceView;
import com.gen.souhaikr.daltons.utils.MyRenderer;
import com.gen.souhaikr.daltons.utils.OnSwipeTouchListener;
import com.gen.souhaikr.daltons.utils.UIComponents;

import java.util.HashSet;
import java.util.Set;

public class FilterActivity extends Activity implements SurfaceTexture.OnFrameAvailableListener {
    private static final int BOTH_FILTERS = 2;
    private static final int LEFT_FILTER = 1;
    public static int NO_FILTERS = -1;
    private static final int RIGHT_FILTER = 3;
    private static final float[] defaultUniformValues = new float[]{10.0f, 10.0f};
    private static final int[] filterIndices = new int[2];
    private static final int[] settingsOptions;
    private final Set<Toast> bread = new HashSet();
    //private UIComponents builder;
    private final CameraAdapter cameraAdapter = new CameraAdapter();
    private MyGLSurfaceView glSurfaceView;
    private AlertDialog helpDialog;
    private boolean isCurrentlyStreaming = false;
    private View myView;
    private final String[] names = FilterVault.getAllNames();
    private final int numFilters = FilterVault.getNumFilters();
    private MyRenderer renderer;
    private AlertDialog settDialog;
    private final Point size = new Point();
    private SurfaceTexture surface;
    private ViewGroup vgParent;
    private UIComponents builder;


    static {
        int[] iArr = new int[4];
        iArr[0] = 1;
        settingsOptions = iArr;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fixScreenView();

        setContentView(R.layout.activity_filter);
        getWindowManager().getDefaultDisplay().getSize(this.size);
        this.builder = new UIComponents(this, this.numFilters, this.names);
        //this.builder.initialUISetup(filterIndices);
        defaultUniformValues[0] = (float) (2.0d / ((double) ((float) this.size.x)));
        defaultUniformValues[1] = (float) (1.0d / ((double) ((float) this.size.y)));



        ImageButton visorButton =  findViewById(R.id.btn);
       visorButton.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {

              filterIndices[0] = 0;
              filterIndices[1] = 0;
         setUpCameraViews();
           }
       });
    }

    public void onPause() {
        super.onPause();
        this.cameraAdapter.destroyCamera();
    }

    public void onStop() {
        super.onStop();
        if (this.isCurrentlyStreaming) {
            System.exit(0);
        }
        this.isCurrentlyStreaming = true;
    }

    public void onResume() {
        super.onResume();
        this.cameraAdapter.destroyCamera();
    }

    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        super.onKeyLongPress(keyCode, event);
        return processAndroidButton(keyCode, event);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        return processAndroidButton(keyCode, event);
    }

    @SuppressLint("WrongConstant")
    private void fixScreenView() {
        setRequestedOrientation(0);
        requestWindowFeature(1);
        getWindow().addFlags(128);
        getWindow().setFlags(1024, 1024);
    }

    public void setUpCameraViews() {

        this.isCurrentlyStreaming = true;
        this.glSurfaceView = new MyGLSurfaceView(this);
        FilterVault.updateUniformValues(defaultUniformValues);
        this.renderer = this.glSurfaceView.getRenderer();
        this.renderer.setFilters(filterIndices);
        this.renderer.updateFilterVariables(settingsOptions);
        this.glSurfaceView.setOnTouchListener(new OnSwipeTouchListener(this, this.size.x) {
            public boolean onTouch(View v, MotionEvent event) {
                return this.gestureDetector.onTouchEvent(event);
            }

            public void onLeftScreenSwipe(int i) {
                FilterActivity.filterIndices[0] = FilterActivity.filterIndices[0] + i;
                FilterActivity.this.updateFilters(1);
            }

            public void onRightScreenSwipe(int i) {
                FilterActivity.filterIndices[1] = FilterActivity.filterIndices[1] + i;
                FilterActivity.this.updateFilters(3);
            }

            public void launchUpdateBox() {
                FilterActivity.this.builder.retrieveUpdateFilterDialog(FilterActivity.filterIndices, FilterActivity.this.renderer).show();
            }
        });
        replaceCurrentView(this.glSurfaceView);
    }

    @SuppressLint("ResourceType")
    private void replaceCurrentView(View view) {
        this.myView = getWindow().getDecorView().findViewById(16908290);
        this.vgParent = (ViewGroup) this.myView.getParent();
        this.vgParent.removeView(this.myView);
        this.vgParent.addView(view);
    }

    private boolean processAndroidButton(int keyCode, KeyEvent event) {
        if (settingsOptions[0] == 1 && this.isCurrentlyStreaming) {
            int[] iArr;
            if (keyCode == 25) {
                iArr = filterIndices;
                iArr[0] = iArr[0] + 1;
                filterIndices[1] = filterIndices[0];
                updateFilters(2);
                return true;
            } else if (keyCode == 24) {
                iArr = filterIndices;
                iArr[0] = iArr[0] - 1;
                filterIndices[1] = filterIndices[0];
                updateFilters(2);
                return true;
            } else if (keyCode == 82) {
                //this.builder.retrieveUpdateFilterDialog(filterIndices, this.renderer).show();
                return true;
            } else if (keyCode == 4) {
                endCurrentStreaming();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void startCamera(int texture) {
        this.surface = new SurfaceTexture(texture);
        this.surface.setOnFrameAvailableListener(this);
        this.renderer.setSurface(this.surface);
        this.cameraAdapter.setupCamera(this.surface);
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.glSurfaceView.requestRender();
    }

    public void updateFilters(int whereToDisplayName) {
        FilterVault.updateUniformValues(defaultUniformValues);
        fixFilterGivenIndex(0);
        fixFilterGivenIndex(1);
        this.renderer.updateFilters(filterIndices);
        for (Toast toast : this.bread) {
            if (toast != null) {
                toast.cancel();
            }
        }
        this.bread.clear();
        if (whereToDisplayName > 0) {
            if (whereToDisplayName < 3) {
                this.bread.add(createFilterToast(0, (-this.size.x) / 4));
            }
            if (whereToDisplayName > 1) {
                this.bread.add(createFilterToast(1, this.size.x / 4));
            }
        }
    }

    private void fixFilterGivenIndex(int i) {
        if (filterIndices[i] < 0) {
            int[] iArr = filterIndices;
            iArr[i] = iArr[i] + this.numFilters;
        }
        filterIndices[i] = filterIndices[i] % this.numFilters;
    }

    private Toast createFilterToast(int filterIndex, int offset) {
        Toast toast = Toast.makeText(getApplicationContext(), this.names[filterIndices[filterIndex]], 0);
        toast.setGravity(17, offset, 0);
        toast.show();
        return toast;
    }

    public void endCurrentStreaming() {
        this.cameraAdapter.destroyCamera();
        this.vgParent.removeView(this.glSurfaceView);
        this.isCurrentlyStreaming = false;
        this.vgParent.addView(this.myView);
    }
}

