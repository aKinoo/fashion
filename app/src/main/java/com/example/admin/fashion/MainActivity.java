package com.example.admin.fashion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView mTitle;
    TextView mDateLabel0;
    TextView mTelop0;
    NetworkImageView mImage0;
    ImageLoader mImageLoader;
    TextView mMinCelsius0;
    TextView mMaxCelsius0;
    //乗換案内用
    private Spinner nSpinner;
    private String spinnerItems[] = {"1限", "2限", "3限", "4限","5限"};
    private Traintime tt;
    private TextView textView;


    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setView();
    }

    private void setView(){
        //設定から値を取得
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String arrivalStr = pref.getString(SettingPrefActivityMain.PREF_TIME_SETTING,"10");
        int arrival = Integer.parseInt(arrivalStr);     //学校に何分前に到着するか デフォルト値10
        String commuteStr = pref.getString(SettingPrefActivityMain.PREF_TIME_SETTING_TO_SHINJYUKU,"5");
        int commute = Integer.parseInt(commuteStr);     //新宿までの所要時間　デフォルト値5
        Log.d("arrival",arrival+"");
        Log.d("shinhyuku",commute+"");

        mTitle = (TextView) findViewById(R.id.title);
        mDateLabel0 = (TextView) findViewById(R.id.dateLabel0);
        mTelop0 = (TextView) findViewById(R.id.telop0);
        mImage0 = (NetworkImageView) findViewById(R.id.image0);
        mMinCelsius0 = (TextView) findViewById(R.id.minCelsius0);
        mMaxCelsius0 = (TextView) findViewById(R.id.maxCelsius0);

        mImageLoader = MySingleton.getInstance(this).getImageLoader();

        String id = "130010";
        String url = "http://weather.livedoor.com/forecast/webservice/json/v1?city=" + id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mTitle.setText(response.getString("title"));
//                            13mDescription.setText(response.getJSONObject("description").getString("text"));
                            mDateLabel0.setText(response.getJSONArray("forecasts").getJSONObject(0).getString("dateLabel"));
                            mTelop0.setText(response.getJSONArray("forecasts").getJSONObject(0).getString("telop"));
                            String url0 = response.getJSONArray("forecasts").getJSONObject(0).getJSONObject("image").getString("url");
                            mImage0.setImageUrl(url0, mImageLoader);


                            if (!Objects.equals(response.getJSONArray("forecasts").getJSONObject(0).getJSONObject("temperature").getString("min"), "null")) {
//                                Log.d("main",response.getJSONArray("forecasts").getJSONObject(0).toString());
                                mMinCelsius0.setText(response.getJSONArray("forecasts").getJSONObject(0).getJSONObject("temperature").getJSONObject("min").getString("celsius") + "℃");
                            } else {
                                mMinCelsius0.setText("--");
                            }
                            if (!Objects.equals(response.getJSONArray("forecasts").getJSONObject(0).getJSONObject("temperature").getString("max"), "null")) {
//                                Log.d("main",response.getJSONArray("forecasts").getJSONObject(0).toString());
                                mMaxCelsius0.setText(response.getJSONArray("forecasts").getJSONObject(0).getJSONObject("temperature").getJSONObject("max").getString("celsius") + "℃ /");
                            } else {
                                mMaxCelsius0.setText("-- /");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.toString());
                    }
                }
        );
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

        //乗換案内
        tt = new Traintime();
        nSpinner = (Spinner)findViewById(R.id.spinner1);
        final TextView textView = (TextView) findViewById(R.id.text_view);
        // ArrayAdapter
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // spinner に adapter をセット
        nSpinner.setAdapter(adapter);

        // リスナーを登録
        nSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //アイテムが選択された時
            public void onItemSelected(AdapterView<?> parent, View viw, int arg2, long arg3) {
                Spinner spinner = (Spinner) parent;
                String item = (String) spinner.getSelectedItem();

                if (item.equals("1限")) {
                    textView.setText(tt.text(1));
                } else if (item.equals("2限")) {
                    textView.setText(tt.text(2));
                } else if (item.equals("3限")) {
                    textView.setText(tt.text(3));
                }else if (item.equals("4限")) {
                    textView.setText(tt.text(4));
                }else if (item.equals("5限")){
                    textView.setText(tt.text(5));
                }else{
                    textView.setText("出発時刻");
                }
            }

            //アイテムが選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //スワイプメニューからコーデ画面へ遷移
        Button drawer_button1 = (Button)findViewById(R.id.drawer_button1);
        drawer_button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
//                finish();
                Intent intent = new Intent(getApplication(),ShowFashion.class);
                startActivity(intent);
            }
        });
        //スワイプメニューから過去のファッション画面へ遷移
        Button drawer_past_fashion = (Button)findViewById(R.id.drawer_past_fashion);
        drawer_past_fashion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
//                finish();
                Intent intent = new Intent(getApplication(),PastFashion.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        setView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_settings:
                //設定ボタン処理
                Intent intent = new Intent(MainActivity.this, SettingPrefActivityMain.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return  super.onOptionsItemSelected(item);
    }
}
