//コーデを表示して色変える画面にする予定

package com.example.admin.fashion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


public class ShowFashion extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showfashion);  //レイアウトファイル　acitivity_showfashion.xml

        setView();

    }

    @Override
    public void onResume() {
        super.onResume();
        setView();
    }

    public void setView(){
        final ImageView image_top = (ImageView)findViewById(R.id.image_top);
        image_top.setImageResource(tops());
        final ImageView image_bottom = (ImageView)findViewById(R.id.image_bottom);
        image_bottom.setImageResource(bottoms());
        //柄表示
        final ImageView design_top = (ImageView)findViewById(R.id.design_top);
        final ImageView design_bottom = (ImageView)findViewById(R.id.design_bottom);
        ImageButton top_design0 = (ImageButton)findViewById(R.id.top_design0);
        top_design0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                design_top.setVisibility(View.INVISIBLE);
            }
        });
        ImageButton top_design1 = (ImageButton)findViewById(R.id.top_design1);
        top_design1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                design_top.setVisibility(View.VISIBLE);
                design_top.setImageResource(R.drawable.lblack);
            }
        });
        ImageButton top_design2 = (ImageButton)findViewById(R.id.top_design2);
        top_design2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                design_top.setVisibility(View.VISIBLE);
                design_top.setImageResource(R.drawable.pinckflower);
            }
        });
        ImageButton top_design3 = (ImageButton)findViewById(R.id.top_design3);
        top_design3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                design_top.setVisibility(View.VISIBLE);
                design_top.setImageResource(R.drawable.sblack);
            }
        });
        ImageButton top_design4 = (ImageButton)findViewById(R.id.top_design4);
        top_design4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                design_top.setVisibility(View.VISIBLE);
                design_top.setImageResource(R.drawable.swhite);
            }
        });
        //ボトムス柄ボタン
        ImageButton bottom_design0 = (ImageButton)findViewById(R.id.bottom_design0);
        bottom_design0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                design_bottom.setVisibility(View.INVISIBLE);
            }
        });
        ImageButton bottom_design1 = (ImageButton)findViewById(R.id.bottom_design1);
        bottom_design1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                design_bottom.setVisibility(View.VISIBLE);
                design_bottom.setImageResource(R.drawable.lblack);
            }
        });
        ImageButton bottom_design2 = (ImageButton)findViewById(R.id.bottom_design2);
        bottom_design2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                design_bottom.setVisibility(View.VISIBLE);
                design_bottom.setImageResource(R.drawable.pinckflower);
            }
        });
        ImageButton bottom_design3 = (ImageButton)findViewById(R.id.bottom_design3);
        bottom_design3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                design_bottom.setVisibility(View.VISIBLE);
                design_bottom.setImageResource(R.drawable.sblack);
            }
        });
        ImageButton bottom_design4 = (ImageButton)findViewById(R.id.bottom_design4);
        bottom_design4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                design_bottom.setVisibility(View.VISIBLE);
                design_bottom.setImageResource(R.drawable.swhite);
            }
        });
        ImageButton bottom_design5 = (ImageButton)findViewById(R.id.bottom_design5);
        bottom_design5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                design_bottom.setVisibility(View.VISIBLE);
                design_bottom.setImageResource(R.drawable.brown);
            }
        });
        final CanvasView canvas_top = (CanvasView)this.findViewById(R.id.canvas_top);
        final CanvasView canvas_bottom = (CanvasView)this.findViewById(R.id.canvas_bottom);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(ShowFashion.this);

        int[][] color = canvas_top.getColor();
        final int[] top_color = new int[4];
        for(int i = 0; i < 4;i++){
//            Log.d("main",SettingPrefActivity.PREF_COLOR_SETTING[i]);
//            Log.d("main",pref.getString(SettingPrefActivity.PREF_COLOR_SETTING[i],"0"));
            String num = i + "";
            String c = pref.getString(SettingPrefActivity.PREF_COLOR_SETTING[i],num);
//            Log.d("main",c);
            top_color[i] = Integer.parseInt(c);
        }
        final int[] bottom_color = new int[4];
        for(int i = 4;i<8;i++){
            String num = i + "";
            String c = pref.getString(SettingPrefActivity.PREF_COLOR_SETTING[i],num);
//            Log.d("main",c);
            bottom_color[i-4] = Integer.parseInt(c);
        }
        //トップスカラーボタン
        Button top_color1 = (Button) findViewById(R.id.top_color1);
        top_color1.setBackgroundColor(Color.argb(125,color[top_color[0]][0],color[top_color[0]][1],color[top_color[0]][2]));
        top_color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas_top.showCanvas(top_color[0]);
            }
        });
        Button top_color2 = (Button) findViewById(R.id.top_color2);
        top_color2.setBackgroundColor(Color.argb(125,color[top_color[1]][0],color[top_color[1]][1],color[top_color[1]][2]));
        top_color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas_top.showCanvas(top_color[1]);
            }
        });
        Button top_color3 = (Button) findViewById(R.id.top_color3);
        top_color3.setBackgroundColor(Color.argb(125,color[top_color[2]][0],color[top_color[2]][1],color[top_color[2]][2]));
        top_color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas_top.showCanvas(top_color[2]);
            }
        });
        Button top_color4 = (Button) findViewById(R.id.top_color4);
        top_color4.setBackgroundColor(Color.argb(125,color[top_color[3]][0],color[top_color[3]][1],color[top_color[3]][2]));
        top_color4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas_top.showCanvas(top_color[3]);
            }
        });
        //ボトムスカラーボタン
        Button bottom_color1 = (Button)findViewById(R.id.bottom_color1);
        bottom_color1.setBackgroundColor(Color.argb(125,color[bottom_color[0]][0],color[bottom_color[0]][1],color[bottom_color[0]][2]));
        bottom_color1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                canvas_bottom.showCanvas(bottom_color[0]);
            }
        });
        Button bottom_color2 = (Button)findViewById(R.id.bottom_color2);
        bottom_color2.setBackgroundColor(Color.argb(125,color[bottom_color[1]][0],color[bottom_color[1]][1],color[bottom_color[1]][2]));
        bottom_color2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                canvas_bottom.showCanvas(bottom_color[1]);
            }
        });
        Button bottom_color3 = (Button)findViewById(R.id.bottom_color3);
        bottom_color3.setBackgroundColor(Color.argb(125,color[bottom_color[2]][0],color[bottom_color[2]][1],color[bottom_color[2]][2]));
        bottom_color3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                canvas_bottom.showCanvas(bottom_color[2]);
            }
        });
        Button bottom_color4 = (Button)findViewById(R.id.bottom_color4);
        bottom_color4.setBackgroundColor(Color.argb(125,color[bottom_color[3]][0],color[bottom_color[3]][1],color[bottom_color[3]][2]));
        bottom_color4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                canvas_bottom.showCanvas(bottom_color[3]);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                //設定ボタン処理
                Intent intent = new Intent(ShowFashion.this, SettingPrefActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    String plan;
    String telop;
    int MaxCelsius;

    private int bottoms() {
        if (plan == "school") {
            return schoolBottom();
        } else if (plan == "date") {
            return R.drawable.dress;
        } else if (plan == "friends") {
            return friendsBottom();
        } else { //job
            return R.drawable.longpants;
        }
    }

    int friendsBottom() {
        if(telop=="晴れ"||telop=="晴のち曇"||telop=="晴時々曇"||telop=="曇り") {
            return R.drawable.longskirt;
        }else {//rain
            return R.drawable.shortskirt;
        }
    }

    int schoolBottom() {
        if(MaxCelsius<15) {
            if(telop=="晴れ"||telop=="晴のち曇"||telop=="晴時々曇") {
                return R.drawable.longskirt;
            }else if(telop=="曇り") {
                return  R.drawable.longpants;
            } else {//rain
                return R.drawable.harfpants;
            }
        }else if(15<= MaxCelsius && MaxCelsius < 25) {
            if(telop=="晴れ"||telop=="晴のち曇"||telop=="晴時々曇") {
                return  R.drawable.shortskirt;
            }else if(telop=="曇り") {
                return  R.drawable.longskirt;
            } else { //rain
                return  R.drawable.shortskirt;
            }
        }else{
            if(telop=="晴れ"||telop=="晴のち曇"||telop=="晴時々曇") {
                return  R.drawable.shortskirt;
            }else { //clloud,rain
                return  R.drawable.shortskirt;
            }
        }
    }

    private int tops() {
        if(MaxCelsius<15) {
            if(telop=="晴れ"||telop=="晴のち曇"||telop=="晴時々曇") {
                return R.drawable.cardigan;
            } else { //rain, cloud
                return R.drawable.janper;
            }
        }else if(15<= MaxCelsius && MaxCelsius < 25) {
            if(telop=="晴れ"||telop=="晴のち曇"||telop=="晴時々曇"||telop=="曇り") {
                return R.drawable.longshirt;
            } else { //rain
                return R.drawable.cardigan;
            }
        }else{
            if(telop=="晴れ"||telop=="晴のち曇"||telop=="晴時々曇") {
                return R.drawable.tanktop;
            }else if(telop=="曇り") {
                return R.drawable.fshortshirt;
            }else { //rain
                return R.drawable.shortshirt;
            }
        }
    }

}