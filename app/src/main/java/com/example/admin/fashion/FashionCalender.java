package com.example.admin.fashion;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class FashionCalender extends AppCompatActivity {

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

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPager.setAdapter(new PagerAdapter() {

            @Override
            public ImageView instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(FashionCalender.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
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


        View.OnClickListener button1ClickListener = new View.OnClickListener() {

            public void onClick(View view) {
                exec_post();
            }
        };
        findViewById(R.id.Buttom).setOnClickListener(button1ClickListener);
    };
    private void exec_post() {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://10.110.130.123/test.php");


        String responseData = null;

        try {

            HttpResponse response = client.execute(post);
            // 取得
            Log.d("name", String.valueOf(response));
        } catch(IOException e) {
            e.printStackTrace();
        }


    }}



