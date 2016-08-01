//コーデを表示して色変える画面にする予定

package com.example.admin.fashion;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by admin on 2016/07/18.
 */



public class ShowFashion extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showfashion);  //レイアウトファイル　acitivity_showfashion.xml

        ImageView image_top = (ImageView)findViewById(R.id.image_top);
        image_top.setImageResource(R.drawable.tanktop);
        ImageView image_bottom = (ImageView)findViewById(R.id.image_bottom);
        image_bottom.setImageResource(R.drawable.shortskirt);

        final CanvasView canvas_top = (CanvasView)this.findViewById(R.id.canvas_top);
        final CanvasView canvas_bottom = (CanvasView)this.findViewById(R.id.canvas_bottom);

        int[][] color = canvas_top.getColor();
        //トップスカラーボタン
        Button top_color1 = (Button) findViewById(R.id.top_color1);
        top_color1.setBackgroundColor(Color.argb(125,color[1][0],color[1][1],color[1][2]));
        top_color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas_top.showCanvas(1);
            }
        });
        Button top_color2 = (Button) findViewById(R.id.top_color2);
        top_color2.setBackgroundColor(Color.argb(125,color[2][0],color[2][1],color[2][2]));
        top_color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas_top.showCanvas(2);
            }
        });
        Button top_color3 = (Button) findViewById(R.id.top_color3);
        top_color3.setBackgroundColor(Color.argb(125,color[3][0],color[3][1],color[3][2]));
        top_color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas_top.showCanvas(3);
            }
        });
        Button top_color4 = (Button) findViewById(R.id.top_color4);
        top_color4.setBackgroundColor(Color.argb(125,color[4][0],color[4][1],color[4][2]));
        top_color4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas_top.showCanvas(4);
            }
        });

        //ボトムスカラーボタン
        Button bottom_color1 = (Button)findViewById(R.id.bottom_color1);
        bottom_color1.setBackgroundColor(Color.argb(125,color[1][0],color[1][1],color[1][2]));
        bottom_color1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                canvas_bottom.showCanvas(1);
            }
        });
        Button bottom_color2 = (Button)findViewById(R.id.bottom_color2);
        bottom_color2.setBackgroundColor(Color.argb(125,color[2][0],color[2][1],color[2][2]));
        bottom_color2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                canvas_bottom.showCanvas(2);
            }
        });
        Button bottom_color3 = (Button)findViewById(R.id.bottom_color3);
        bottom_color3.setBackgroundColor(Color.argb(125,color[3][0],color[3][1],color[3][2]));
        bottom_color3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                canvas_bottom.showCanvas(3);
            }
        });
        Button bottom_color4 = (Button)findViewById(R.id.bottom_color4);
        bottom_color4.setBackgroundColor(Color.argb(125,color[4][0],color[4][1],color[4][2]));
        bottom_color4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                canvas_bottom.showCanvas(4);
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
        //スワイプメニューから過去のファッション画面へ遷移
        Button drawer_past_fashion = (Button)findViewById(R.id.drawer_past_fashion);
        drawer_past_fashion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
                Intent intent = new Intent(getApplication(),PastFashion.class);
                startActivity(intent);
            }
        });
    }
}
