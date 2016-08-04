package com.example.admin.fashion;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by admin on 2016/08/04.
 */
public class SimpleCameraActivity extends AppCompatActivity{
    //カメラインスタンス
    private Camera mCam = null;
    private boolean misTake = false;

    //カメラプレビュークラス
    private  CameraPreview mCamPreview = null;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        //カメラインスタンスの取得
        try{
            mCam = Camera.open();
        }catch(Exception e){
            //エラー
            this.finish();
        }

        //FrameLayoutにCameraPreviewクラスを設定
        FrameLayout preview = (FrameLayout)findViewById(R.id.cameraPreview);
        mCamPreview = new CameraPreview(this,mCam);
        preview.addView(mCamPreview);

        //mCamPreviewにタッチイベントを設定
        mCamPreview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    if(!misTake){
                        //撮影中の2度押し禁止フラグ
                        misTake = true;
                        //画像取得
                        mCam.takePicture(null,null,mPicJpgListener);
                    }
                }
                return true;
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        //カメラ破棄インスタンスを解放
        if(mCam != null){
            mCam.release();
            mCam = null;
        }
    }
    //JPEGデータ生成完了時のコールバック
    private Camera.PictureCallback mPicJpgListener = new Camera.PictureCallback(){
        public void onPictureTaken(byte[] data,Camera camera){
            if(data == null){
                return;
            }

            String saveDir = Environment.getExternalStorageDirectory().getPath() + "/test";

            //SDカードフォルダを取得
            File file = new File(saveDir);

            //フォルダ作成
            if(!file.exists()){
                if(!file.mkdir()){
                    Log.e("Debug","Make Dir Error");
                }
            }

            //画像保存パス
            Calendar cal = Calendar.getInstance();
            Date today = cal.getTime();
            DateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
            String imgPath = saveDir + "/" + sf.format(today) + ".jpg";

            //ファイル保存
            FileOutputStream fos;
            try{
                fos = new FileOutputStream(imgPath,true);
                fos.write(data);
                fos.close();

                //アンドロイドのデータベースへ登録
                registAndroidDB(imgPath);
            }catch(Exception e){
                Log.e("Debug",e.getMessage());
            }

            fos = null;

            //takePictureするとプレビューが停止するので、再度プレビュースタート
            mCam.startPreview();

            misTake = false;

        }
    };

    /*
    アンドロイドのデータベースへ画像のパスを登録
    @param path 登録するパス
     */
    private void registAndroidDB(String path){
        //アンドロイドのデータベースへ登録
        ContentValues values = new ContentValues();
        ContentResolver contentResolver = SimpleCameraActivity.this.getContentResolver();
        values.put(MediaStore.Images.Media.MIME_TYPE,"image/jpeg");
        values.put("_data",path);
        contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
    }
}
