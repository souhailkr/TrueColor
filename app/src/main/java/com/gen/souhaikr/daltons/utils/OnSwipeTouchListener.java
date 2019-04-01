package com.gen.souhaikr.daltons.utils;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by SouhaiKr on 25/03/2019.
 */

public abstract class OnSwipeTouchListener implements View.OnTouchListener {
    public final GestureDetector gestureDetector;
    private final int midWidth;

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        private GestureListener() {
        }

        public boolean onDown(MotionEvent e) {
            return true;
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                float diffY = e2.getY() - e1.getY();
                if (Math.abs(diffY) <= 100.0f || Math.abs(velocityY) <= 100.0f) {
                    OnSwipeTouchListener.this.launchUpdateBox();
                    return false;
                }
                if (e1.getRawX() < ((float) OnSwipeTouchListener.this.midWidth)) {
                    OnSwipeTouchListener.this.onLeftScreenSwipe(Integer.signum((int) diffY));
                } else {
                    OnSwipeTouchListener.this.onRightScreenSwipe(Integer.signum((int) diffY));
                }
                return true;
            } catch (Exception exception) {
                exception.printStackTrace();
                return false;
            }
        }
    }

    public abstract void launchUpdateBox();

    public abstract void onLeftScreenSwipe(int i);

    public abstract void onRightScreenSwipe(int i);

    public OnSwipeTouchListener(Context ctx, int widthPixels) {
        this.gestureDetector = new GestureDetector(ctx, new GestureListener());
        this.midWidth = widthPixels / 2;
    }
}

