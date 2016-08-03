package com.example.admin.fashion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by admin on 2016/07/19.
 */
public class PastFashion extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastfashion);  //レイアウトファイル　acitivity_pastfashionr.xml

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public ImageView instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(PastFashion.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setImageDrawable(getDrawable(drawables[position]));
                container.addView(imageView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                if (object instanceof ImageView) {
                    ImageView imageView = (ImageView) object;
                    imageView.setImageDrawable(null);
                    container.removeView(imageView);
                }
//                super.destroyItem(container, position, object);
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

        //スワイプメニューからトップ画面へ遷移
        Button drawer_button_top = (Button)findViewById(R.id.drawer_button_top);
        drawer_button_top.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
                Intent intent = new Intent(getApplication(),MainActivity.class);
                startActivity(intent);
            }
        });
        //スワイプメニューからコーデ画面へ遷移
        Button drawer_button1 = (Button)findViewById(R.id.drawer_button1);
        drawer_button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
                Intent intent = new Intent(getApplication(),ShowFashion.class);
                startActivity(intent);
            }
        });
    }
}
