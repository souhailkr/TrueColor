package com.gen.souhaikr.daltons.utils;

/**
 * Created by SouhaiKr on 23/03/2019.
 */

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Paint;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.MotionEventCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.TextView;

import com.gen.souhaikr.daltons.FilterActivity;

import java.lang.reflect.Field;

@SuppressLint({"InflateParams"})
public class UIComponents {
    private final FilterActivity context;
    private final String[] names;
    private final int numFilters;
    //private final Animation shake;

    /* renamed from: com.visor.visionhacker.UIComponents$4 */
    class C00694 implements OnClickListener {
        C00694() {
        }

        public void onClick(DialogInterface dialog, int id) {
        }
    }

    /* renamed from: com.visor.visionhacker.UIComponents$6 */
    class C00716 implements OnClickListener {
        C00716() {
        }

        public void onClick(DialogInterface dialog, int id) {
            UIComponents.this.context.endCurrentStreaming();
        }
    }

    public UIComponents(FilterActivity context, int numFilters, String[] names) {
        this.context = context;
        this.numFilters = numFilters;
        this.names = names;
        //this.shake = AnimationUtils.loadAnimation(context, C0065R.anim.shake);
    }

    private static void setNumberPickerTextColor(NumberPicker numberPicker, int color) {
        int count = numberPicker.getChildCount();
        int i = 0;
        while (i < count) {
            View child = numberPicker.getChildAt(i);
            if (child instanceof EditText) {
                try {
                    Field selectorWheelPaintField = numberPicker.getClass().getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint) selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((EditText) child).setTextColor(color);
                    numberPicker.invalidate();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                i++;
            }
        }
    }

