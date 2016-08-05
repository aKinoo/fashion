package com.example.admin.fashion;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;

//import com.google.api.client.http.HttpResponse;


import com.android.internal.http.multipart.MultipartEntity;
import com.android.volley.Request;
import com.google.common.net.MediaType;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by admin on 2016/08/05.
 */
public class PostMessageTask extends AsyncTask<String,Integer,Integer>{
    @Override
    protected Integer doInBackground(String... contents){
        String url = "http://192.168.0.41:8080/test";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();


        HttpResponse res = null;
        try{
//            post.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
            res = httpClient.execute(post);

        }catch(IOException e){
            e.printStackTrace();
        }
        return Integer.valueOf(res.getStatusLine().getStatusCode());
    }

//    //POSTするファイルのパスを引数として貰っている
//    protected String doInBackground(String... ImagePath) {
//        //ポスト先のURL
//        String url = "http://192.168.0.41:8080/test/service.php";
//
//        File file = new File(ImagePath[0]);
//
//        //ここでPOSTする内容を設定　"image/jpg"の部分は送りたいファイルの形式に合わせて変更する
//        RequestBody requestBody = new MultipartBuilder()
//                .type(MultipartBuilder.FORM)
//                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/jpg"), file))
//                .build();
//
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url(url)
//                .post(requestBody)
//                .build();
//
//        String result="";
//        try {
//            Response response = client.newCall(request).execute();
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//            {
//                result = response.body().string();
//            }
//        } catch (Exception e) {}
//
//        return result;
//    }

}
