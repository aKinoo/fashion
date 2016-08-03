package com.example.admin.fashion;

import java.util.ArrayList;

/**
 * Created by admin on 2016/08/02.
 */
public class Tree {
    ArrayList<int[]> trainData = new ArrayList<int[]>();

    private void setTrainData(){
        //0天気（0晴れ,1くもり,2雨）,1温度(0:15以下,1:15~25,2:25以上),2スケジュール(0何もない,1デート,2友達,3バイト),
        // 3結果(0harfpants,1longpants,2longskirt,3shortskirt,4one-piece)
        int[][] tData = {{0,2,0,0},{1,2,0,3},{2,2,0,0},     //学校
                {0,1,1,4},                                  //デート
                {1,0,3,1},                                  //バイト
                {0,0,2,2},{1,1,2,2},{2,2,2,3}};
        int len = tData.length;
        for(int i = 0; i<len;i++){
            trainData.add(tData[i]);
        }
    }

    private float entropy(){
        return 0;
    }
}
