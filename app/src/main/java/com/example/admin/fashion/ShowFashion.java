//コーデを表示して色変える画面にする予定

package com.example.admin.fashion;

import android.content.Intent;
import android.graphics.Canvas;
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
        image_bottom.setImageResource(R.drawable.skirt);

        final CanvasView canvas_top = (CanvasView)this.findViewById(R.id.canvas_top);
        CanvasView canvas_bottom = (CanvasView)this.findViewById(R.id.canvas_bottom);

        Button button = (Button) findViewById(R.id.top_color1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas_top.flag = true;
                canvas_top.showCanvas();
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
