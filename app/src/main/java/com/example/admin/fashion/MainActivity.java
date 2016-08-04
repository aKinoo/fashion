package com.example.admin.fashion;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
        View.OnClickListener button1ClickListener = new View.OnClickListener() {

            public void onClick(View view) {
                AsyncHttpRequest task = new AsyncHttpRequest();
                task.execute();

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
            HttpEntity httpEntity = response.getEntity();

            Log.d("name", String.valueOf(EntityUtils.toString(httpEntity)));
        } catch(IOException e) {
            e.printStackTrace();
        }


    }
    public class AsyncHttpRequest extends AsyncTask<Uri.Builder, Void, String> {

//        private Activity mainActivity;
//
//        public AsyncHttpRequest(Activity activity) {
//
//            // 呼び出し元のアクティビティ
//            this.mainActivity = activity;
//        }

        // このメソッドは必ずオーバーライドする必要があるよ
        // ここが非同期で処理される部分みたいたぶん。

        protected String doInBackground(Uri.Builder... builder) {
            exec_post();
        return "x"; };


        // このメソッドは非同期処理の終わった後に呼び出されます

        protected void onPostExecute(String result) {
            Log.d("name", "finish");
        }
    }
}
