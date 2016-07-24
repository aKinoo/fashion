package com.example.admin.fashion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
