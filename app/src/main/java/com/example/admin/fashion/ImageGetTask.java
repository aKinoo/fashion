package com.example.admin.fashion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

class ImageGetTask extends AsyncTask<String,Void,Bitmap> {
    private ImageView image;

    public ImageGetTask(ImageView _image) {
        image = _image;
    }
    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap image;
        try {
            URL imageUrl = new URL(params[0]);
            InputStream imageIs;
            imageIs = imageUrl.openStream();
            image = BitmapFactory.decodeStream(imageIs);
            Bitmap originalBitmap = image;
            //元画像
            Bitmap rotateBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
            Matrix matrix = new Matrix();
            matrix.postRotate(90.0f);
            //Bitmap回転させる
            Bitmap flippedBmp = Bitmap.createBitmap(rotateBitmap, 0, 0, rotateBitmap.getWidth(), rotateBitmap.getHeight(), matrix, false);
            return flippedBmp;
        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }
    @Override
    protected void onPostExecute(Bitmap result) {
        // 取得した画像をImageViewに設定します。
        image.setImageBitmap(result);
    }
}
