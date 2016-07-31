package com.example.admin.fashion;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class MainActivity extends AppCompatActivity {

    int[] drawables = {
            R.drawable.bigfig,
            R.drawable.feminine,
            R.drawable.casual,
            R.drawable.onepiece,
            R.drawable.pants,
            R.drawable.rb04471,
            R.drawable.skirt
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        viewPager.setAdapter(new PagerAdapter() {

            @Override
            public ImageView instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setScaleType(ScaleType.FIT_CENTER);
                imageView.setImageDrawable(getDrawable(drawables[position]));
                container.addView(imageView, new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                if (object instanceof ImageView) {
                    ImageView imageView = (ImageView) object;
                    imageView.setImageDrawable(null);
                    container.removeView(imageView);
                }
                super.destroyItem(container, position, object);
            }

            @Override
            public int getCount() {
                return drawables.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
    }
}

