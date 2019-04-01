package com.gen.souhaikr.daltons.slide;

/**
 * Created by SouhaiKr on 29/03/2019.
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gen.souhaikr.daltons.R;


public class SliderAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

//    private TextView slideHeading, slideDescription;
//    private ImageView slide_imageView;


    public SliderAdapter(Context context) {

        this.context = context;
    }

    // img Array
    public int[] image_slide ={
            R.drawable.eye,
            R.drawable.time,
            R.drawable.light,
            R.drawable.glasses

    };

    // heading Array
    public String[] heading_slide ={
            "TEST",
            "TIMER",
            "RESULT" ,
            "FILTER"
    };

    // description Array
    public String[] description_slide ={
            "Splash Screen like professional with Animation in Android Studio",
            "Splash Screen with Transition Animation",
            "Simple way to create Splash Scree" ,
            "Simple way to create Filter"

    };




    @Override
    public int getCount() {

        return heading_slide.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container,false);
        container.addView(view);

        ImageView slide_imageView = view.findViewById(R.id.imageView1);
        TextView slideHeading = view.findViewById(R.id.tvHeading);
        TextView  slideDescription = view.findViewById(R.id.tvDescription);

        slide_imageView.setImageResource(image_slide[position]);
        slideHeading.setText(heading_slide[position]);
        slideDescription.setText(description_slide[position]);

        return view;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }

//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        View view = (View) object;
//        container.removeView(view);
//    }

}


