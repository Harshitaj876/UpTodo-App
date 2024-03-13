package com.example.adds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPageAdapter extends PagerAdapter {
    Context context;

    int images[] = {
            R.drawable.slider_image1,
            R.drawable.slider_image2,
            R.drawable.slider_image3
    };

    int headings[] = {
            R.string.heading_one,
            R.string.heading_two,
            R.string.heading_three
    };

    int description[] = {
            R.string.desc_one,
            R.string.desc_two,
            R.string.desc_three
    };

    public ViewPageAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container,false);
        ImageView slidetitileimage = (ImageView) view.findViewById(R.id.titleImage);
        TextView slideHeading = (TextView) view.findViewById(R.id.textHeading);
        TextView slideDescription = (TextView) view.findViewById(R.id.textDescription);

        slidetitileimage.setImageResource(images[position]);
        slideHeading.setText(headings[position]);
        slideDescription.setText(description[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object){
        container.removeView((LinearLayout)object);
    }
}
