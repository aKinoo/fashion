package com.example.admin.fashion;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by admin on 2016/08/02.
 */
public class Tree {
    ArrayList<int[]> trainData = new ArrayList<int[]>();
    int[] attrNum = {3,3,4,5};

    Tree(){

    }

    public void setTrainData(){
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

    public void makeTreeBottom(){
        setTrainData();
        double t0 = entropy(0);
        double t1 = entropy(1);
        double t2 = entropy(2);
        double[] rank = {t0,t1,t2};


    }

    public double entropy(int column){
        int len = trainData.size();
        int sumIndex = attrNum[3];
        int[][] matrix = new int[attrNum[column]][sumIndex+1];
        int num = 0;
        for(int[] tData: trainData){
            int dataA = tData[column];
            int dataB = tData[3];
            matrix[dataA][sumIndex]++;
            matrix[dataA][dataB]++;
            num++;
        }
        double entropy = 0;
        for(int i = 0; i < attrNum[column];i++){
            double en = 0;
            for(int j = 0; j < attrNum[3];j++){
//                Log.d("entropy",matrix[i][sumIndex]+"");
                double divide = matrix[i][sumIndex] + 0.0;
                double p =matrix[i][j]/divide;
                double log = 0;
                if(p != 0){
                    log = Math.log(p) / Math.log(2.0);
                }

                en += -1 * p *log;
            }
            double n = num + 0.0;
            entropy += (matrix[i][sumIndex]*en)/n;
//            Log.d("entropy",entropy+"");
        }
//        Log.d("entropy",entropy+"");
        return entropy;
    }
}
