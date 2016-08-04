package com.example.admin.fashion;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;

/**
 * Created by admin on 2016/08/04.
 */
public class SimpleCameraActivity extends AppCompatActivity{
    //カメラインスタンス
    private Camera mCam = null;

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
}
