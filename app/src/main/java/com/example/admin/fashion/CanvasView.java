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
    //0白、1水色、2紺、3モスグリーン,4黄,5茶,6ピンク7,灰,8黒
    int[][] color = {{240,240,240},{173,216,230},{35,59,108},{119,126,65},{255,255,0},{165,42,42},{255,192,203},{211,211,211},{0,0,0}};
    int index = 0;

    public CanvasView(Context context, AttributeSet attrs){
        super(context,attrs);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas){
        //背景、半透明
        canvas.drawColor(Color.argb(125,color[index][0],color[index][1],color[index][2]));

//        paint.setColor(Color.argb(255,255,0,255));
//        paint.setStrokeWidth(10);
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawRect(280,280,850,880,paint);
    }

    public void showCanvas(int idx){
        index = idx;
        invalidate();
    }

    int[][] getColor(){
        return color;
    }
}
