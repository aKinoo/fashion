package com.example.admin.fashion;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by admin on 2016/07/24.
 */
public class CanvasView extends View {
    Paint paint;
    boolean flag = false;

    public CanvasView(Context context, AttributeSet attrs){
        super(context,attrs);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas){
        //背景、半透明
        if(!flag){
            canvas.drawColor(Color.argb(125,0,0,255));
        }else{
            canvas.drawColor(Color.argb(125,255,0,0));
        }


//        paint.setColor(Color.argb(255,255,0,255));
//        paint.setStrokeWidth(10);
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawRect(280,280,850,880,paint);
    }

    public void showCanvas(){
        invalidate();
    }
}
