package com.example.admin.fashion;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

//    String[] stackURL = {
//            "http://10.110.130.123/img/IMG_20160804_194152.jpg",
//            "http://10.110.130.123/img/IMG_20160804_194130.jpg",
//            "http://10.110.130.123/img/IMG_20160804_194219.jpg"
//    };

    String[] stackURL = {"http://192.168.0.41:8080/test/testImage.jpg"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fashioncalender);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        //imageを取得
//        Activity view=;
        ImageView image = (ImageView)findViewById(R.id.firstImage);
        //画像取得スレッド起動
//        ImageGetTask task = new ImageGetTask(image);
//        task.execute("http://10.110.130.123/img/hert.jpg");
//        assert viewPager != null;
        viewPager.setAdapter(new PagerAdapter() {

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                // コンテナから View を削除
                container.removeView((View) object);
            }

            @Override
            public int getCount() {
                // リストのアイテム数を返す
             return 3;
            }


            @Override
            public boolean isViewFromObject(View view, Object object) {
                // Object 内に View が存在するか判定する
                return view == (ImageView) object;
            }

            @Override
            public ImageView instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(FashionCalender.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                ImageGetTask task = new ImageGetTask(imageView);
//                task.execute("http://10.110.130.123/img/hert.jpg");
                task.execute(stackURL[0]);
                container.addView(imageView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return imageView;
            }

            });





//        View.OnClickListener button1ClickListener = new View.OnClickListener() {
//
//            public void onClick(View view) {
//                exec_post();
//            }
//        };
//        findViewById(R.id.Buttom).setOnClickListener(button1ClickListener);

        Button takePicture = (Button)findViewById(R.id.takePicture);
        takePicture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplication(),SimpleCameraActivity.class);
                startActivity(intent);
            }
        });
        //スワイプメニューからトップ画面へ遷移
        Button drawer_button_top = (Button)findViewById(R.id.drawer_button_top);
        drawer_button_top.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
                Intent intent = new Intent(getApplication(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
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


    }

//    @Override
//    public void onPause(){
//        super.onPause();
//        finish();
//    }
}