    public void initialUISetup(final int[] filterIndices) {
        final NumberPicker numPicker = (NumberPicker) this.context.findViewById(C0065R.id.numberPicker);
        //initializeNumberPickerWithFilters(numPicker);
        setNumberPickerTextColor(numPicker, SupportMenu.CATEGORY_MASK);
        ImageButton visorButton = (ImageButton) this.context.findViewById(C0065R.id.visorIm);
        visorButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                filterIndices[0] = numPicker.getValue();
                filterIndices[1] = numPicker.getValue();
                UIComponents.this.context.setUpCameraViews();
            }
        });
        ValueAnimator colorAnim = ObjectAnimator.ofInt(visorButton, "alpha", new int[]{100, MotionEventCompat.ACTION_MASK});
        colorAnim.setDuration(1000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(-1);
        //colorAnim.setRepeatMode(2);
        colorAnim.start();
        ImageButton helpButton = (ImageButton) this.context.findViewById(C0065R.id.helpIm);
        helpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UIComponents.this.animateClickedButton((ImageButton) UIComponents.this.context.findViewById(C0065R.id.helpIm));
                //helpDialog.show();
            }
        });
        helpButton.setAlpha(1.0f);
        ImageButton settButton = (ImageButton) this.context.findViewById(C0065R.id.settingsIm);
        settButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UIComponents.this.animateClickedButton((ImageButton) UIComponents.this.context.findViewById(C0065R.id.settingsIm));
                //settDialog.show();
            }
        });
        settButton.setAlpha(1.0f);
    }

    private void animateClickedButton(ImageButton button) {
        ObjectAnimator.ofFloat(button, "alpha", new float[]{1.0f, 0.5f, 1.0f}).start();
        //button.startAnimation(this.shake);
    }

    public AlertDialog retrieveHelpDialog() {
        return cancelableDialog(new Builder(this.context).setTitle("Help").setPositiveButton("Ok", new C00694()).setMessage("Reality Hacker Version 0.9\n\nSelect a filter, then click the VisoR button to view your world in a new way with a VR headset.\n\nSwipe on the camera screens or use the volume button to change the filter while viewing.\n\nUse a fisheye lens to increase your field of view.\n\nCompatible with Google Cardboard, Durovis Dive, and other VR Headsets.\n\n© 2014 VisoR"));
    }

    public AlertDialog retrieveUpdateFilterDialog(final int[] filterIndices, MyRenderer renderer) {
        final NumberPicker picker = new NumberPicker(this.context);
        initializeNumberPickerWithFilters(picker);
        return cancelableDialog(new Builder(this.context).setTitle("Settings").setPositiveButton("Set Filter", new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                filterIndices[0] = picker.getValue();
                filterIndices[1] = picker.getValue();
                UIComponents.this.context.updateFilters(FilterActivity.NO_FILTERS);
            }
        }).setNegativeButton("Main Menu", new C00716()).setView(picker));
    }

    private AlertDialog cancelableDialog(Builder builder) {
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    public AlertDialog retrieveSettingsDialog(final int[] settingsOptions) {
        boolean z = false;
        View settingsView = this.context.getLayoutInflater().inflate(C0065R.layout.dialog_settings, null);
        final Switch volumeSwitch = (Switch) settingsView.findViewById(C0065R.id.switch_volume);
        if (settingsOptions[0] == 1) {
            z = true;
        }
        volumeSwitch.setChecked(z);
        textViewSeekBarLinker(settingsView, C0065R.id.textViewXnum, C0065R.id.seekBarX, C0065R.id.buttonXminus, C0065R.id.buttonXplus, settingsOptions, 1, 24, 15);
        textViewSeekBarLinker(settingsView, C0065R.id.textViewYnum, C0065R.id.seekBarY, C0065R.id.buttonYminus, C0065R.id.buttonYplus, settingsOptions, 2, 24, 15);
        textViewSeekBarLinker(settingsView, C0065R.id.textViewZnum, C0065R.id.seekBarZ, C0065R.id.buttonZminus, C0065R.id.buttonZplus, settingsOptions, 3, 24, 15);
        return cancelableDialog(new Builder(this.context).setTitle("Settings").setPositiveButton("Ok", new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (volumeSwitch.isChecked()) {
                    settingsOptions[0] = 1;
                } else {
                    settingsOptions[0] = 0;
                }
            }
        }).setView(settingsView));
    }

    private void textViewSeekBarLinker(View settingsView, int textViewNum, int seekBarNum, int minus, int plus, int[] settingsOptions, int offset, int maxNum, int increment) {
        final TextView textView = (TextView) settingsView.findViewById(textViewNum);
        textView.setText(settingsOptions[offset]);
        final SeekBar seekBar = (SeekBar) settingsView.findViewById(seekBarNum);
        seekBar.setMax(maxNum);
        seekBar.setProgress(settingsOptions[offset]);
        final int[] iArr = settingsOptions;
        final int i = offset;
        final int i2 = increment;
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar arg0) {
                UIComponents.this.setViewValsTextArray(textView, iArr, i, arg0.getProgress() * i2);
            }

            public void onStartTrackingTouch(SeekBar arg0) {
                UIComponents.this.setViewValsTextArray(textView, iArr, i, arg0.getProgress() * i2);
            }

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                UIComponents.this.setViewValsTextArray(textView, iArr, i, arg0.getProgress() * i2);
            }
        });
        final int[] iArr2 = settingsOptions;
        final int i3 = offset;
        final int i4 = increment;
        final TextView textView2 = textView;
        ((Button) settingsView.findViewById(minus)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int[] iArr = iArr2;
                int i = i3;
                iArr[i] = iArr[i] - i4;
                if (iArr2[i3] <= 0) {
                    iArr2[i3] = 0;
                }
                UIComponents.this.setViewValsSeekText(textView2, seekBar, iArr2[i3], i4);
            }
        });
        final int[] iArr3 = settingsOptions;
        final int i5 = offset;
        final int i6 = increment;
        final int i7 = maxNum;
        final TextView textView3 = textView;
        final SeekBar seekBar2 = seekBar;
        ((Button) settingsView.findViewById(plus)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int[] iArr = iArr3;
                int i = i5;
                iArr[i] = iArr[i] + i6;
                if (iArr3[i5] >= i7 * i6) {
                    iArr3[i5] = i7 * i6;
                }
                UIComponents.this.setViewValsSeekText(textView3, seekBar2, iArr3[i5], i6);
            }
        });
    }

    protected void setViewValsSeekText(TextView textView, SeekBar seekBar, int value, int increment) {
        textView.setText(value);
        seekBar.setProgress(value / increment);
    }

    protected void setViewValsTextArray(TextView textView, int[] settingsOptions, int offset, int value) {
        textView.setText(value);
        settingsOptions[offset] = value;
    }


    private void initializeNumberPickerWithFilters(NumberPicker numberPicker) {
        numberPicker.setMinValue(3);
        numberPicker.setMaxValue(this.numFilters - 1);
        numberPicker.setDisplayedValues(this.names);
        //numberPicker.setDescendantFocusability(393216);
    }
}
